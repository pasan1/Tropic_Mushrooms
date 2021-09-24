package lk.sliit.TropicoMushrooms.dao.custom;

import lk.sliit.TropicoMushrooms.dao.CrudDAO;
import lk.sliit.TropicoMushrooms.entity.OrderDetail;

import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrderDetail, String> {
    public ArrayList<OrderDetail> searchOrderDetails(String orderID) throws Exception;

    public int getCount() throws Exception;
}
