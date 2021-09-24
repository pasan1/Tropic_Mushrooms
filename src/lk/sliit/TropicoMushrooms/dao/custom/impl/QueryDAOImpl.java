package lk.sliit.TropicoMushrooms.dao.custom.impl;

import lk.sliit.TropicoMushrooms.dao.CrudUtil;
import lk.sliit.TropicoMushrooms.dao.custom.QueryDAO;
import lk.sliit.TropicoMushrooms.entity.CustomEntity;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public double getTodayTotalSale(Date today) throws Exception {
        String sql = "SELECT SUM(Qty * UnitPrice) AS Amount FROM OrderDetail WHERE OrderID IN (SELECT orderID FROM Orders WHERE date = ?)";
        ResultSet rst = CrudUtil.executeQuery(sql, today);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public ArrayList<CustomEntity> chart() throws Exception {
        String sql="SELECT o.Date, SUM(oo.Qty * oo.UnitPrice) AS Amount FROM Orders o, OrderDetail oo WHERE o.OrderID=oo.OrderID GROUP BY o.Date";
        ResultSet rst=CrudUtil.executeQuery(sql);
        ArrayList<CustomEntity> charts=new ArrayList<>();
        while (rst.next()){

            charts.add(new CustomEntity(rst.getDate(1),rst.getDouble(2)));
        }
        return charts;
    }
}
