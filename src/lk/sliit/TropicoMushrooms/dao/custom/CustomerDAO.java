package lk.sliit.TropicoMushrooms.dao.custom;

import lk.sliit.TropicoMushrooms.dao.CrudDAO;
import lk.sliit.TropicoMushrooms.entity.Customer;

import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer, String> {
    public String getLastId() throws Exception;

    public Customer searchByNIC(String nic) throws Exception;

    public ArrayList<Customer> getSearchCustomerDetails(String id) throws Exception;

    public int getCount() throws Exception;
}