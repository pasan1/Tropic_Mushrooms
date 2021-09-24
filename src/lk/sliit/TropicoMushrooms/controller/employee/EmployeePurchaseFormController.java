package lk.sliit.TropicoMushrooms.controller.employee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import lk.sliit.TropicoMushrooms.bo.BOFactory;
import lk.sliit.TropicoMushrooms.bo.custom.PurchaseBO;
import lk.sliit.TropicoMushrooms.db.DBConnection;
import lk.sliit.TropicoMushrooms.dto.PurchaseDTO;
import lk.sliit.TropicoMushrooms.tm.PurchaseTM;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class EmployeePurchaseFormController {
    public AnchorPane window;
    public TableView<PurchaseTM> tblPurchase;
    public TableColumn colPurchaseID;
    public TableColumn colUserID;
    public TableColumn colDate;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnit;
    public TableColumn colTotalPrice;
    public AnchorPane panePurchaseEdit;
    public JFXTextField txtDescription;
    public JFXTextField txtUnit;
    public JFXTextField txtTotalPrice;
    public JFXTextField txtQty;
    public Text lblPurchaseID;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public Text lblCompleteAll;
    public Text lblUserID;
    public Text lblDate;
    public JFXButton btnNewPurchase;
    public JFXButton btnPrintAllPurchases;
    public JFXDatePicker dpSearchDate;
    public ImageView imgCancel;

    private static String userId;

    private PurchaseBO bo = BOFactory.getInstance().getBO(BOFactory.BOTypes.PURCHASE);

    public void initialize() {
        setDefault();
        setTableCols();
        loadLastId();
        loadTable();
        btnNewPurchase.requestFocus();

        tblPurchase.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDefault();
            if (null != newValue)
                loadPurchaseDataToForm(String.valueOf(newValue.getPurchaseId()));
            panePurchaseEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        });
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[A-z0-9 ,.]{2,}$").matcher(txtDescription.getText()).matches()) {
            if (Pattern.compile("^[0-9.]+$").matcher(txtQty.getText()).matches()) {
                if (Pattern.compile("^[0-9.]+$").matcher(txtTotalPrice.getText()).matches()) {
                    if (Pattern.compile("^[A-z0-9]{1,4}$").matcher(txtUnit.getText()).matches()) {
                        //-----1
                        if (btnAdd.getText().equals("Add")) {
                            if (addPurchase()) {
                                new Alert(Alert.AlertType.INFORMATION, "SuccessFully Added").show();
                                loadLastId();
                                loadTable();
                                setDefault();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Adding Failed").show();
                            }
                        } else if (btnAdd.getText().equals("Update")) {
                            if (updatePurchase()) {
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
                        txtUnit.setFocusColor(Paint.valueOf("red"));
                        txtUnit.requestFocus();
                    }
                } else {
                    txtTotalPrice.setFocusColor(Paint.valueOf("red"));
                    txtTotalPrice.requestFocus();
                }
            } else {
                txtQty.setFocusColor(Paint.valueOf("red"));
                txtQty.requestFocus();
            }
        } else {
            txtDescription.setFocusColor(Paint.valueOf("red"));
            txtDescription.requestFocus();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " + lblPurchaseID.getText() + " " + txtDescription.getText() + "?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Do you want to delete?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            ButtonType btnYes = new ButtonType("Yes");
            ButtonType btnNo = new ButtonType("No");
            if (buttonType.get() == ButtonType.YES) {
                if (bo.delete(lblPurchaseID.getText())) {
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

    public void btnNewPurchaseOnAction(ActionEvent actionEvent) {
        setDefault();
        loadLastId();
        panePurchaseEdit.setDisable(false);
        btnDelete.setVisible(false);
        loadTable();
    }

    public void btnPrintAllPurchasesOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/sliit/TropicoMushrooms/reports/PurchaseReport.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint jp = JasperFillManager.fillReport(jr,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,false);
//            JasperPrintManager.printReport(jp,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dpSearchDateOnAction(ActionEvent actionEvent) {
        loadPurchaseDataToTableByDate(Date.valueOf(dpSearchDate.getValue()));
        imgCancel.setVisible(true);
    }

    public static void setUserId(String userId) {
        EmployeePurchaseFormController.userId = userId;
    }

    private void setDefault() {
        panePurchaseEdit.setDisable(true);
        dpSearchDate.setVisible(true);
        dpSearchDate.setDisable(false);
        dpSearchDate.setValue(LocalDate.now());
        btnNewPurchase.setDisable(false);
        btnAdd.setText("Add");
        lblCompleteAll.setVisible(false);
        btnDelete.setVisible(true);
        lblPurchaseID.setText("");
        lblUserID.setText(userId);
        lblDate.setText(String.valueOf(LocalDate.now()));
        txtDescription.clear();
        txtQty.clear();
        txtUnit.clear();
        txtTotalPrice.clear();
        imgCancel.setVisible(false);

        txtDescription.setFocusColor(Paint.valueOf("black"));
        txtQty.setFocusColor(Paint.valueOf("black"));
        txtUnit.setFocusColor(Paint.valueOf("black"));
        txtTotalPrice.setFocusColor(Paint.valueOf("black"));
    }

    private void loadLastId() {
        try {
            String lastCode = bo.getPurchaseLastId();
            if (null != lastCode) {
//                lastCode = lastCode.split("[A-Z]")[1];
                lastCode = "" + (Integer.parseInt(lastCode) + 001);
                lblPurchaseID.setText(lastCode);
            } else {
                lblPurchaseID.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTableCols() {
        colPurchaseID.setCellValueFactory(new PropertyValueFactory<>("purchaseId"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    private void loadTable() {
        try {
            ArrayList<PurchaseDTO> allPurchasesDetails = bo.getAll();
            ObservableList<PurchaseTM> list = FXCollections.observableArrayList();

            for (PurchaseDTO dto : allPurchasesDetails) {
                list.add(new PurchaseTM(
                        dto.getPurchaseId(),
                        dto.getUserId(),
                        dto.getDate(),
                        dto.getDescription(),
                        dto.getQty(),
                        dto.getUnit(),
                        dto.getTotalPrice()
                ));
            }
            tblPurchase.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean addPurchase() {
        try {
            return bo.save(new PurchaseDTO(
                    Integer.parseInt(lblPurchaseID.getText()),
                    Integer.parseInt(lblUserID.getText()),
                    Date.valueOf(lblDate.getText()),
                    txtDescription.getText(),
                    Double.parseDouble(txtQty.getText()),
                    txtUnit.getText(),
                    Double.parseDouble(txtTotalPrice.getText())
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean updatePurchase() {
        try {
            return bo.update(new PurchaseDTO(
                    Integer.parseInt(lblPurchaseID.getText()),
                    Integer.parseInt(lblUserID.getText()),
                    Date.valueOf(lblDate.getText()),
                    txtDescription.getText(),
                    Double.parseDouble(txtQty.getText()),
                    txtUnit.getText(),
                    Double.parseDouble(txtTotalPrice.getText())
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void loadPurchaseDataToForm(String id) {
        try {
            PurchaseDTO dto = bo.search(id);
            lblPurchaseID.setText(String.valueOf(dto.getPurchaseId()));
            lblUserID.setText(String.valueOf(dto.getUserId()));
            lblDate.setText(String.valueOf(dto.getDate()));
            txtDescription.setText(dto.getDescription());
            txtQty.setText(String.valueOf(dto.getQty()));
            txtUnit.setText(dto.getUnit());
            txtTotalPrice.setText(String.valueOf(dto.getTotalPrice()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPurchaseDataToTableByDate(Date date) {
        try {
            ArrayList<PurchaseDTO> allPurchasesDetails = bo.getPurchaseByDate(date);
            ObservableList<PurchaseTM> list = FXCollections.observableArrayList();

            for (PurchaseDTO dto : allPurchasesDetails) {
                list.add(new PurchaseTM(
                        dto.getPurchaseId(),
                        dto.getUserId(),
                        dto.getDate(),
                        dto.getDescription(),
                        dto.getQty(),
                        dto.getUnit(),
                        dto.getTotalPrice()
                ));
            }
            tblPurchase.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void imgCancelOnMouseClicked(MouseEvent mouseEvent) {
        loadTable();
        imgCancel.setVisible(false);
    }
}
