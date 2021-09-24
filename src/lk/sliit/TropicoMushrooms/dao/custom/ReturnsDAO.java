package lk.sliit.TropicoMushrooms.dao.custom;

import lk.sliit.TropicoMushrooms.dao.CrudDAO;
import lk.sliit.TropicoMushrooms.entity.Returns;

public interface ReturnsDAO extends CrudDAO<Returns, String> {
    public String getLastId() throws Exception;
}
