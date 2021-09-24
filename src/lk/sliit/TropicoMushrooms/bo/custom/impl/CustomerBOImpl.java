package lk.sliit.TropicoMushrooms.bo.custom.impl;

import lk.sliit.TropicoMushrooms.bo.custom.CustomerBO;
import lk.sliit.TropicoMushrooms.dao.DAOFactory;
import lk.sliit.TropicoMushrooms.dao.custom.CustomerDAO;
import lk.sliit.TropicoMushrooms.dto.CustomerDTO;
import lk.sliit.TropicoMushrooms.entity.Customer;

import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public String getCustomerLastId() throws Exception {
        return customerDAO.getLastId();
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws Exception {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer c : all) {
            CustomerDTO dto = new CustomerDTO(
                    c.getCustomerId(),
                    c.getName(),
                    c.getAddress(),
                    c.getMobile(),
                    c.getNic()
            );
            allCustomers.add(dto);
        }
        return allCustomers;
    }

    @Override
    public boolean save(CustomerDTO dto) throws Exception {
        return customerDAO.save(new Customer(dto.getCustomerId(), dto.getName(), dto.getAddress(), dto.getMobile(), dto.getNic()));
    }

    @Override
    public boolean update(CustomerDTO dto) throws Exception {
        return customerDAO.update(new Customer(dto.getCustomerId(), dto.getName(), dto.getAddress(), dto.getMobile(), dto.getNic()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO search(String id) throws Exception {
        Customer c = customerDAO.search(id);
        return new CustomerDTO(
                c.getCustomerId(),
                c.getName(),
                c.getAddress(),
                c.getMobile(),
                c.getNic()
        );
    }

    @Override
    public CustomerDTO searchByNIC(String nic) throws Exception {
        Customer c = customerDAO.searchByNIC(nic);
        return new CustomerDTO(
                c.getCustomerId(),
                c.getName(),
                c.getAddress(),
                c.getMobile(),
                c.getNic()
        );
    }

    @Override
    public ArrayList<CustomerDTO> getSearchCustomerDetails(String id) throws Exception {
        ArrayList<Customer> all = customerDAO.getSearchCustomerDetails(id);
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer c : all) {
            CustomerDTO dto = new CustomerDTO(
                    c.getCustomerId(),
                    c.getName(),
                    c.getAddress(),
                    c.getMobile(),
                    c.getNic()
            );
            allCustomers.add(dto);
        }
        return allCustomers;
    }
}
