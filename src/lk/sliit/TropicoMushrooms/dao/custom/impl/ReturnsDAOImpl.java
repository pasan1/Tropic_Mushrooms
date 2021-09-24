package lk.sliit.TropicoMushrooms.dao.custom.impl;

import lk.sliit.TropicoMushrooms.dao.CrudUtil;
import lk.sliit.TropicoMushrooms.dao.custom.ReturnsDAO;
import lk.sliit.TropicoMushrooms.entity.Customer;
import lk.sliit.TropicoMushrooms.entity.Returns;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ReturnsDAOImpl implements ReturnsDAO {
    @Override
    public boolean save(Returns returns) throws Exception {
        String sql = "INSERT INTO Returns VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.executeUpdate(sql,
                returns.getReturnId(),
                returns.getOrderId(),
                returns.getUserId(),
                returns.getReason()
        );
    }

    @Override
    public boolean update(Returns returns) throws Exception {
        String sql = "UPDATE Returns SET OrderID=?, UserID=?, Reason=? WHERE ReturnID=?";
        return CrudUtil.executeUpdate(sql,
                returns.getOrderId(),
                returns.getUserId(),
                returns.getReason(),
                returns.getReturnId()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM Returns WHERE ReturnID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public Returns search(String s) throws Exception {
        String sql = "SELECT * FROM Returns WHERE ReturnID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()) {
            return new Returns(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Returns> getAll() throws Exception {
        String sql = "SELECT * FROM Returns";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Returns> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Returns(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getString(4)
            ));
        }
        return list;
    }

    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT ReturnID FROM Returns ORDER BY ReturnID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
