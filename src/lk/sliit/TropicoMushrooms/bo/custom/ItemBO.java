package lk.sliit.TropicoMushrooms.bo.custom;

import lk.sliit.TropicoMushrooms.bo.SuperBO;
import lk.sliit.TropicoMushrooms.dto.ItemDTO;

import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public String getItemLastId() throws Exception;

    public ArrayList<ItemDTO> getAll() throws Exception;

    public boolean save(ItemDTO dto) throws Exception;

    public boolean update(ItemDTO dto) throws Exception;

    public boolean delete(String id) throws Exception;

    public ItemDTO search(String id) throws Exception;

    public ItemDTO searchByDescription(String description) throws Exception;

    public ArrayList<ItemDTO> getItemByDescription(String description) throws Exception;
}
