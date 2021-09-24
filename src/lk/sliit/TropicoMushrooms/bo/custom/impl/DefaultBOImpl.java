package lk.sliit.TropicoMushrooms.bo.custom.impl;

import lk.sliit.TropicoMushrooms.bo.custom.DefaultBO;
import lk.sliit.TropicoMushrooms.dao.DAOFactory;
import lk.sliit.TropicoMushrooms.dao.custom.*;
import lk.sliit.TropicoMushrooms.dto.ChartDTO;
import lk.sliit.TropicoMushrooms.entity.CustomEntity;

import java.util.ArrayList;
import java.util.Date;

public class DefaultBOImpl implements DefaultBO {

    CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    OrdersDAO ordersDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERS);
    PurchaseDAO purchaseDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PURCHASE);
    OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public double getCountForCustomer() throws Exception {
        return customerDAO.getCount();
    }

    @Override
    public double getCountForItems() throws Exception {
        return itemDAO.getCount();
    }

    @Override
    public double getCountForOrders() throws Exception {
        return ordersDAO.getCount();
    }

    @Override
    public double getCountForPurchases() throws Exception {
        return purchaseDAO.getCount();
    }

    @Override
    public double getTodayTotalSale(Date today) throws Exception {
        return queryDAO.getTodayTotalSale(java.sql.Date.valueOf(String.valueOf(today)));
    }

    @Override
    public ArrayList<ChartDTO> barChart() throws Exception {
        ArrayList<CustomEntity> populate = queryDAO.chart();
        ArrayList<ChartDTO> amount = new ArrayList<>();
        for (CustomEntity chart : populate) {
            amount.add(new ChartDTO(String.valueOf(chart.getDate()), chart.getAmount()));
        }
        return amount;
    }
}
