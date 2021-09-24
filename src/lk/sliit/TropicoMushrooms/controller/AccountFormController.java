package lk.sliit.TropicoMushrooms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import lk.sliit.TropicoMushrooms.bo.BOFactory;
import lk.sliit.TropicoMushrooms.bo.custom.UserBO;
import lk.sliit.TropicoMushrooms.dto.SqDTO;
import lk.sliit.TropicoMushrooms.dto.UserDTO;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AccountFormController {

    public AnchorPane window;
    public AnchorPane paneUserEdit;
    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXTextField txtMobileNumber;
    public Text lblUserId;
    public JFXButton btnUpdate;
    public JFXTextField txtDesignation;
    public JFXButton btnUpdateInfo;
    public AnchorPane paneQA;
    public JFXTextField txtA1;
    public JFXComboBox<String> cmbQ1;
    public JFXTextField txtA2;
    public JFXComboBox<String> cmbQ2;
    public JFXTextField txtA3;
    public JFXComboBox<String> cmbQ3;
    public JFXButton btnUpdateQA;

    UserBO bo = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    private static String userId;

    public void initialize() {
        setDefault();
        loadId();
        loadAllQuestions();
        loadDataToForm(userId);
    }

    private void loadId() {
        lblUserId.setText(userId);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[A-z ]{2,}$").matcher(txtName.getText()).matches()) {
            if (Pattern.compile("^[0-9]{9}V$").matcher(txtNIC.getText()).matches() || Pattern.compile("^[0-9]{9}v$").matcher(txtNIC.getText()).matches() || Pattern.compile("^[0-9]{12}$").matcher(txtNIC.getText()).matches()) {
                if (Pattern.compile("^[0-9]{9}v$").matcher(txtNIC.getText()).matches()) {
                    String temp = txtNIC.getText();
                    temp = temp.split("[a-z]")[1];
                    temp = temp + "V";
                    txtNIC.setText(temp);
                }
                if (Pattern.compile("^[A-z0-9 ,.]{2,}$").matcher(txtAddress.getText()).matches()) {
                    if (Pattern.compile("^0[0-9]{9}$").matcher(txtMobileNumber.getText()).matches()) {
                        if (Pattern.compile("^[A-z]{2,}$").matcher(txtDesignation.getText()).matches()) {
                            //-----
                            if (updateUserBasic()) {
                                new Alert(Alert.AlertType.INFORMATION, "SuccessFully Added").show();
                                setDefault();
                                loadId();
                                loadDataToForm(userId);
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Adding Failed").show();
                            }
                        } else {
                            txtDesignation.setFocusColor(Paint.valueOf("red"));
                            txtDesignation.requestFocus();
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

    private boolean updateUserBasic() {
        try {
            return bo.updateBasicInfo(new UserDTO(
                    Integer.parseInt(lblUserId.getText()),
                    txtName.getText(),
                    txtNIC.getText(),
                    txtAddress.getText(),
                    txtMobileNumber.getText(),
                    txtDesignation.getText()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void btnUpdateInfoOnAction(ActionEvent actionEvent) {
        if (btnUpdateInfo.getText().equals("Update Info")) {
            paneUserEdit.setDisable(false);
            paneQA.setDisable(false);
            btnUpdate.setVisible(true);
            btnUpdateQA.setVisible(true);
            btnUpdateInfo.setText("Cancel");
        } else if (btnUpdateInfo.getText().equals("Cancel")) {
            setDefault();
            btnUpdateInfo.setText("Update Info");
        }
    }

    public void btnUpdateQAOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[A-z0-9 ]{2,}$").matcher(txtA1.getText()).matches()) {
            if (Pattern.compile("^[A-z0-9 ]{2,}$").matcher(txtA2.getText()).matches()) {
                if (Pattern.compile("^[A-z0-9 ]{2,}$").matcher(txtA3.getText()).matches()) {
                    if (updateUserQA()) {
                        new Alert(Alert.AlertType.INFORMATION, "SuccessFully Added").show();
                        setDefault();
                        loadId();
                        loadDataToForm(userId);
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Adding Failed").show();
                    }
                } else {
                    txtA3.setFocusColor(Paint.valueOf("red"));
                    txtA3.requestFocus();
                }
            } else {
                txtA2.setFocusColor(Paint.valueOf("red"));
                txtA2.requestFocus();
            }
        } else {
            txtA1.setFocusColor(Paint.valueOf("red"));
            txtA1.requestFocus();
        }
    }

    private boolean updateUserQA() {
        try {
            return bo.updateUserQA(new UserDTO(
                    Integer.parseInt(lblUserId.getText()),
                    cmbQ1.getValue(),
                    cmbQ2.getValue(),
                    cmbQ3.getValue(),
                    txtA1.getText(),
                    txtA2.getText(),
                    txtA3.getText(),
                    0
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setDefault() {
        btnUpdateInfo.setDisable(false);
        btnUpdate.setVisible(false);
        btnUpdateQA.setVisible(false);
        paneUserEdit.setDisable(true);
        paneQA.setDisable(true);
        btnUpdateInfo.setText("Update Info");

        lblUserId.setText("");
        txtName.clear();
        txtNIC.clear();
        txtAddress.clear();
        txtMobileNumber.clear();
        txtDesignation.clear();
        loadAllQuestions();
        txtA1.clear();
        txtA2.clear();
        txtA3.clear();

        txtName.setFocusColor(Paint.valueOf("black"));
        txtNIC.setFocusColor(Paint.valueOf("black"));
        txtAddress.setFocusColor(Paint.valueOf("black"));
        txtMobileNumber.setFocusColor(Paint.valueOf("black"));
        txtDesignation.setFocusColor(Paint.valueOf("black"));
        txtA1.setFocusColor(Paint.valueOf("black"));
        txtA2.setFocusColor(Paint.valueOf("black"));
        txtA3.setFocusColor(Paint.valueOf("black"));
    }

    private void loadAllQuestions() {
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            ArrayList<SqDTO> allSQ = bo.getAllSQ();
            for (SqDTO dto : allSQ) {
                list.add(dto.getQuestion());
            }
            cmbQ1.setItems(list);
            cmbQ2.setItems(list);
            cmbQ3.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataToForm(String userId) {
        try {
            UserDTO dto = bo.search(userId);
            txtName.setText(dto.getName());
            txtNIC.setText(dto.getNic());
            txtAddress.setText(dto.getAddress());
            txtMobileNumber.setText(dto.getMobile());
            txtDesignation.setText(dto.getDesignation());
            cmbQ1.setValue(dto.getQ1());
            cmbQ2.setValue(dto.getQ2());
            cmbQ3.setValue(dto.getQ3());
            txtA1.setText(dto.getA1());
            txtA2.setText(dto.getA2());
            txtA3.setText(dto.getA3());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
