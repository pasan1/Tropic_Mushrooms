package lk.sliit.TropicoMushrooms.dao.custom;

import lk.sliit.TropicoMushrooms.dao.CrudDAO;
import lk.sliit.TropicoMushrooms.entity.User;

import java.util.ArrayList;

public interface UserDAO extends CrudDAO<User, String> {
    public User searchByNIC(String nic) throws Exception;

    public ArrayList<User> getUsersDetails(String nic) throws Exception;

    public String getPasswordFromUserName(String userName) throws Exception;

    public String getPasswordFromUserId(String userName) throws Exception;

    public String getLastId() throws Exception;

    public String getDesignation(String userName) throws Exception;

    public String getUserId(String userName) throws Exception;

    public String getUserFull(String userId) throws Exception;

    public boolean updateUserBasicInfo(User user) throws Exception;

    public boolean updateUserBasic(User user) throws Exception;

    public boolean updateUserQA(User user) throws Exception;

    public boolean updateUserPassword(User user) throws Exception;

    public boolean isUserNameAvailable(String userName) throws Exception;

    public boolean isPasswordAvailable(String password) throws Exception;

    public boolean updatePasswordAndSQ(User user) throws Exception;
}
