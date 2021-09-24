package lk.sliit.TropicoMushrooms.bo.custom.impl;

import lk.sliit.TropicoMushrooms.bo.custom.PurchaseBO;
import lk.sliit.TropicoMushrooms.dao.DAOFactory;
import lk.sliit.TropicoMushrooms.dao.custom.PurchaseDAO;
import lk.sliit.TropicoMushrooms.dto.PurchaseDTO;
import lk.sliit.TropicoMushrooms.entity.Purchase;

import java.util.ArrayList;
import java.sql.Date;

public class PurchaseBOImpl implements PurchaseBO {

    PurchaseDAO purchaseDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PURCHASE);

    @Override
    public String getPurchaseLastId() throws Exception {
        return purchaseDAO.getLastId();
    }

    @Override
    public ArrayList<PurchaseDTO> getAll() throws Exception {
        ArrayList<Purchase> all = purchaseDAO.getAll();
        ArrayList<PurchaseDTO> allPurchases = new ArrayList<>();
        for (Purchase p : all) {
            PurchaseDTO dto = new PurchaseDTO(
                    p.getPurchaseId(),
                    p.getUserId(),
                    p.getDate(),
                    p.getDescription(),
                    p.getQty(),
                    p.getUnit(),
                    p.getTotalPrice()
            );
            allPurchases.add(dto);
        }
        return allPurchases;
    }

    @Override
    public boolean save(PurchaseDTO dto) throws Exception {
        return purchaseDAO.save(new Purchase(dto.getPurchaseId(), dto.getUserId(), dto.getDate(), dto.getDescription(), dto.getQty(), dto.getUnit(), dto.getTotalPrice()));
    }

    @Override
    public boolean update(PurchaseDTO dto) throws Exception {
        return purchaseDAO.update(new Purchase(dto.getPurchaseId(), dto.getUserId(), dto.getDate(), dto.getDescription(), dto.getQty(), dto.getUnit(), dto.getTotalPrice()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return purchaseDAO.delete(id);
    }

    @Override
    public PurchaseDTO search(String id) throws Exception {
        Purchase p = purchaseDAO.search(id);
        return new PurchaseDTO(
                p.getPurchaseId(),
                p.getUserId(),
                p.getDate(),
                p.getDescription(),
                p.getQty(),
                p.getUnit(),
                p.getTotalPrice()
        );
    }

    @Override
    public ArrayList<PurchaseDTO> getPurchaseByDate(Date date) throws Exception {
        ArrayList<Purchase> all = purchaseDAO.getItemByDate(date);
        ArrayList<PurchaseDTO> allPurchases = new ArrayList<>();
        for (Purchase p : all) {
            PurchaseDTO dto = new PurchaseDTO(
                    p.getPurchaseId(),
                    p.getUserId(),
                    p.getDate(),
                    p.getDescription(),
                    p.getQty(),
                    p.getUnit(),
                    p.getTotalPrice()
            );
            allPurchases.add(dto);
        }
        return allPurchases;
    }
}
