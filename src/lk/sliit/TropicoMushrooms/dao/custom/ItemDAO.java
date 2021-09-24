package lk.sliit.TropicoMushrooms.dao.custom;

import lk.sliit.TropicoMushrooms.dao.CrudDAO;
import lk.sliit.TropicoMushrooms.entity.Item;

import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item, String> {
    public String getLastId() throws Exception;

    public Item searchByDescription(String description) throws Exception;

    public ArrayList<Item> getItemByDescription(String description) throws Exception;

    public String getItemFromItemCode(String itemCode) throws Exception;

    public int getCount() throws Exception;
}
