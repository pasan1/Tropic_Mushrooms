package lk.sliit.TropicoMushrooms.dao.custom.impl;

import lk.sliit.TropicoMushrooms.dao.CrudUtil;
import lk.sliit.TropicoMushrooms.dao.custom.PurchaseDAO;
import lk.sliit.TropicoMushrooms.entity.Purchase;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PurchaseDAOImpl implements PurchaseDAO {
    @Override
    public boolean save(Purchase purchase) throws Exception {
        String sql = "INSERT INTO Purchase VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.executeUpdate(sql,
                purchase.getPurchaseId(),
                purchase.getUserId(),
                purchase.getDate(),
                purchase.getDescription(),
                purchase.getQty(),
                purchase.getUnit(),
                purchase.getTotalPrice()
        );
    }

    @Override
    public boolean update(Purchase purchase) throws Exception {
        String sql = "UPDATE Purchase SET UserID=?, Date=?, Description=?, Qty=?, Unit=?, TotalPrice=? WHERE PurchaseID=?";
        return CrudUtil.executeUpdate(sql,
                purchase.getUserId(),
                purchase.getDate(),
                purchase.getDescription(),
                purchase.getQty(),
                purchase.getUnit(),
                purchase.getTotalPrice(),
                purchase.getPurchaseId()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM Purchase WHERE PurchaseID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public Purchase search(String s) throws Exception {
        String sql = "SELECT * FROM Purchase WHERE PurchaseID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()) {
            return new Purchase(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getDate(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getDouble(7)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Purchase> getAll() throws Exception {
        String sql = "SELECT * FROM Purchase";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Purchase> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Purchase(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getDate(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getDouble(7)
            ));
        }
        return list;
    }

    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT PurchaseID FROM Purchase ORDER BY PurchaseID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<Purchase> getItemByDate(Date date) throws Exception {
        String sql = "SELECT * FROM Purchase WHERE Date=?";
        ResultSet rst = CrudUtil.executeQuery(sql, date);
        ArrayList<Purchase> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Purchase(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getDate(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getDouble(7)
            ));
        }
        return list;
    }

    @Override
    public int getCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM Purchase";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}
