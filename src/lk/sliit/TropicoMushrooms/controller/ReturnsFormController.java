package lk.sliit.TropicoMushrooms.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.sliit.TropicoMushrooms.bo.BOFactory;
import lk.sliit.TropicoMushrooms.bo.custom.OrderBO;
import lk.sliit.TropicoMushrooms.dto.OrderDTO;
import lk.sliit.TropicoMushrooms.dto.OrderDetailDTO;
import lk.sliit.TropicoMushrooms.tm.OrderTM;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReturnsFormController {
    public AnchorPane window;
    public AnchorPane paneDetail;
    public Text lblDate;
    public Text lblUserID;
    public Text lblReturnID;
    public AnchorPane paneOrderDetails;
    public TableView<OrderTM> tblItemDetail;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colPrice;
    public TableColumn colQty;
    public TableColumn colUnit;
    public TableColumn colTotal;
    public AnchorPane paneSearch;
    public AnchorPane paneCustomer;
    public Text lblCustomerName;
    public Text lblCustomerID;
    public Text lblCustomerNIC;
    public AnchorPane paneReason;
    public TextArea txtReason;
    public Text lblOrderID;
    public JFXButton btnReturn;
    public TextField txtSearchOrder;
    public JFXButton btnSearchOrder;
    public Text lblOrderDate;

    OrderBO bo = BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);

    private static String userId;

    public void initialize() {
        loadLastId();
        setTableCols();
        setDefault();
        txtSearchOrder.requestFocus();
    }

    public void txtSearchOrderOnAction(ActionEvent actionEvent) throws Exception {
        if (!txtSearchOrder.getText().isEmpty()) {
            loadOrderDetails(txtSearchOrder.getText());
            txtSearchOrder.clear();
        } else {
            txtSearchOrder.requestFocus();
        }
    }

    public void btnSearchOrderOnAction(ActionEvent actionEvent) throws Exception {
        if (!txtSearchOrder.getText().isEmpty()) {
            loadOrderDetails(txtSearchOrder.getText());
            txtSearchOrder.clear();
        } else {
            txtSearchOrder.requestFocus();
        }
    }

    public void txtReasonOnKeyPressed(KeyEvent keyEvent) {
        if (!txtReason.getText().isEmpty()){
            txtReason.setStyle("-fx-border-color: black");
        } else {
            txtReason.setStyle("-fx-border-color: red");
        }
    }

    public void btnReturnOnAction(ActionEvent actionEvent) throws Exception {
        if (!txtReason.getText().isEmpty()){
            if (addReturn()) {
                new Alert(Alert.AlertType.INFORMATION, "SuccessFully Record Added").show();
                loadLastId();
                setDefault();
            } else {
                new Alert(Alert.AlertType.WARNING, "Record Adding Failed").show();
            }
        } else {
            txtReason.requestFocus();
            txtReason.setStyle("-fx-border-color: red");
        }
    }

    private boolean addReturn() throws Exception {
        return bo.addReturn(new OrderDTO(
                Integer.parseInt(lblReturnID.getText()),
                Integer.parseInt(lblOrderID.getText()),
                Integer.parseInt(lblUserID.getText()),
                txtReason.getText()
        ));
    }

    private void loadOrderDetails(String orderID) throws Exception {
        txtReason.requestFocus();
        OrderDTO dto = bo.searchOrder(orderID);
        lblOrderID.setText(String.valueOf(dto.getOrderId()));
        lblOrderDate.setText(String.valueOf(dto.getDate()));
        lblCustomerID.setText(String.valueOf(dto.getCustomerId()));
        OrderDTO customer = bo.getCustomerFromID(String.valueOf(dto.getCustomerId()));
        lblCustomerName.setText(customer.getName());
        lblCustomerNIC.setText(customer.getNic());
        loadTable(orderID);
    }

    private void loadTable(String orderID) throws Exception {
        ArrayList<OrderDetailDTO> details = bo.searchOrderDetails(orderID);

        ObservableList<OrderTM> list = FXCollections.observableArrayList();

        for (OrderDetailDTO dto : details) {
            list.add(new OrderTM(
                    String.valueOf(dto.getItemCode()),
                    dto.getItemName(),
                    dto.getUnitPrice(),
                    dto.getQty(),
                    dto.getUnit(),
                    dto.getTotal()
            ));
        }

        tblItemDetail.setItems(list);
    }

    public static void setUserId(String userId) {
        ReturnsFormController.userId = userId;
    }

    private void loadLastId() {
        try {
            String lastCode = bo.getLastReturnOrderId();
            if (null != lastCode) {
//                lastCode = lastCode.split("[A-Z]")[1];
                lastCode = "" + (Integer.parseInt(lastCode) + 001);
                lblReturnID.setText(lastCode);
            } else {
                lblReturnID.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTableCols() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void setDefault() {
        loadLastId();
        txtSearchOrder.setText("");
        lblUserID.setText(userId);
        lblDate.setText(String.valueOf(LocalDate.now()));

        lblCustomerID.setText("");
        lblCustomerName.setText("");
        lblCustomerNIC.setText("");

        lblOrderID.setText("000");
        lblOrderDate.setText("");
        txtReason.setText("");

        txtSearchOrder.setStyle("-fx-border-color: black");
        txtReason.setStyle("-fx-border-color: black");
    }
}
