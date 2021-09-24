package lk.sliit.TropicoMushrooms.dao.custom.impl;

import lk.sliit.TropicoMushrooms.dao.CrudUtil;
import lk.sliit.TropicoMushrooms.dao.custom.SqDAO;
import lk.sliit.TropicoMushrooms.entity.SQ;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SqDAOImpl implements SqDAO {
    @Override
    public boolean save(SQ sq) throws Exception {
        String sql = "INSERT INTO SQ VALUES (?,?)";
        return CrudUtil.executeUpdate(sql,
                sq.getSqNo(),
                sq.getQuestion()
        );
    }

    @Override
    public boolean update(SQ sq) throws Exception {
        String sql = "UPDATE SQ SET Question=? WHERE SQno=?";
        return CrudUtil.executeUpdate(sql,
                sq.getQuestion(),
                sq.getSqNo()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE FROM SQ WHERE SQno=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public SQ search(String s) throws Exception {
        String sql = "SELECT * FROM SQ WHERE SQno=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        if (rst.next()) {
            return new SQ(
                    rst.getInt(1),
                    rst.getString(2)
            );
        }
        return null;
    }

    @Override
    public ArrayList<SQ> getAll() throws Exception {
        String sql = "SELECT * FROM SQ";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<SQ> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new SQ(
                    rst.getInt(1),
                    rst.getString(2)
            ));
        }
        return list;
    }

    @Override
    public String getQuestionFromNumber(int no) throws Exception {
        String sql = "SELECT Question FROM SQ WHERE SQno=?";
        ResultSet rst = CrudUtil.executeQuery(sql, no);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public int getQuestionNumberFromQuestion(String question) throws Exception {
        String sql = "SELECT SQno FROM SQ WHERE Question=?";
        ResultSet rst = CrudUtil.executeQuery(sql, question);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}
