package lk.sliit.TropicoMushrooms.dao.custom.impl;

import lk.sliit.TropicoMushrooms.dao.CrudUtil;
import lk.sliit.TropicoMushrooms.dao.custom.UserDAO;
import lk.sliit.TropicoMushrooms.entity.User;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User user) throws Exception {
        String sql = "INSERT INTO User VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.executeUpdate(sql,
                user.getUserId(),
                user.getName(),
                user.getAddress(),
                user.getMobile(),
                user.getNic(),
                user.getDesignation(),
                user.getUserName(),
                user.getPassword(),
                user.getQ1(),
                user.getQ2(),
                user.getQ3(),
                user.getA1(),
                user.getA2(),
                user.getA3()
        );
    }

    @Override
    public boolean update(User user) throws Exception {
        String sql = "UPDATE User SET Name=?, Address=?, Mobile=?, NIC=?, Designation=?, UserName=?, Password=?, Q1=?, Q2=?, Q3=?, A1=?, A2=?, A3=? WHERE UserID=?";
        return CrudUtil.executeUpdate(sql,
                user.getName(),
                user.getAddress(),
                user.getMobile(),
                user.getNic(),
                user.getDesignation(),
                user.getUserName(),
                user.getPassword(),
                user.getQ1(),
                user.getQ2(),
                user.getQ3(),
                user.getA1(),
                user.getA2(),
                user.getA3(),
                user.getUserId()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM User WHERE UserID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public User search(String s) throws Exception {
        String sql = "SELECT * FROM User WHERE UserID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()) {
            return new User(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getInt(9),
                    rst.getInt(10),
                    rst.getInt(11),
                    rst.getString(12),
                    rst.getString(13),
                    rst.getString(14)
            );
        }
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        String sql = "SELECT * FROM User";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<User> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new User(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getInt(9),
                    rst.getInt(10),
                    rst.getInt(11),
                    rst.getString(12),
                    rst.getString(13),
                    rst.getString(14)
            ));
        }
        return list;
    }

    @Override
    public User searchByNIC(String nic) throws Exception {
        String sql = "SELECT * FROM User WHERE NIC=?";
        ResultSet rst = CrudUtil.executeQuery(sql, nic);
        if (rst.next()) {
            return new User(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getInt(9),
                    rst.getInt(10),
                    rst.getInt(11),
                    rst.getString(12),
                    rst.getString(13),
                    rst.getString(14)
            );
        }
        return null;
    }

    @Override
    public ArrayList<User> getUsersDetails(String nic) throws Exception {
        String s = "" + "%" + nic + "%";
        String sql = "SELECT * FROM User WHERE NIC LIKE ?";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<User> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new User(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getInt(9),
                    rst.getInt(10),
                    rst.getInt(11),
                    rst.getString(12),
                    rst.getString(13),
                    rst.getString(14)
            ));
        }
        return list;
    }

    @Override
    public String getPasswordFromUserName(String userName) throws Exception {
        String sql = "SELECT Password FROM User WHERE UserName=?";
        ResultSet rst = CrudUtil.executeQuery(sql, userName);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getPasswordFromUserId(String userName) throws Exception {
        String sql = "SELECT Password FROM User WHERE UserId=?";
        ResultSet rst = CrudUtil.executeQuery(sql, userName);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT UserID FROM User ORDER BY UserID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getDesignation(String userName) throws Exception {
        String sql = "SELECT Designation FROM User WHERE UserName=?";
        ResultSet rst = CrudUtil.executeQuery(sql, userName);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getUserId(String userName) throws Exception {
        String sql = "SELECT UserID FROM User WHERE UserName=?";
        ResultSet rst = CrudUtil.executeQuery(sql, userName);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public String getUserFull(String userId) throws Exception {
        String sql = " SELECT Name AS Name FROM User WHERE UserID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, userId);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean updateUserBasicInfo(User user) throws Exception {
        String sql = "UPDATE User SET Name=?, Address=?, Mobile=?, NIC=?, Designation=? WHERE UserID=?";
        return CrudUtil.executeUpdate(sql,
                user.getName(),
                user.getAddress(),
                user.getMobile(),
                user.getNic(),
                user.getDesignation(),
                user.getUserId()
        );
    }

    @Override
    public boolean updateUserBasic(User user) throws Exception {
        String sql = "UPDATE User SET Name=?, Address=?, Mobile=?, NIC=?, Designation=?, UserName=? WHERE UserID=?";
        return CrudUtil.executeUpdate(sql,
                user.getName(),
                user.getAddress(),
                user.getMobile(),
                user.getNic(),
                user.getDesignation(),
                user.getUserName(),
                user.getUserId()
        );
    }

    @Override
    public boolean updateUserQA(User user) throws Exception {
        String sql = "UPDATE User SET Q1=?, Q2=?, Q3=?, A1=?, A2=?, A3=? WHERE UserID=?";
        return CrudUtil.executeUpdate(sql,
                user.getQ1(),
                user.getQ2(),
                user.getQ3(),
                user.getA1(),
                user.getA2(),
                user.getA3(),
                user.getUserId()
        );
    }

    @Override
    public boolean updateUserPassword(User user) throws Exception {
        String sql = "UPDATE User SET Password=? WHERE UserID=?";
        return CrudUtil.executeUpdate(sql,
                user.getPassword(),
                user.getUserId()
        );
    }

    @Override
    public boolean isUserNameAvailable(String userName) throws Exception {
        String sql = "SELECT UserID FROM User WHERE UserName=?";
        ResultSet rst = CrudUtil.executeQuery(sql, userName);
        return rst.next();
    }

    @Override
    public boolean isPasswordAvailable(String password) throws Exception {
        String sql = "SELECT Password FROM User WHERE Password=?";
        ResultSet rst = CrudUtil.executeQuery(sql, password);
        return rst.next();
    }

    @Override
    public boolean updatePasswordAndSQ(User user) throws Exception {
        String sql = "UPDATE User SET Password=?, Q1=?, Q2=?, Q3=?, A1=?, A2=?, A3=? WHERE UserID=?";
        return CrudUtil.executeUpdate(sql,
                user.getPassword(),
                user.getQ1(),
                user.getQ2(),
                user.getQ3(),
                user.getA1(),
                user.getA2(),
                user.getA3(),
                user.getUserId()
        );
    }
}
