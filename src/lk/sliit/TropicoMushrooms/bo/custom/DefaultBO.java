package lk.sliit.TropicoMushrooms.bo.custom;

import lk.sliit.TropicoMushrooms.bo.SuperBO;
import lk.sliit.TropicoMushrooms.dto.ChartDTO;

import java.util.ArrayList;
import java.util.Date;

public interface DefaultBO extends SuperBO {
    public double getCountForCustomer() throws Exception;

    public double getCountForItems() throws Exception;

    public double getCountForOrders() throws Exception;

    public double getCountForPurchases() throws Exception;

    public double getTodayTotalSale(Date today) throws Exception;

    public ArrayList<ChartDTO> barChart() throws Exception;
}
