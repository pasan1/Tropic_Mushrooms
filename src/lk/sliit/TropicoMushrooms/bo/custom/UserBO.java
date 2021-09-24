package lk.sliit.TropicoMushrooms.bo.custom;

import lk.sliit.TropicoMushrooms.bo.SuperBO;
import lk.sliit.TropicoMushrooms.dto.SqDTO;
import lk.sliit.TropicoMushrooms.dto.UserDTO;

import java.util.ArrayList;

public interface UserBO extends SuperBO {
    public boolean save(UserDTO dto) throws Exception;

    public boolean update(UserDTO dto) throws Exception;

    public boolean delete(String id) throws Exception;

    public ArrayList<UserDTO> getAll() throws Exception;

    public String getUserLastId() throws Exception;

    public String getPasswordFromUserName(String userName) throws Exception;

    public String getPasswordFromUserId(String userId) throws Exception;

    public String getDesignation(String userName) throws Exception;

    public String getUserId(String userName) throws Exception;

    public void setUserId(String userName) throws Exception;

    public String getUserId() throws Exception;

    public String getUserFullName(String userId) throws Exception;

    public UserDTO search(String id) throws Exception;

    public UserDTO searchByNIC(String nic) throws Exception;

    public boolean updateBasicInfo(UserDTO dto) throws Exception;

    public boolean updateUserBasic(UserDTO dto) throws Exception;

    public boolean updateUserQA(UserDTO dto) throws Exception;

    public boolean updateUserPassword(UserDTO dto) throws Exception;

    public String getQuestionFromNumber(int no) throws Exception;

    public int getQuestionNumberFromQuestion(String question) throws Exception;

    public ArrayList<SqDTO> getAllSQ() throws Exception;

    public ArrayList<UserDTO> getUsersDetails(String nic) throws Exception;

    public ArrayList<String> getAllQuestions() throws Exception;

    public boolean isUserNameAvailable(String userName) throws Exception;

    public boolean isPasswordAvailable(String password) throws Exception;

    public boolean updatePasswordAndSQ(UserDTO dto) throws Exception;
}
