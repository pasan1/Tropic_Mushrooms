package lk.sliit.TropicoMushrooms.dao.custom.impl;

import lk.sliit.TropicoMushrooms.dao.CrudUtil;
import lk.sliit.TropicoMushrooms.dao.custom.CustomerDAO;
import lk.sliit.TropicoMushrooms.entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws Exception {
        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.executeUpdate(sql,
                customer.getCustomerId(),
                customer.getName(),
                customer.getAddress(),
                customer.getMobile(),
                customer.getNic()
        );
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        String sql = "UPDATE Customer SET Name=?, Address=?, Mobile=?, NIC=? WHERE CustomerID=?";
        return CrudUtil.executeUpdate(sql,
                customer.getName(),
                customer.getAddress(),
                customer.getMobile(),
                customer.getNic(),
                customer.getCustomerId()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM Customer WHERE CustomerID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public Customer search(String s) throws Exception {
        String sql = "SELECT * FROM Customer WHERE CustomerID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()) {
            return new Customer(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        String sql = "SELECT * FROM Customer";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Customer> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Customer(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return list;
    }

    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT CustomerID FROM Customer ORDER BY CustomerID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public Customer searchByNIC(String nic) throws Exception {
        String sql = "SELECT * FROM Customer WHERE NIC=?";
        ResultSet rst = CrudUtil.executeQuery(sql, nic);
        if (rst.next()) {
            return new Customer(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getSearchCustomerDetails(String id) throws Exception {
        String s = "" + "%" + id + "%";
        String sql = "SELECT * FROM Customer WHERE NIC LIKE ?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        ArrayList<Customer> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Customer(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return list;
    }

    @Override
    public int getCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM Customer";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}
