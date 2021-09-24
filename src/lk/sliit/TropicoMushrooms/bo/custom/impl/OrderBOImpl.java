package lk.sliit.TropicoMushrooms.bo.custom.impl;

import lk.sliit.TropicoMushrooms.bo.custom.OrderBO;
import lk.sliit.TropicoMushrooms.dao.DAOFactory;
import lk.sliit.TropicoMushrooms.dao.custom.*;
import lk.sliit.TropicoMushrooms.db.DBConnection;
import lk.sliit.TropicoMushrooms.dto.OrderDTO;
import lk.sliit.TropicoMushrooms.dto.OrderDetailDTO;
import lk.sliit.TropicoMushrooms.entity.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    OrdersDAO ordersDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERS);
    OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    ReturnsDAO returnsDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RETURNS);

    @Override
    public String getLastOrderId() throws Exception {
        return ordersDAO.getLastId();
    }

    @Override
    public ArrayList<OrderDTO> getAllCustomers() throws Exception {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<OrderDTO> allCustomers = new ArrayList<>();
        for (Customer c : all) {
            OrderDTO dto = new OrderDTO(
                    c.getCustomerId(),
                    c.getName(),
                    c.getAddress(),
                    c.getMobile(),
                    c.getNic()
            );
            allCustomers.add(dto);
        }
        return allCustomers;
    }

    @Override
    public ArrayList<OrderDTO> getAllItems() throws Exception {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<OrderDTO> allItems = new ArrayList<>();
        for (Item i : all) {
            OrderDTO dto = new OrderDTO(
                    i.getItemCode(),
                    i.getDescription(),
                    i.getPrice(),
                    i.getQtyOnHand(),
                    i.getUnit()
            );
            allItems.add(dto);
        }
        return allItems;
    }

    @Override
    public boolean saveOrder(OrderDTO dto) throws Exception {
        boolean temp = false;
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            if (ordersDAO.save(new Orders(dto.getOrderId(), Date.valueOf(String.valueOf(dto.getDate())), dto.getCustomerId(), dto.getUserId()))) {
                for (OrderDetailDTO ord : dto.getOrderDetails()) {
                    if (!orderDetailDAO.save(new OrderDetail(ord.getOrderId(), ord.getItemCode(), ord.getQty(), ord.getUnit(), ord.getUnitPrice()))) {
                        connection.rollback();
                        return false;
                    }
                }
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public OrderDTO getCustomerFromNic(String nic) throws Exception {
        Customer c = customerDAO.searchByNIC(nic);
        return new OrderDTO(
                c.getCustomerId(),
                c.getName(),
                c.getAddress(),
                c.getMobile(),
                c.getNic()
        );
    }

    @Override
    public OrderDTO getCustomerFromID(String id) throws Exception {
        Customer c = customerDAO.search(id);
        return new OrderDTO(
                c.getCustomerId(),
                c.getName(),
                c.getAddress(),
                c.getMobile(),
                c.getNic()
        );
    }

    @Override
    public OrderDTO getItemFromId(String id) throws Exception {
        Item i = itemDAO.search(id);
        return new OrderDTO(
                i.getItemCode(),
                i.getDescription(),
                i.getPrice(),
                i.getQtyOnHand(),
                i.getUnit()
        );
    }

    @Override
    public OrderDTO getItemFromDescription(String description) throws Exception {
        Item i = itemDAO.searchByDescription(description);
        return new OrderDTO(
                i.getItemCode(),
                i.getDescription(),
                i.getPrice(),
                i.getQtyOnHand(),
                i.getUnit()
        );
    }

    @Override
    public String getLastReturnOrderId() throws Exception {
        return returnsDAO.getLastId();
    }

    @Override
    public ArrayList<OrderDetailDTO> searchOrderDetails(String orderID) throws Exception {
        ArrayList<OrderDetail> orderDetails = orderDetailDAO.searchOrderDetails(orderID);
        ArrayList<OrderDetailDTO> all = new ArrayList<>();
        for (OrderDetail o : orderDetails) {
            OrderDetailDTO dto = new OrderDetailDTO(
                    o.getItemCode(),
                    getItemFromItemCode(String.valueOf(o.getItemCode())),
                    o.getUnitPrice(),
                    o.getQty(),
                    o.getUnit(),
                    o.getQty() * o.getUnitPrice()
            );
            all.add(dto);
        }
        return all;
    }

    @Override
    public OrderDTO searchOrder(String orderID) throws Exception {
        Orders o = ordersDAO.search(orderID);
        return new OrderDTO(
                o.getOrderId(),
                o.getDate(),
                o.getCustomerId(),
                o.getUserId()
        );
    }

    @Override
    public String getItemFromItemCode(String itemCode) throws Exception {
        return itemDAO.getItemFromItemCode(itemCode);
    }

    @Override
    public boolean addReturn(OrderDTO dto) throws Exception {
        return returnsDAO.save(new Returns(
                dto.getReturnId(),
                dto.getOrderId(),
                dto.getUserId(),
                dto.getReason()
        ));
    }
}
