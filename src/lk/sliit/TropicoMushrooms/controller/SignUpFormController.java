package lk.sliit.TropicoMushrooms.controller;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.sliit.TropicoMushrooms.bo.BOFactory;
import lk.sliit.TropicoMushrooms.bo.custom.UserBO;
import lk.sliit.TropicoMushrooms.dto.UserDTO;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SignUpFormController {
    public AnchorPane pane;
    public JFXTextField txtUserName;
    public JFXPasswordField txtOldPassword;
    public JFXPasswordField txtNewPassword;
    public JFXPasswordField txtConfirmPassword;
    public Text lblErrorUserName;
    public Text lblErrorOldPassword;
    public Text lblErrorNewPassword;
    public Text lblErrorConfirmPassword;
    public JFXTextField txtA1;
    public JFXComboBox<String> cmbQ1;
    public JFXTextField txtA2;
    public JFXComboBox<String> cmbQ2;
    public JFXButton btnSignUp;
    public JFXTextField txtA3;
    public JFXComboBox<String> cmbQ3;
    public ImageView imgCorrectUserName;
    public ImageView imgCorrectOldPassword;
    public Text lblErrorFillAllRecords;
    public ImageView imgWrongUserName;
    public ImageView imgWrongOldPassword;

    UserBO bo = BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        setDefault();
        loadDataToComboBox();
    }

    public void btnMinimizeOnMouseClicked(MouseEvent mouseEvent) {
        Stage window = (Stage) pane.getScene().getWindow();
        window.setIconified(true);
    }

    public void txtUserNameOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            try {
                if (bo.isUserNameAvailable(txtUserName.getText())) {
                    imgCorrectUserName.setVisible(true);
                    imgWrongUserName.setVisible(false);
                } else {
                    imgWrongUserName.setVisible(true);
                    imgCorrectUserName.setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void txtOldPasswordOnPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            try {
                if (bo.isPasswordAvailable(txtOldPassword.getText())) {
                    imgCorrectOldPassword.setVisible(true);
                    imgWrongOldPassword.setVisible(false);
                } else {
                    imgWrongOldPassword.setVisible(true);
                    imgCorrectOldPassword.setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void txtNewPasswordOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtConfirmPassword.requestFocus();
        }
    }

    public void txtConfirmPasswordOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            cmbQ1.requestFocus();
        }
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        try {
            loadLoginForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnLoginOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            try {
                loadLoginForm();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLoginForm() throws Exception {
        if (bo.isUserNameAvailable(txtUserName.getText())) {
            imgCorrectUserName.setVisible(true);
            imgWrongUserName.setVisible(false);
            if (bo.isPasswordAvailable(txtOldPassword.getText())) {
                imgCorrectOldPassword.setVisible(true);
                imgWrongOldPassword.setVisible(false);
                if (validateForm()) {
                    if (confirmOldPasswords()) {
                        if (updateNewUser()) {
                            Parent root = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            new FadeIn(root).setSpeed(0.7).play();
                            stage.initStyle(StageStyle.TRANSPARENT);
                            scene.setFill(Color.TRANSPARENT);
                            stage.show();

                            Stage primaryStage = (Stage) btnSignUp.getScene().getWindow();
                            primaryStage.close();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "New User updating failed").show();
                        }
                    } else {

                    }
                } else {
                }
            } else {
                imgWrongOldPassword.setVisible(true);
                imgCorrectOldPassword.setVisible(false);
            }
        } else {
            imgWrongUserName.setVisible(true);
            imgCorrectUserName.setVisible(false);
        }
    }

    private boolean updateNewUser() {
        try {
            String userId = bo.getUserId(txtUserName.getText());
            return bo.updatePasswordAndSQ(new UserDTO(
                    Integer.parseInt(userId),
                    txtConfirmPassword.getText(),
                    cmbQ1.getValue(),
                    cmbQ2.getValue(),
                    cmbQ3.getValue(),
                    txtA1.getText(),
                    txtA2.getText(),
                    txtA3.getText()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean confirmOldPasswords() {
        boolean temp = false;
        if (txtUserName.getText() != null) {
            if (txtOldPassword.getText() != null) {
                try {
                    if (txtOldPassword.getText().equals(bo.getPasswordFromUserName(txtUserName.getText()))) {
                        temp = true;
                    } else {
                        txtUserName.setFocusColor(Paint.valueOf("red"));
                        txtOldPassword.setFocusColor(Paint.valueOf("red"));
                        txtUserName.requestFocus();
                        lblErrorOldPassword.setVisible(true);
                        lblErrorUserName.setVisible(true);
                        temp = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                txtOldPassword.setFocusColor(Paint.valueOf("red"));
                txtOldPassword.requestFocus();
                temp = false;
            }
        } else {
            txtUserName.setFocusColor(Paint.valueOf("red"));
            txtUserName.requestFocus();
            temp = false;
        }
        return temp;
    }

    private boolean validateForm() {
        if (Pattern.compile("^[A-z0-9]{2,}$").matcher(txtUserName.getText()).matches()) {
            if (Pattern.compile("^[A-z0-9 !@#$%^&*></+-.,()|;\"'?=:`~]{2,}$").matcher(txtOldPassword.getText()).matches()) {
                if (Pattern.compile("^[A-z0-9 !@#$%^&*></+-.,()|;\"'?=:`~]{2,}$").matcher(txtNewPassword.getText()).matches()) {
                    if (txtConfirmPassword.getText() != null) {
                        if (txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
                            if (Pattern.compile("^[A-z0-9 ,.]{2,}$").matcher(txtA1.getText()).matches() && Pattern.compile("^[A-z0-9 ,.]{2,}$").matcher(txtA2.getText()).matches() && Pattern.compile("^[A-z0-9 ,.]{2,}$").matcher(txtA1.getText()).matches()) {
                                return true;
                            } else {
                                lblErrorFillAllRecords.setVisible(true);
                                txtA1.setFocusColor(Paint.valueOf("red"));
                                txtA2.setFocusColor(Paint.valueOf("red"));
                                txtA3.setFocusColor(Paint.valueOf("red"));
                                return false;
                            }
                        } else {
                            txtConfirmPassword.setFocusColor(Paint.valueOf("red"));
                            txtNewPassword.setFocusColor(Paint.valueOf("red"));
                            txtNewPassword.requestFocus();
                            lblErrorNewPassword.setVisible(true);
                            lblErrorConfirmPassword.setVisible(true);
                            return false;
                        }
                    } else {
                        txtConfirmPassword.setFocusColor(Paint.valueOf("red"));
                        txtConfirmPassword.requestFocus();
                        return false;
                    }

                } else {
                    txtNewPassword.setFocusColor(Paint.valueOf("red"));
                    txtNewPassword.requestFocus();
                    return false;
                }
            } else {
                txtOldPassword.setFocusColor(Paint.valueOf("red"));
                txtOldPassword.requestFocus();
                return false;
            }
        } else {
            txtUserName.setFocusColor(Paint.valueOf("red"));
            txtUserName.requestFocus();
            return false;
        }
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnExitOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            System.exit(0);
        }
    }

    private void loadDataToComboBox() {
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            ArrayList<String> allQuestions = bo.getAllQuestions();
            for (String s : allQuestions) {
                list.add(s);
            }
            cmbQ1.setItems(list);
            cmbQ2.setItems(list);
            cmbQ3.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDefault() {
        txtUserName.clear();
        txtOldPassword.clear();
        txtNewPassword.clear();
        txtConfirmPassword.clear();
        txtA1.clear();
        txtA2.clear();
        txtA3.clear();
        imgCorrectUserName.setVisible(false);
        imgCorrectOldPassword.setVisible(false);
        imgWrongUserName.setVisible(false);
        imgWrongOldPassword.setVisible(false);
        lblErrorNewPassword.setVisible(false);
        lblErrorConfirmPassword.setVisible(false);
        lblErrorFillAllRecords.setVisible(false);
        lblErrorUserName.setVisible(false);
        lblErrorOldPassword.setVisible(false);
        txtNewPassword.setFocusColor(Paint.valueOf("black"));
        txtConfirmPassword.setFocusColor(Paint.valueOf("black"));
        txtOldPassword.setFocusColor(Paint.valueOf("black"));
        txtUserName.setFocusColor(Paint.valueOf("black"));
        txtA1.setFocusColor(Paint.valueOf("black"));
        txtA2.setFocusColor(Paint.valueOf("black"));
        txtA3.setFocusColor(Paint.valueOf("black"));
    }
}
