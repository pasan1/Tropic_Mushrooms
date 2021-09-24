package lk.sliit.TropicoMushrooms.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInRight;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.sliit.TropicoMushrooms.controller.employee.EmployeePurchaseFormController;
import lk.sliit.TropicoMushrooms.controller.manager.ManagePurchaseFormController;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class EmployeeMainFormController {
    public AnchorPane root;
    public AnchorPane paneUser;
    public ImageView btnArrowDown;
    public ImageView btnArrowUp;
    public Label lblUserName;
    public AnchorPane paneTitle;
    public Text lblTitle;
    public ImageView imgHome;
    public AnchorPane paneMenu;
    public AnchorPane paneBody;
    public AnchorPane paneUserOptions;
    public JFXButton btnLogout;
    public AnchorPane paneUserDisplay;

    private static String userId;
    private static String userName;

    public void initialize() throws IOException {
        loadDefaultForm();
        setDefaults();
        loadDateTime();
    }

    public void btnExitOnMouseClicked(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("EXIT ???");
        Optional<ButtonType> buttonType = alert.showAndWait();
        ButtonType btnYes = new ButtonType("Yes");
        ButtonType btnNo = new ButtonType("No");
        if (buttonType.get() == ButtonType.YES) {
            System.exit(0);
        } else if (buttonType.get() == ButtonType.NO) {
            alert.close();
        }
    }

    public void btnMinimizeOnMouseClicked(MouseEvent mouseEvent) {
        Stage window = (Stage) root.getScene().getWindow();
        window.setIconified(true);
    }

    public void btnSalesOnAction(ActionEvent actionEvent) throws IOException {
        OrderFormController.setUserId(userId);
        loadUI("OrderForm");
        lblTitle.setText("Tropico Mushrooms's | Sale Management System | Order Form");
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        loadUI("employee/EmployeeCustomerForm");
        lblTitle.setText("Tropico Mushrooms's | Sale Management System | Customer Form");
    }

    public void btnItemOnAction(ActionEvent actionEvent) throws IOException {
        loadUI("employee/EmployeeItemForm");
        lblTitle.setText("Tropico Mushrooms's | Sale Management System | Item Form");
    }

    public void btnPurchaseOnAction(ActionEvent actionEvent) throws IOException {
        EmployeePurchaseFormController.setUserId(userId);
        loadUI("employee/EmployeePurchaseForm");
        lblTitle.setText("Tropico Mushrooms's | Sale Management System | Purchase Form");
    }

    public void btnReturnsOnAction(ActionEvent actionEvent) throws IOException {
        ReturnsFormController.setUserId(userId);
        loadUI("ReturnsForm");
        lblTitle.setText("Tropico Mushrooms's | Sale Management System | Returns Form");
    }

    public void btnAccountOnAction(ActionEvent actionEvent) throws IOException {
        loadUI("AccountForm");
        lblTitle.setText("Tropico Mushrooms's | Sale Management System | Account Form");
    }

    public void txtGoBackOnMouseClicked(MouseEvent mouseEvent) {
        paneUserOptions.setVisible(false);
        new FadeIn(paneUserOptions).play();
        paneMenu.setVisible(true);
        btnArrowUp.setVisible(false);
        btnArrowDown.setVisible(true);
    }

    public void btnChangePasswordOnAction(ActionEvent actionEvent) throws IOException {
        loadUI("ChangePasswordForm");
        lblTitle.setText("Tropico Mushrooms's | Sale Management System | Change Password Form");
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to logout ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("LOGOUT ???");
        Optional<ButtonType> buttonType = alert.showAndWait();
        ButtonType btnYes = new ButtonType("Yes");
        ButtonType btnNo = new ButtonType("No");
        if (buttonType.get() == ButtonType.YES) {
            loadLogin();
        } else if (buttonType.get() == ButtonType.NO) {
            alert.close();
        }
    }

    public void btnUserNameOnMouseClicked(MouseEvent mouseEvent) {
        if (paneUserOptions.isVisible()) {
            paneUserOptions.setVisible(false);
            new FadeIn(paneUserOptions).play();
            paneMenu.setVisible(true);
            btnArrowUp.setVisible(false);
            btnArrowDown.setVisible(true);
        } else {
            paneUserOptions.setVisible(true);
            new FadeIn(paneUserOptions).play();
            paneMenu.setVisible(false);
            btnArrowUp.setVisible(true);
            btnArrowDown.setVisible(false);
        }
    }

    private void loadUI(String formName) throws IOException {

        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("../view/" + formName + ".fxml"));
        paneBody.getChildren().clear();
        paneBody.getChildren().add(pane);
        new FadeInRight(pane).play();
        //paneBody.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/" + formName + ".fxml")));
    }

    public void loadLogin() throws IOException {
//        Stage window = (Stage) this.root.getScene().getWindow();
//        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"))));
//        window.centerOnScreen();

        Parent root = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        new FadeIn(root).setSpeed(0.7).play();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.show();

        Stage primaryStage = (Stage) btnLogout.getScene().getWindow();
        primaryStage.close();
    }

    private void setDefaults() {
        lblUserName.setText(userName);
        paneUserOptions.setVisible(false);
        paneMenu.setVisible(true);
        btnArrowDown.setVisible(true);
        btnArrowUp.setVisible(false);
    }

    private void loadDateTime() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable beeper = new Runnable() {
            public void run() {
//                txtDate.setText(String.valueOf(LocalDate.now()));
//                txtTime.setText((LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
            }
        };
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 1, TimeUnit.SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() {
                try {
                    loadLogin();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                beeperHandle.cancel(true);
            }
        }, 86400, TimeUnit.SECONDS);
    }

    public void imgHomeOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        loadDefaultForm();
    }

    private void loadDefaultForm() throws IOException {
        loadUI("DefaultForm");
        lblTitle.setText("Welcome to Tropico Mushrooms's | Sale Management System");
    }

    public static void setUserId(String userId) {
        EmployeeMainFormController.userId = userId;
    }

    public static void setUserName(String userName) {
        EmployeeMainFormController.userName = userName;
    }
}
