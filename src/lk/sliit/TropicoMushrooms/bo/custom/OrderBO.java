package lk.sliit.TropicoMushrooms.bo.custom;

import lk.sliit.TropicoMushrooms.bo.SuperBO;
import lk.sliit.TropicoMushrooms.dto.OrderDTO;
import lk.sliit.TropicoMushrooms.dto.OrderDetailDTO;

import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    public String getLastOrderId() throws Exception;

    public ArrayList<OrderDTO> getAllCustomers() throws Exception;

    public ArrayList<OrderDTO> getAllItems() throws Exception;

    public boolean saveOrder(OrderDTO dto) throws Exception;

    public OrderDTO getCustomerFromNic(String nic) throws Exception;

    public OrderDTO getCustomerFromID(String id) throws Exception;

    public OrderDTO getItemFromId(String id) throws Exception;

    public OrderDTO getItemFromDescription(String description) throws Exception;

    public String getLastReturnOrderId() throws Exception;

    public ArrayList<OrderDetailDTO> searchOrderDetails(String orderID) throws Exception;

    public OrderDTO searchOrder(String orderID) throws Exception;

    public String getItemFromItemCode(String itemCode) throws Exception;

    public boolean addReturn(OrderDTO dto) throws Exception;
}
