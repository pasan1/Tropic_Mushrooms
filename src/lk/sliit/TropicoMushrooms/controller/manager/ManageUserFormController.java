package lk.sliit.TropicoMushrooms.controller.manager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import lk.sliit.TropicoMushrooms.bo.BOFactory;
import lk.sliit.TropicoMushrooms.bo.custom.UserBO;
import lk.sliit.TropicoMushrooms.dto.UserDTO;
import lk.sliit.TropicoMushrooms.tm.UserTM;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class ManageUserFormController {
    public AnchorPane window;
    public JFXButton btnSearchUser;
    public TableView<UserTM> tbIUser;
    public TableColumn colUserID;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colMobileNumber;
    public TableColumn colDesignation;
    public TableColumn colUserName;
    public TextField txtSearchUser;
    public AnchorPane paneUserEdit;
    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXTextField txtMobileNumber;
    public Text lblUserID;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXTextField txtUserName;
    public JFXComboBox<String> cmbDesignation;
    public Text lblNewUser;
    public JFXButton btnNewUser;

    UserBO bo = BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        setTableCols();
        setDefault();
        loadTable();
        txtSearchUser.requestFocus();

        txtSearchUser.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ArrayList<UserDTO> allUsersDetails = bo.getUsersDetails(newValue);
                ObservableList<UserTM> list = FXCollections.observableArrayList();

                for (UserDTO dto : allUsersDetails) {
                    list.add(new UserTM(
                            dto.getUserId(),
                            dto.getName(),
                            dto.getNic(),
                            dto.getAddress(),
                            dto.getMobile(),
                            dto.getDesignation(),
                            dto.getUserName()
                    ));
                }
                tbIUser.setItems(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        tbIUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDefault();
            if (null != newValue)
                loadInstructorDataToForm(String.valueOf(newValue.getUserId()));
            paneUserEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        });
    }

    public void btnSearchUserOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[0-9]{9}V$").matcher(txtSearchUser.getText()).matches() || Pattern.compile("^[0-9]{9}v$").matcher(txtSearchUser.getText()).matches() || Pattern.compile("^[0-9]{12}$").matcher(txtSearchUser.getText()).matches()) {
            if (Pattern.compile("^[0-9]{9}v$").matcher(txtNIC.getText()).matches()) {
                String temp = txtNIC.getText();
                temp = temp.split("[a-z]")[1];
                temp = temp + "V";
                txtNIC.setText(temp);
            }
            loadInstructorDataToFormByNIC(txtSearchUser.getText());
            paneUserEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        } else {
            txtSearchUser.setStyle("-fx-border-color: red");
            txtSearchUser.requestFocus();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[A-z ]{2,}$").matcher(txtName.getText()).matches()) {
            if (Pattern.compile("^[0-9]{9}V$").matcher(txtNIC.getText()).matches() || Pattern.compile("^[0-9]{9}v$").matcher(txtNIC.getText()).matches() || Pattern.compile("^[0-9]{12}$").matcher(txtNIC.getText()).matches()) {
                if (Pattern.compile("^[0-9]{9}v$").matcher(txtNIC.getText()).matches()) {
                    String temp = txtNIC.getText();
                    temp = temp.toUpperCase();
                    txtNIC.setText(temp);
                }
                if (Pattern.compile("^[A-z0-9 ,.]{2,}$").matcher(txtAddress.getText()).matches()) {
                    if (Pattern.compile("^0[0-9]{9}$").matcher(txtMobileNumber.getText()).matches()) {
                        if (Pattern.compile("^[A-z0-9]{1,}$").matcher(txtUserName.getText()).matches()) {
                            if (cmbDesignation.getValue() != null) {
                                //-----
                                if (btnAdd.getText().equals("Add")) {
                                    if (addUser()) {
                                        new Alert(Alert.AlertType.INFORMATION, "SuccessFully Added").show();
                                        loadLastId();
                                        loadTable();
                                        setDefault();
                                    } else {
                                        new Alert(Alert.AlertType.WARNING, "Adding Failed").show();
                                    }
                                } else if (btnAdd.getText().equals("Update")) {
                                    if (updateUser()) {
                                        new Alert(Alert.AlertType.INFORMATION, "SuccessFully Updated").show();
                                        loadLastId();
                                        loadTable();
                                        setDefault();
                                    } else {
                                        new Alert(Alert.AlertType.WARNING, "Updating Failed").show();
                                    }
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Error on Add button").show();
                                }
                            } else {
                                cmbDesignation.setFocusColor(Paint.valueOf("red"));
                                cmbDesignation.requestFocus();
                            }
                        } else {
                            txtUserName.setFocusColor(Paint.valueOf("red"));
                            txtUserName.requestFocus();
                        }
                    } else {
                        txtMobileNumber.setFocusColor(Paint.valueOf("red"));
                        txtMobileNumber.requestFocus();
                    }
                } else {
                    txtAddress.setFocusColor(Paint.valueOf("red"));
                    txtAddress.requestFocus();
                }
            } else {
                txtNIC.setFocusColor(Paint.valueOf("red"));
                txtNIC.requestFocus();
            }
        } else {
            txtName.setFocusColor(Paint.valueOf("red"));
            txtName.requestFocus();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " + lblUserID.getText() + " " + txtName.getText() + "?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Do you want to delete?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            ButtonType btnYes = new ButtonType("Yes");
            ButtonType btnNo = new ButtonType("No");
            if (buttonType.get() == ButtonType.YES) {
                if (bo.delete(lblUserID.getText())) {
                    new Alert(Alert.AlertType.INFORMATION, "SuccessFully Deleted").show();
                    loadLastId();
                    loadTable();
                    setDefault();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Deleting was Failed").show();
                }
            } else if (buttonType.get() == ButtonType.NO) {
                alert.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnNewUserOnAction(ActionEvent actionEvent) {
        setDefault();
        loadLastId();
        paneUserEdit.setDisable(false);
        btnDelete.setVisible(false);
        lblNewUser.setVisible(true);
    }

    private boolean addUser() {
        try {
            return bo.save(new UserDTO(
                    Integer.parseInt(lblUserID.getText()),
                    txtName.getText(),
                    txtNIC.getText(),
                    txtAddress.getText(),
                    txtMobileNumber.getText(),
                    cmbDesignation.getValue(),
                    txtUserName.getText(),
                    "USER",
                    "0",
                    "0",
                    "0",
                    "",
                    "",
                    ""
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean updateUser() {
        try {
            return bo.updateUserBasic(new UserDTO(
                    Integer.parseInt(lblUserID.getText()),
                    txtName.getText(),
                    txtNIC.getText(),
                    txtAddress.getText(),
                    txtMobileNumber.getText(),
                    cmbDesignation.getValue(),
                    txtUserName.getText()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void loadInstructorDataToForm(String id) {
        try {
            UserDTO dto = bo.search(id);
            lblUserID.setText(String.valueOf(dto.getUserId()));
            txtName.setText(dto.getName());
            txtNIC.setText(dto.getNic());
            txtAddress.setText(dto.getAddress());
            txtMobileNumber.setText(dto.getMobile());
            cmbDesignation.setValue(dto.getDesignation());
            txtUserName.setText(dto.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadInstructorDataToFormByNIC(String nic) {
        try {
            UserDTO dto = bo.searchByNIC(nic);
            lblUserID.setText(String.valueOf(dto.getUserId()));
            txtName.setText(dto.getName());
            txtNIC.setText(dto.getNic());
            txtAddress.setText(dto.getAddress());
            txtMobileNumber.setText(dto.getMobile());
            cmbDesignation.setValue(dto.getDesignation());
            txtUserName.setText(dto.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTableCols() {
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        colDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
    }

    private void loadTable() {
        try {
            ArrayList<UserDTO> allUsersDetails = bo.getAll();
            ObservableList<UserTM> list = FXCollections.observableArrayList();

            for (UserDTO dto : allUsersDetails) {
                list.add(new UserTM(
                        dto.getUserId(),
                        dto.getName(),
                        dto.getNic(),
                        dto.getAddress(),
                        dto.getMobile(),
                        dto.getDesignation(),
                        dto.getUserName()
                ));
            }
            tbIUser.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDefault() {
        paneUserEdit.setDisable(true);
        txtSearchUser.clear();
        btnNewUser.setDisable(false);
        btnAdd.setText("Add");
        btnDelete.setVisible(true);
        lblUserID.setText("");
        loadLastId();
        txtName.clear();
        txtNIC.clear();
        txtAddress.clear();
        txtMobileNumber.clear();
        txtUserName.clear();
        loadDesignation();

        lblNewUser.setVisible(false);

        txtSearchUser.setStyle("-fx-border-color: black");
        txtName.setFocusColor(Paint.valueOf("black"));
        txtNIC.setFocusColor(Paint.valueOf("black"));
        txtAddress.setFocusColor(Paint.valueOf("black"));
        txtMobileNumber.setFocusColor(Paint.valueOf("black"));
        txtUserName.setFocusColor(Paint.valueOf("black"));
        cmbDesignation.setFocusColor(Paint.valueOf("black"));
    }

    private void loadDesignation() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Manager");
        list.add("Employee");
        list.add("InActive");
        cmbDesignation.setItems(list);
    }

    private void loadLastId() {
        try {
            String lastCode = bo.getUserLastId();
            if (null != lastCode) {
//                lastCode = lastCode.split("[A-Z]")[1];
                lastCode = "" + (Integer.parseInt(lastCode) + 001);
                lblUserID.setText(lastCode);
            } else {
                lblUserID.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
