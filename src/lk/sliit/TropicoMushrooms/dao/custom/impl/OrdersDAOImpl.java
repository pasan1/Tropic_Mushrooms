package lk.sliit.TropicoMushrooms.dao.custom.impl;

import lk.sliit.TropicoMushrooms.dao.CrudUtil;
import lk.sliit.TropicoMushrooms.dao.custom.OrdersDAO;
import lk.sliit.TropicoMushrooms.entity.Orders;

import java.sql.ResultSet;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public boolean save(Orders orders) throws Exception {
        String sql = "INSERT INTO Orders VALUES (?, ?, ?, ?)";
        return CrudUtil.executeUpdate(sql,
                orders.getOrderId(),
                orders.getDate(),
                orders.getCustomerId(),
                orders.getUserId()
        );
    }

    @Override
    public boolean update(Orders orders) throws Exception {
        String sql = "UPDATE Orders SET Date=?, CustomerID=?, UserID=? WHERE OrderID=?";
        return CrudUtil.executeUpdate(sql,
                orders.getDate(),
                orders.getCustomerId(),
                orders.getUserId(),
                orders.getOrderId()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM Orders WHERE OrderID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public Orders search(String s) throws Exception {
        String sql = "SELECT * FROM Orders WHERE OrderID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()) {
            return new Orders(
                    rst.getInt(1),
                    rst.getDate(2),
                    rst.getInt(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Orders> getAll() throws Exception {
        String sql = "SELECT * FROM Orders";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Orders> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Orders(
                    rst.getInt(1),
                    rst.getDate(2),
                    rst.getInt(3),
                    rst.getInt(4)
            ));
        }
        return list;
    }

    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT OrderID FROM Orders ORDER BY OrderID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public int getCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM Orders";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}
