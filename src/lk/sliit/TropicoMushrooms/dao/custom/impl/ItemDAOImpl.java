package lk.sliit.TropicoMushrooms.dao.custom.impl;

import lk.sliit.TropicoMushrooms.dao.CrudUtil;
import lk.sliit.TropicoMushrooms.dao.custom.ItemDAO;
import lk.sliit.TropicoMushrooms.entity.Item;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item item) throws Exception {
        String sql = "INSERT INTO Item VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.executeUpdate(sql,
                item.getItemCode(),
                item.getDescription(),
                item.getPrice(),
                item.getQtyOnHand(),
                item.getUnit()
        );
    }

    @Override
    public boolean update(Item item) throws Exception {
        String sql = "UPDATE Item SET Description=?, Price=?, QtyOnHand=?, Unit=? WHERE ItemCode=?";
        return CrudUtil.executeUpdate(sql,
                item.getDescription(),
                item.getPrice(),
                item.getQtyOnHand(),
                item.getUnit(),
                item.getItemCode()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM Item WHERE ItemCode=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public Item search(String s) throws Exception {
        String sql = "SELECT * FROM Item WHERE ItemCode=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()) {
            return new Item(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Item> getAll() throws Exception {
        String sql = "SELECT * FROM Item";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Item> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Item(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getString(5)
            ));
        }
        return list;
    }

    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT ItemCode FROM Item ORDER BY ItemCode DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public Item searchByDescription(String description) throws Exception {
        String sql = "SELECT * FROM Item WHERE Description = ?";
        ResultSet rst = CrudUtil.executeQuery(sql, description);
        if (rst.next()) {
            return new Item(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Item> getItemByDescription(String description) throws Exception {
        String s = "" + "%" + description + "%";
        String sql = "SELECT * FROM Item WHERE Description LIKE ?";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Item> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Item(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getString(5)
            ));
        }
        return list;
    }

    @Override
    public String getItemFromItemCode(String itemCode) throws Exception {
        String sql = "SELECT Description FROM Item WHERE ItemCode=?";
        ResultSet rst = CrudUtil.executeQuery(sql, itemCode);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public int getCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM Item";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}
