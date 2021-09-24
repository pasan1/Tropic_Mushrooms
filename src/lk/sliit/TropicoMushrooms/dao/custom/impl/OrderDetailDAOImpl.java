package lk.sliit.TropicoMushrooms.dao.custom.impl;

import lk.sliit.TropicoMushrooms.dao.CrudUtil;
import lk.sliit.TropicoMushrooms.dao.custom.OrderDetailDAO;
import lk.sliit.TropicoMushrooms.entity.OrderDetail;

import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean save(OrderDetail orderDetail) throws Exception {
        String sql = "INSERT INTO OrderDetail VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.executeUpdate(sql,
                orderDetail.getOrderId(),
                orderDetail.getItemCode(),
                orderDetail.getQty(),
                orderDetail.getUnit(),
                orderDetail.getUnitPrice()
        );
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws Exception {
        String sql = "UPDATE OrderDetail SET ItemCode=?, Qty=?, Unit=?, UnitPrice=? WHERE OrderID=?";
        return CrudUtil.executeUpdate(sql,
                orderDetail.getOrderId(),
                orderDetail.getItemCode(),
                orderDetail.getQty(),
                orderDetail.getUnit(),
                orderDetail.getUnitPrice()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM OrderDetail WHERE OrderID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public OrderDetail search(String s) throws Exception {
        String sql = "SELECT * FROM OrderDetail WHERE OrderID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()) {
            return new OrderDetail(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getDouble(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws Exception {
        String sql = "SELECT * FROM OrderDetail";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<OrderDetail> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new OrderDetail(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getDouble(5)
            ));
        }
        return list;
    }

    @Override
    public ArrayList<OrderDetail> searchOrderDetails(String orderID) throws Exception {
        String sql = "SELECT * FROM OrderDetail WHERE OrderID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, orderID);
        ArrayList<OrderDetail> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new OrderDetail(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getDouble(5)
            ));
        }
        return list;
    }

    @Override
    public int getCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM OrderDetail";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}
