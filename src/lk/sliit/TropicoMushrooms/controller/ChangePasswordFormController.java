package lk.sliit.TropicoMushrooms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import lk.sliit.TropicoMushrooms.bo.BOFactory;
import lk.sliit.TropicoMushrooms.bo.custom.UserBO;
import lk.sliit.TropicoMushrooms.dto.UserDTO;

public class ChangePasswordFormController {
    public AnchorPane window;
    public AnchorPane panePasswordEdit;
    public Text lblUserId;
    public JFXButton btnChangePassword;
    public JFXPasswordField txtCurrentPassword;
    public JFXPasswordField txtNewPassword;
    public JFXPasswordField txtAgainNewPassword;
    public JFXButton btnUpdateInfo;
    public Text txtCurrentPasswordError;
    public Text txtAgainPasswordError;
    public Text txtNewPasswordError;

    UserBO bo = BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    private String userId;

    public void initialize(){
        setUserId();
        setDefault();
    }

    private void setUserId() {
        try {
            userId=bo.getUserId();
            lblUserId.setText(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateInfoOnAction(ActionEvent actionEvent) {
        if (btnUpdateInfo.getText().equals("Update Info")){
            panePasswordEdit.setDisable(false);
            btnUpdateInfo.setText("Cancel");
        } else if (btnUpdateInfo.getText().equals("Cancel")){
            setDefault();
            btnUpdateInfo.setText("Update Info");
        }
    }

    public void btnChangePasswordOnAction(ActionEvent actionEvent) {
        if (isOldPassCorrect(lblUserId.getText())){
            if (txtNewPassword.getText().equals(txtAgainNewPassword.getText())){
                try {
                    if (bo.updateUserPassword(new UserDTO(Integer.parseInt(lblUserId.getText()),txtAgainNewPassword.getText()))) {
                        new Alert(Alert.AlertType.INFORMATION, "SuccessFully Changed").show();
                        setDefault();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Changing Failed").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                txtNewPassword.setFocusColor(Paint.valueOf("red"));
                txtAgainNewPassword.setFocusColor(Paint.valueOf("red"));
                txtNewPassword.requestFocus();
                txtNewPasswordError.setVisible(true);
                txtAgainPasswordError.setVisible(true);
            }
        } else {
            txtCurrentPassword.setFocusColor(Paint.valueOf("red"));
            txtCurrentPassword.requestFocus();
            txtCurrentPasswordError.setVisible(true);
        }
    }

    private boolean isOldPassCorrect(String id) {
        try {
            return txtCurrentPassword.getText().equals(bo.getPasswordFromUserId(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setDefault() {
        panePasswordEdit.setDisable(true);
        btnUpdateInfo.setText("Update Info");
        txtCurrentPassword.clear();
        txtNewPassword.clear();
        txtAgainNewPassword.clear();
        txtCurrentPasswordError.setVisible(false);
        txtNewPasswordError.setVisible(false);
        txtAgainPasswordError.setVisible(false);
        txtCurrentPassword.setFocusColor(Paint.valueOf("black"));
        txtNewPassword.setFocusColor(Paint.valueOf("black"));
        txtAgainNewPassword.setFocusColor(Paint.valueOf("black"));
    }
}
