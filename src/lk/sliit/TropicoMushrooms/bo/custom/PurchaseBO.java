package lk.sliit.TropicoMushrooms.bo.custom;

import lk.sliit.TropicoMushrooms.bo.SuperBO;
import lk.sliit.TropicoMushrooms.dto.PurchaseDTO;

import java.util.ArrayList;
import java.sql.Date;

public interface PurchaseBO extends SuperBO {
    public String getPurchaseLastId() throws Exception;

    public ArrayList<PurchaseDTO> getAll() throws Exception;

    public boolean save(PurchaseDTO dto) throws Exception;

    public boolean update(PurchaseDTO dto) throws Exception;

    public boolean delete(String id) throws Exception;

    public PurchaseDTO search(String id) throws Exception;

    public ArrayList<PurchaseDTO> getPurchaseByDate(Date date) throws Exception;
}
