package lk.sliit.TropicoMushrooms.dao.custom;

import lk.sliit.TropicoMushrooms.dao.CrudDAO;
import lk.sliit.TropicoMushrooms.entity.Purchase;

import java.sql.Date;
import java.util.ArrayList;

public interface PurchaseDAO extends CrudDAO<Purchase, String> {
    public String getLastId() throws Exception;

    public ArrayList<Purchase> getItemByDate(Date date) throws Exception;

    public int getCount() throws Exception;
}
