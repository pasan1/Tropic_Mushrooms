package lk.sliit.TropicoMushrooms.bo.custom.impl;

import lk.sliit.TropicoMushrooms.bo.custom.ItemBO;
import lk.sliit.TropicoMushrooms.dao.DAOFactory;
import lk.sliit.TropicoMushrooms.dao.custom.ItemDAO;
import lk.sliit.TropicoMushrooms.dto.ItemDTO;
import lk.sliit.TropicoMushrooms.entity.Item;

import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public String getItemLastId() throws Exception {
        return itemDAO.getLastId();
    }

    @Override
    public ArrayList<ItemDTO> getAll() throws Exception {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        for (Item i : all) {
            ItemDTO dto = new ItemDTO(
                    i.getItemCode(),
                    i.getDescription(),
                    i.getPrice(),
                    i.getQtyOnHand(),
                    i.getUnit()
            );
            allItems.add(dto);
        }
        return allItems;
    }

    @Override
    public boolean save(ItemDTO dto) throws Exception {
        return itemDAO.save(new Item(dto.getItemCode(), dto.getDescription(), dto.getPrice(), dto.getQtyOnHand(), dto.getUnit()));
    }

    @Override
    public boolean update(ItemDTO dto) throws Exception {
        return itemDAO.update(new Item(dto.getItemCode(), dto.getDescription(), dto.getPrice(), dto.getQtyOnHand(), dto.getUnit()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return itemDAO.delete(id);
    }

    @Override
    public ItemDTO search(String id) throws Exception {
        Item i = itemDAO.search(id);
        return new ItemDTO(
                i.getItemCode(),
                i.getDescription(),
                i.getPrice(),
                i.getQtyOnHand(),
                i.getUnit()
        );
    }

    @Override
    public ItemDTO searchByDescription(String description) throws Exception {
        Item i = itemDAO.searchByDescription(description);
        return new ItemDTO(
                i.getItemCode(),
                i.getDescription(),
                i.getPrice(),
                i.getQtyOnHand(),
                i.getUnit()
        );
    }

    @Override
    public ArrayList<ItemDTO> getItemByDescription(String description) throws Exception {
        ArrayList<Item> all = itemDAO.getItemByDescription(description);
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        for (Item i : all) {
            ItemDTO dto = new ItemDTO(
                    i.getItemCode(),
                    i.getDescription(),
                    i.getPrice(),
                    i.getQtyOnHand(),
                    i.getUnit()
            );
            allItems.add(dto);
        }
        return allItems;
    }
}
