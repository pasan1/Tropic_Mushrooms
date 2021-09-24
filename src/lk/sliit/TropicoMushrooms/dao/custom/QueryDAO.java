package lk.sliit.TropicoMushrooms.dao.custom;

import lk.sliit.TropicoMushrooms.dao.SuperDAO;
import lk.sliit.TropicoMushrooms.entity.CustomEntity;

import java.sql.Date;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public double getTodayTotalSale(Date today) throws Exception;

    public ArrayList<CustomEntity> chart() throws Exception;
}
