package lk.sliit.TropicoMushrooms.controller.employee;

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
import lk.sliit.TropicoMushrooms.bo.custom.ItemBO;
import lk.sliit.TropicoMushrooms.db.DBConnection;
import lk.sliit.TropicoMushrooms.dto.ItemDTO;
import lk.sliit.TropicoMushrooms.tm.ItemTM;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class EmployeeItemFormController {
    public AnchorPane window;
    public JFXButton btnSearchItem;
    public TableView<ItemTM> tblItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colUnit;
    public TextField txtSearchItem;
    public AnchorPane paneItemEdit;
    public JFXTextField txtDescriptions;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnit;
    public JFXTextField txtPrice;
    public Text lblItemCode;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public Text lblCompleteAll;
    public JFXButton btnNewItem;
    public JFXButton btnPrintAllItems;

    private ItemBO bo = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);

    public void initialize() {
        setDefault();
        setTableCols();
        loadLastId();
        loadTable();
        txtSearchItem.requestFocus();

        txtSearchItem.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ArrayList<ItemDTO> allItemsDetails = bo.getItemByDescription(newValue);
                ObservableList<ItemTM> list = FXCollections.observableArrayList();

                for (ItemDTO dto : allItemsDetails) {
                    list.add(new ItemTM(
                            dto.getItemCode(),
                            dto.getDescription(),
                            dto.getPrice(),
                            dto.getQtyOnHand(),
                            dto.getUnit()
                    ));
                }
                tblItem.setItems(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDefault();
            if (null != newValue)
                loadItemDataToForm(String.valueOf(newValue.getItemCode()));
            paneItemEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        });
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[A-z0-9 ,.]{2,}$").matcher(txtSearchItem.getText()).matches()) {
            loadItemDataToFormByDescription(txtSearchItem.getText());
            paneItemEdit.setDisable(false);
            btnAdd.setText("Update");
            btnDelete.setVisible(true);
        } else {
            txtSearchItem.setStyle("-fx-border-color: red");
            txtSearchItem.requestFocus();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[A-z0-9 ,.]{2,}$").matcher(txtDescriptions.getText()).matches()) {
            if (Pattern.compile("^[0-9.]{2,}$").matcher(txtPrice.getText()).matches()) {
                if (Pattern.compile("^[0-9.]+$").matcher(txtQtyOnHand.getText()).matches()) {
                    if (Pattern.compile("^[A-z0-9]{1,4}$").matcher(txtUnit.getText()).matches()) {
                        //-----1
                        if (btnAdd.getText().equals("Add")) {
                            if (addItem()) {
                                new Alert(Alert.AlertType.INFORMATION, "SuccessFully Added").show();
                                loadLastId();
                                loadTable();
                                setDefault();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Adding Failed").show();
                            }
                        } else if (btnAdd.getText().equals("Update")) {
                            if (updateItem()) {
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
                    txtQtyOnHand.setFocusColor(Paint.valueOf("red"));
                    txtQtyOnHand.requestFocus();
                }
            } else {
                txtPrice.setFocusColor(Paint.valueOf("red"));
                txtPrice.requestFocus();
            }
        } else {
            txtDescriptions.setFocusColor(Paint.valueOf("red"));
            txtDescriptions.requestFocus();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " + lblItemCode.getText() + " " + txtDescriptions.getText() + "?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Do you want to delete?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            ButtonType btnYes = new ButtonType("Yes");
            ButtonType btnNo = new ButtonType("No");
            if (buttonType.get() == ButtonType.YES) {
                if (bo.delete(lblItemCode.getText())) {
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

    public void btnNewItemOnAction(ActionEvent actionEvent) {
        setDefault();
        loadLastId();
        paneItemEdit.setDisable(false);
        btnDelete.setVisible(false);
    }

    public void btnPrintAllItemsOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/sliit/TropicoMushrooms/reports/ItemReport.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint jp = JasperFillManager.fillReport(jr,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,false);
//            JasperPrintManager.printReport(jp,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDefault() {
        paneItemEdit.setDisable(true);
        btnSearchItem.setVisible(true);
        btnSearchItem.setDisable(false);
        txtSearchItem.setDisable(false);
        txtSearchItem.clear();
        txtSearchItem.setStyle("-fx-border-color: black");
        btnNewItem.setDisable(false);
        btnAdd.setText("Add");
        lblCompleteAll.setVisible(false);
        btnDelete.setVisible(true);
        lblItemCode.setText("");
        txtDescriptions.clear();
        txtPrice.clear();
        txtQtyOnHand.clear();
        txtUnit.clear();

        txtDescriptions.setFocusColor(Paint.valueOf("black"));
        txtPrice.setFocusColor(Paint.valueOf("black"));
        txtQtyOnHand.setFocusColor(Paint.valueOf("black"));
        txtUnit.setFocusColor(Paint.valueOf("black"));
    }

    private void setTableCols() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
    }

    private void loadTable() {
        try {
            ArrayList<ItemDTO> allItemsDetails = bo.getAll();
            ObservableList<ItemTM> list = FXCollections.observableArrayList();

            for (ItemDTO dto : allItemsDetails) {
                list.add(new ItemTM(
                        dto.getItemCode(),
                        dto.getDescription(),
                        dto.getPrice(),
                        dto.getQtyOnHand(),
                        dto.getUnit()
                ));
            }
            tblItem.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadItemDataToForm(String id) {
        try {
            ItemDTO dto = bo.search(id);
            lblItemCode.setText(String.valueOf(dto.getItemCode()));
            txtDescriptions.setText(dto.getDescription());
            txtPrice.setText(String.valueOf(dto.getPrice()));
            txtQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
            txtUnit.setText(dto.getUnit());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadItemDataToFormByDescription(String description) {
        try {
            ItemDTO dto = bo.searchByDescription(description);
            lblItemCode.setText(String.valueOf(dto.getItemCode()));
            txtDescriptions.setText(dto.getDescription());
            txtPrice.setText(String.valueOf(dto.getPrice()));
            txtQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
            txtUnit.setText(dto.getUnit());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLastId() {
        try {
            String lastCode = bo.getItemLastId();
            if (null != lastCode) {
//                lastCode = lastCode.split("[A-Z]")[1];
                lastCode = "" + (Integer.parseInt(lastCode) + 001);
                lblItemCode.setText(lastCode);
            } else {
                lblItemCode.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean addItem() {
        try {
            return bo.save(new ItemDTO(
                    Integer.parseInt(lblItemCode.getText()),
                    txtDescriptions.getText(),
                    Integer.parseInt(txtPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText()),
                    txtUnit.getText()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean updateItem() {
        try {
            return bo.update(new ItemDTO(
                    Integer.parseInt(lblItemCode.getText()),
                    txtDescriptions.getText(),
                    Integer.parseInt(txtPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText()),
                    txtUnit.getText()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
