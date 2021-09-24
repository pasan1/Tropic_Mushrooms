package lk.sliit.TropicoMushrooms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.sliit.TropicoMushrooms.api.SendEmail;
import lk.sliit.TropicoMushrooms.bo.BOFactory;
import lk.sliit.TropicoMushrooms.bo.custom.OrderBO;
import lk.sliit.TropicoMushrooms.db.DBConnection;
import lk.sliit.TropicoMushrooms.dto.OrderDTO;
import lk.sliit.TropicoMushrooms.dto.OrderDetailDTO;
import lk.sliit.TropicoMushrooms.tm.OrderTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class OrderFormController {
    public AnchorPane window;
    public AnchorPane paneDetail;
    public Text lblOrderID;
    public Text lblDate;
    public Text lblUserID;
    public AnchorPane paneCustomerDetail;
    public JFXComboBox<String> cmbCustomerNIC;
    public JFXTextField txtCustomerID;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerNIC;
    public JFXTextField txtCustomerMobile;
    public AnchorPane paneItemDetail;
    public JFXComboBox<String> cmbItemCode;
    public JFXTextField txtItemCode;
    public JFXTextField txtItemDescription;
    public JFXTextField txtItemQtyOnHand;
    public JFXTextField txtItemPrice;
    public JFXComboBox<String> cmbItemDescription;
    public TextField txtQty;
    public JFXButton btnQtyAdd;
    public JFXTextField txtItemUnit;
    public AnchorPane paneTableDetail;
    public TableView<OrderTM> tblItemDetail;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colPrice;
    public TableColumn colQty;
    public TableColumn colUnit;
    public TableColumn colTotal;
    public Text lblItemCount;
    public AnchorPane paneTotDetail;
    public TextField txtTotal;
    public TextField txtDiscount;
    public TextField txtFullTotal;
    public AnchorPane panePaymentDetail;
    public JFXRadioButton rdBtnCash;
    public JFXRadioButton rdBtnCard;
    public AnchorPane paneCash;
    public JFXButton btnCashPay;
    public JFXButton btnCashCancel;
    public TextField txtCashBalance;
    public TextField txtCashAmount;
    public AnchorPane paneCard;
    public JFXButton btnCardPay;
    public JFXButton btnCardCancel;

    private OrderBO bo = BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);

    private static String userId;
    private ObservableList<OrderTM> tblList = FXCollections.observableArrayList();

    public void initialize() {
        setDefault();
        setTableCols();
        loadLastId();
        refreshTable();
        cmbCustomerNIC.requestFocus();
        loadCustomerNICsToCmd();
        loadItemCodesToCmd();
        loadItemDescriptionsToCmd();
    }

    private void loadItemDescriptionsToCmd() {
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            ArrayList<OrderDTO> allItems = bo.getAllItems();
            for (OrderDTO dto : allItems) {
                list.add(dto.getDescription());
            }
            cmbItemDescription.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadItemCodesToCmd() {
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            ArrayList<OrderDTO> allItems = bo.getAllItems();
            for (OrderDTO dto : allItems) {
                list.add(String.valueOf(dto.getItemCode()));
            }
            cmbItemCode.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerNICsToCmd() {
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            ArrayList<OrderDTO> allCustomers = bo.getAllCustomers();
            for (OrderDTO dto : allCustomers) {
                list.add(dto.getNic());
            }
            cmbCustomerNIC.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbCustomerNICOnAction(ActionEvent actionEvent) {
        try {
            OrderDTO dto = bo.getCustomerFromNic(cmbCustomerNIC.getValue());
            txtCustomerID.setText(String.valueOf(dto.getCustomerId()));
            txtCustomerNIC.setText(dto.getNic());
            txtCustomerName.setText(dto.getName());
            txtCustomerMobile.setText(dto.getMobile());

            cmbItemCode.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbItemCodeOnAction(ActionEvent actionEvent) {
        try {
            OrderDTO dto = bo.getItemFromId(cmbItemCode.getValue());
            txtItemCode.setText(String.valueOf(dto.getItemCode()));
            txtItemDescription.setText(dto.getDescription());
            txtItemQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
            txtItemUnit.setText(dto.getUnit());
            txtItemPrice.setText(String.valueOf(dto.getPrice()));

            if (dto.getQtyOnHand() > 0) {
                txtQty.setDisable(false);
                btnQtyAdd.setDisable(false);
                txtQty.requestFocus();
            } else {
                txtQty.setDisable(true);
                btnQtyAdd.setDisable(true);
                cmbItemCode.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbItemDescriptionOnAction(ActionEvent actionEvent) {
        try {
            OrderDTO dto = bo.getItemFromDescription(cmbItemDescription.getValue());
            txtItemCode.setText(String.valueOf(dto.getItemCode()));
            txtItemDescription.setText(dto.getDescription());
            txtItemQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
            txtItemUnit.setText(dto.getUnit());
            txtItemPrice.setText(String.valueOf(dto.getPrice()));

            if (dto.getQtyOnHand() > 0) {
                txtQty.setDisable(false);
                btnQtyAdd.setDisable(false);
                txtQty.requestFocus();
            } else {
                txtQty.setDisable(true);
                btnQtyAdd.setDisable(true);
                cmbItemDescription.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        addQty();
        clearItemDetails();
        cmbItemCode.requestFocus();
    }

    public void btnQtyAddOnAction(ActionEvent actionEvent) {
        addQty();
        clearItemDetails();
        cmbItemCode.requestFocus();
    }

    private void addQty() {
        if (getTxtQty() > 0) {
            addToTable(new OrderTM(
                    txtItemCode.getText(),
                    txtItemDescription.getText(),
                    Double.valueOf(txtItemPrice.getText()),
                    getTxtQty(),
                    txtItemUnit.getText(),
                    Double.valueOf(txtItemPrice.getText()) * Double.valueOf(getTxtQty())
            ));
        }
    }

    private void clearItemDetails() {
        txtItemCode.clear();
        txtItemDescription.clear();
        txtItemQtyOnHand.clear();
        txtItemUnit.clear();
        txtItemPrice.clear();
        txtQty.clear();
    }

    private double getTxtQty() {
        if (Pattern.compile("^[0-9.]+$").matcher(txtQty.getText()).matches()) {
            return Double.valueOf(txtQty.getText());
        } else {
            txtQty.setStyle("-fx-border-color: red");
            txtQty.requestFocus();
            return 0;
        }
    }

    public void txtDiscountOnAction(ActionEvent actionEvent) {
        if (!txtDiscount.getText().isEmpty()) {
            txtFullTotal.setText(String.valueOf(calDiscount(Double.parseDouble(txtDiscount.getText()))));
            rdBtnCash.requestFocus();
            txtDiscount.setStyle("-fx-border-color: black");
        } else {
            txtDiscount.requestFocus();
            txtDiscount.setStyle("-fx-border-color: red");
        }
    }

    private Double calDiscount(double discount) {
        double tot = Double.parseDouble(txtTotal.getText());
        double amount = tot - (tot * discount / 100);
        return amount;
    }

    public void rdBtnCashOnAction(ActionEvent actionEvent) {
        if (rdBtnCash.isSelected()) {
            txtCashAmount.requestFocus();
            rdBtnCard.setSelected(false);
            paneCash.setVisible(true);
            paneCard.setVisible(false);
        }
    }

    public void rdBtnCardOnAction(ActionEvent actionEvent) {
        if (rdBtnCard.isSelected()) {
            btnCardPay.requestFocus();
            rdBtnCash.setSelected(false);
            paneCard.setVisible(true);
            paneCash.setVisible(false);
        }
    }

    public void btnCashPayOnAction(ActionEvent actionEvent) {
        doPayment();
    }

    public void btnCashCancelOnAction(ActionEvent actionEvent) {
        setDefault();
        tblList.clear();
        refreshTable();
    }

    public void txtCashAmountOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtCashAmount.requestFocus();
        }
    }

    private void doPayment() {
        if (!txtCustomerID.getText().isEmpty()) {
            if (Pattern.compile("^[0-9.]+$").matcher(txtDiscount.getText()).matches()) {
                if (Pattern.compile("^[0-9.]+$").matcher(txtCashAmount.getText()).matches()) {
                    if (checkBalance()){
                        payByCash();
                    }
                } else {
                    txtCashAmount.requestFocus();
                }
            } else {
                txtDiscount.requestFocus();
            }
        } else {
            cmbItemCode.requestFocus();
        }
    }

    private boolean checkBalance() {
        double balance =Double.parseDouble(txtFullTotal.getText()) - Double.parseDouble(txtCashAmount.getText());
        txtCashBalance.setText(String.valueOf(balance));
        if (balance>0){
            txtCashAmount.setStyle("-fx-border-color: black");
            return true;
        } else {
            txtCashAmount.requestFocus();
            txtCashAmount.setStyle("-fx-border-color: red");
            return false;
        }
    }

    private void payByCash() {
        if (saveOrder()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to print bill?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Do you want to Print?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            ButtonType btnYes = new ButtonType("Yes");
            ButtonType btnNo = new ButtonType("No");
            if (buttonType.get() == ButtonType.YES) {
                printBill(txtCashAmount.getText(), "Cash", txtCashBalance.getText());
            } else if (buttonType.get() == ButtonType.NO) {
                alert.close();
            }
            sendEmail();
            setDefault();
        } else {
            new Alert(Alert.AlertType.WARNING, "Payment Failed").show();
        }
    }

    private boolean saveOrder() {
        try {
            ArrayList<OrderDetailDTO> ord = new ArrayList<>();

            for (OrderTM tm : tblList) {
                ord.add(new OrderDetailDTO(
                        Integer.parseInt(lblOrderID.getText()),
                        Integer.parseInt(tm.getItemCode()),
                        tm.getQty(),
                        tm.getUnit(),
                        tm.getPrice()
                ));
            }

            return bo.saveOrder(new OrderDTO(
                    Integer.parseInt(lblOrderID.getText()),
                    Date.valueOf(lblDate.getText()),
                    Integer.parseInt(txtCustomerID.getText()),
                    Integer.parseInt(lblUserID.getText()),
                    ord
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void btnCardPayOnAction(ActionEvent actionEvent) {
        if (!txtCustomerID.getText().isEmpty()) {
            if (Pattern.compile("^[0-9.]+$").matcher(txtDiscount.getText()).matches()) {
                payByCash();
            } else {
                txtDiscount.requestFocus();
            }
        } else {
            cmbItemCode.requestFocus();
        }
    }

    public void btnCardCancelOnAction(ActionEvent actionEvent) {
        setDefault();
        tblList.clear();
        refreshTable();
    }

    public static void setUserId(String userId) {
        OrderFormController.userId = userId;
    }

    private void loadLastId() {
        try {
            String lastCode = bo.getLastOrderId();
            if (null != lastCode) {
//                lastCode = lastCode.split("[A-Z]")[1];
                lastCode = "" + (Integer.parseInt(lastCode) + 001);
                lblOrderID.setText(lastCode);
            } else {
                lblOrderID.setText("1");
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

    private void refreshTable() {
        tblItemDetail.setItems(tblList);
    }

    private void addToTable(OrderTM tm) {
        for (OrderTM orderTM : tblList) {
            if (orderTM.getItemCode().equals(tm.getItemCode())) {
                double qtyTemp = orderTM.getQty() + tm.getQty();
                System.out.println(qtyTemp);
                tm.setQty(qtyTemp);

                double totTemp = qtyTemp * tm.getPrice();
                System.out.println(totTemp);
                tm.setTotal(totTemp);

                tblList.remove(orderTM);
            }
        }
        tblList.add(tm);
        refreshTable();
        loadTotal();
    }

    private void loadTotal() {
        double tot = 0;
        int count = 0;
        for (OrderTM tm : tblList) {
            tot = tot + tm.getTotal();
            count++;
        }
        txtTotal.setText(String.valueOf(tot));
        lblItemCount.setText(String.valueOf(count));
    }

    private void setDefault() {
        lblDate.setText(String.valueOf(LocalDate.now()));
        txtQty.setStyle("-fx-border-color: black");
        txtTotal.setStyle("-fx-border-color: black");
        txtFullTotal.setStyle("-fx-border-color: black");
        txtDiscount.setStyle("-fx-border-color: black");
        txtCashAmount.setStyle("-fx-border-color: black");
        txtCashBalance.setStyle("-fx-border-color: black");
        rdBtnCash.setSelected(true);
        rdBtnCard.setSelected(false);
        paneCash.setVisible(true);
        paneCard.setVisible(false);

        lblUserID.setText(userId);

        txtQty.setDisable(false);
        btnQtyAdd.setDisable(false);

        txtDiscount.setText("0");
    }

    public void windowOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            txtDiscount.requestFocus();
        }
    }

    public void sendEmail() {

        String receiverEmail = "dmtbdissanayake@gmail.com";
        String subject = "Payment was DONE";
        String body = "Rs . " + txtFullTotal.getText() + " payment paid by " + txtCustomerName.getText() + "(" + txtCustomerNIC.getText() + ") in now.";

//        ****************************************
//        **********READ BEFORE USE***************
//        ****************************************
//        There are 3 parameters for SendEmail
//
//        1. receiverEmailID:
//        2. Mail subject
//        3. Mail Body
//
//        To receive the mail, first you have to go to security in your gmail
//        Then go to less secure app permission
//        then turn on access permission
//
//        If you did not give the permissions, software will give you an exception
//
//        *****************************************
//        *.*.*.*.*.*.*.*.*.*END*.*.*.*.*.*.*.*.*.*
//        *****************************************


        new SendEmail(receiverEmail, subject, body);
    }

    private void printBill(String amount, String method, String balance) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/sliit/TropicoMushrooms/reports/InvoiceForTropico.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);

            String s = "Rs. ";

            HashMap hs = new HashMap();
            hs.put("BillOrderID", lblOrderID.getText());
            hs.put("CustomerName", txtCustomerName.getText());
            hs.put("CustomerMobileNumber", txtCustomerMobile.getText());
            hs.put("TotalAmount", s + txtFullTotal.getText());
            hs.put("CashAmount", s + amount);
            hs.put("CashMethod", method);
            hs.put("CashBalance", s + balance);

            JasperPrint jp = JasperFillManager.fillReport(jr, hs, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp, false);
//            JasperPrintManager.printReport(jp,true);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void rdBtnCashOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtCashAmount.requestFocus();
        }
    }

    public void rdBtnCardOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            btnCardPay.requestFocus();
        }
    }
}
