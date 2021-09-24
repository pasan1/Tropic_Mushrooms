package lk.sliit.TropicoMushrooms.dao.custom;

import lk.sliit.TropicoMushrooms.dao.CrudDAO;
import lk.sliit.TropicoMushrooms.entity.Orders;

public interface OrdersDAO extends CrudDAO<Orders, String> {
    public String getLastId() throws Exception;

    public int getCount() throws Exception;
}
