package lk.sliit.TropicoMushrooms.controller.manager;

import com.jfoenix.controls.JFXButton;
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
import lk.sliit.TropicoMushrooms.bo.custom.CustomerBO;
import lk.sliit.TropicoMushrooms.db.DBConnection;
import lk.sliit.TropicoMushrooms.dto.CustomerDTO;
import lk.sliit.TropicoMushrooms.tm.CustomerTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class ManageCustomerFormController {
    public AnchorPane window;
    public JFXButton btnSearchCustomer;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCustomerID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colMobileNumber;
    public TableColumn colNIC;
    public TextField txtSearchCustomer;
    public AnchorPane paneCustomerEdit;
    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXTextField txtMobileNumber;
    public Text lblCustomerID;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXButton btnNewCustomer;
    public Text lblCompleteAll;
    public JFXButton btnPrintAllCustomers;

    private CustomerBO bo = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize() {
        setDefault();
        setTableCols();
        loadLastId();
        loadTable();
        txtSearchCustomer.requestFocus();

        txtSearchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ArrayList<CustomerDTO> allCustomersDetails = bo.getSearchCustomerDetails(newValue);
                ObservableList<CustomerTM> list = FXCollections.observableArrayList();

                for (CustomerDTO dto : allCustomersDetails) {
                    list.add(new CustomerTM(
                            dto.getCustomerId(),
                            dto.getName(),
                            dto.getAddress(),
                            dto.getMobile(),
                            dto.getNic()
                    ));
                }
                tblCustomer.setItems(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDefault();
            if (null != newValue)
                loadCustomerDataToForm(String.valueOf(newValue.getCustomerId()));
            paneCustomerEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        });
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[0-9]{9}V$").matcher(txtSearchCustomer.getText()).matches() || Pattern.compile("^[0-9]{9}v$").matcher(txtSearchCustomer.getText()).matches() || Pattern.compile("^[0-9]{12}$").matcher(txtSearchCustomer.getText()).matches()) {
            if (Pattern.compile("^[0-9]{9}v$").matcher(txtSearchCustomer.getText()).matches()) {
                String temp = txtSearchCustomer.getText();
                temp = temp.split("[a-z]")[1];
                temp = temp + "V";
                txtSearchCustomer.setText(temp);
            }
            loadCustomerDataToFormByNumber(txtSearchCustomer.getText());
            paneCustomerEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        } else {
            txtSearchCustomer.setStyle("-fx-border-color: red");
            txtSearchCustomer.requestFocus();
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
                        //-----
                        if (btnAdd.getText().equals("Add")) {
                            if (addCustomer()) {
                                new Alert(Alert.AlertType.INFORMATION, "SuccessFully Added").show();
                                loadLastId();
                                loadTable();
                                setDefault();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Adding Failed").show();
                            }
                        } else if (btnAdd.getText().equals("Update")) {
                            if (updateCustomer()) {
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " + lblCustomerID.getText() + " " + txtName.getText() + "?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Do you want to delete?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            ButtonType btnYes = new ButtonType("Yes");
            ButtonType btnNo = new ButtonType("No");
            if (buttonType.get() == ButtonType.YES) {
                if (bo.delete(lblCustomerID.getText())) {
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

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {
        setDefault();
        loadLastId();
        paneCustomerEdit.setDisable(false);
        btnDelete.setVisible(false);
    }

    private void loadCustomerDataToForm(String id) {
        try {
            CustomerDTO dto = bo.search(id);
            lblCustomerID.setText(String.valueOf(dto.getCustomerId()));
            txtName.setText(dto.getName());
            txtAddress.setText(dto.getAddress());
            txtMobileNumber.setText(dto.getMobile());
            txtNIC.setText(dto.getNic());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerDataToFormByNumber(String nic) {
        try {
            CustomerDTO dto = bo.searchByNIC(nic);
            lblCustomerID.setText(String.valueOf(dto.getCustomerId()));
            txtName.setText(dto.getName());
            txtAddress.setText(dto.getAddress());
            txtMobileNumber.setText(dto.getMobile());
            txtNIC.setText(dto.getNic());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean addCustomer() {
        try {
            return bo.save(new CustomerDTO(
                    Integer.parseInt(lblCustomerID.getText()),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtMobileNumber.getText(),
                    txtNIC.getText()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean updateCustomer() {
        try {
            return bo.update(new CustomerDTO(
                    Integer.parseInt(lblCustomerID.getText()),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtMobileNumber.getText(),
                    txtNIC.getText()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setTableCols() {
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
    }

    private void loadTable() {
        try {
            ArrayList<CustomerDTO> allCustomersDetails = bo.getAll();
            ObservableList<CustomerTM> list = FXCollections.observableArrayList();

            for (CustomerDTO dto : allCustomersDetails) {
                list.add(new CustomerTM(
                        dto.getCustomerId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getMobile(),
                        dto.getNic()
                ));
            }
            tblCustomer.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLastId() {
        try {
            String lastCode = bo.getCustomerLastId();
            if (null != lastCode) {
//                lastCode = lastCode.split("[A-Z]")[1];
                lastCode = "" + (Integer.parseInt(lastCode) + 001);
                lblCustomerID.setText(lastCode);
            } else {
                lblCustomerID.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDefault() {
        paneCustomerEdit.setDisable(true);
        btnSearchCustomer.setVisible(true);
        btnSearchCustomer.setDisable(false);
        txtSearchCustomer.setDisable(false);
        txtSearchCustomer.clear();
        txtSearchCustomer.setStyle("-fx-border-color: black");
        btnNewCustomer.setDisable(false);
        btnAdd.setText("Add");
        lblCompleteAll.setVisible(false);
        btnDelete.setVisible(true);
        lblCustomerID.setText("");
        txtName.clear();
        txtNIC.clear();
        txtAddress.clear();
        txtMobileNumber.clear();

        txtName.setFocusColor(Paint.valueOf("black"));
        txtNIC.setFocusColor(Paint.valueOf("black"));
        txtAddress.setFocusColor(Paint.valueOf("black"));
        txtMobileNumber.setFocusColor(Paint.valueOf("black"));
    }

    public void btnPrintAllCustomersOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/sliit/TropicoMushrooms/reports/CustomerReport.jrxml");
//            System.out.println(is);
            JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint jp = JasperFillManager.fillReport(jr,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,false);
//            JasperPrintManager.printReport(jp,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
