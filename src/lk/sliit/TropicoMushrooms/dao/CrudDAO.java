package lk.sliit.TropicoMushrooms.dao;

import lk.sliit.TropicoMushrooms.entity.SuperEntity;

import java.io.Serializable;
import java.util.ArrayList;

public interface CrudDAO<T extends SuperEntity, ID extends Serializable> extends SuperDAO {
    public boolean save(T t) throws Exception;

    public boolean update(T t) throws Exception;

    public boolean delete(ID id) throws Exception;

    public T search(ID id) throws Exception;

    public ArrayList<T> getAll() throws Exception;
}
