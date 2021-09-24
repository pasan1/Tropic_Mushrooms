package lk.sliit.TropicoMushrooms.bo.custom.impl;

import lk.sliit.TropicoMushrooms.bo.custom.UserBO;
import lk.sliit.TropicoMushrooms.dao.DAOFactory;
import lk.sliit.TropicoMushrooms.dao.custom.SqDAO;
import lk.sliit.TropicoMushrooms.dao.custom.UserDAO;
import lk.sliit.TropicoMushrooms.dto.SqDTO;
import lk.sliit.TropicoMushrooms.dto.UserDTO;
import lk.sliit.TropicoMushrooms.entity.SQ;
import lk.sliit.TropicoMushrooms.entity.User;

import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    SqDAO sqDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SQ);

    private static String userId;

    @Override
    public boolean save(UserDTO dto) throws Exception {
        return userDAO.save(new User(
                dto.getUserId(),
                dto.getName(),
                dto.getNic(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getDesignation(),
                dto.getUserName(),
                dto.getPassword(),
                getQuestionNumberFromQuestion(dto.getQ1()),
                getQuestionNumberFromQuestion(dto.getQ2()),
                getQuestionNumberFromQuestion(dto.getQ3()),
                dto.getA1(),
                dto.getA2(),
                dto.getA3()
        ));
    }

    @Override
    public boolean update(UserDTO dto) throws Exception {
        return userDAO.update(new User(
                dto.getUserId(),
                dto.getName(),
                dto.getNic(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getDesignation(),
                dto.getUserName(),
                dto.getPassword(),
                getQuestionNumberFromQuestion(dto.getQ1()),
                getQuestionNumberFromQuestion(dto.getQ2()),
                getQuestionNumberFromQuestion(dto.getQ3()),
                dto.getA1(),
                dto.getA2(),
                dto.getA3()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return userDAO.delete(id);
    }

    @Override
    public ArrayList<UserDTO> getAll() throws Exception {
        ArrayList<User> all = userDAO.getAll();
        ArrayList<UserDTO> allUsers = new ArrayList<>();
        for (User u : all) {
            UserDTO dto = new UserDTO(
                    u.getUserId(),
                    u.getName(),
                    u.getNic(),
                    u.getAddress(),
                    u.getMobile(),
                    u.getDesignation(),
                    u.getUserName(),
                    u.getPassword(),
                    getQuestionFromNumber(u.getQ1()),
                    getQuestionFromNumber(u.getQ2()),
                    getQuestionFromNumber(u.getQ3()),
                    u.getA1(),
                    u.getA2(),
                    u.getA3()
            );
            allUsers.add(dto);
        }
        return allUsers;
    }

    @Override
    public String getUserLastId() throws Exception {
        return userDAO.getLastId();
    }

    @Override
    public String getPasswordFromUserName(String userName) throws Exception {
        return userDAO.getPasswordFromUserName(userName);
    }

    @Override
    public String getPasswordFromUserId(String userId) throws Exception {
        return userDAO.getPasswordFromUserId(userId);
    }

    @Override
    public String getDesignation(String userName) throws Exception {
        return userDAO.getDesignation(userName);
    }

    @Override
    public String getUserId(String userName) throws Exception {
        return userDAO.getUserId(userName);
    }

    @Override
    public void setUserId(String userName) throws Exception {
        this.userId = getUserId(userName);
    }

    @Override
    public String getUserId() throws Exception {
        return userId;
    }

    @Override
    public String getUserFullName(String userId) throws Exception {
        return userDAO.getUserFull(userId);
    }

    @Override
    public UserDTO search(String id) throws Exception {
        User u = userDAO.search(id);
        return new UserDTO(
                u.getUserId(),
                u.getName(),
                u.getNic(),
                u.getAddress(),
                u.getMobile(),
                u.getDesignation(),
                u.getUserName(),
                u.getPassword(),
                getQuestionFromNumber(u.getQ1()),
                getQuestionFromNumber(u.getQ2()),
                getQuestionFromNumber(u.getQ3()),
                u.getA1(),
                u.getA2(),
                u.getA3()
        );
    }

    @Override
    public UserDTO searchByNIC(String nic) throws Exception {
        User u = userDAO.searchByNIC(nic);
        return new UserDTO(
                u.getUserId(),
                u.getName(),
                u.getNic(),
                u.getAddress(),
                u.getMobile(),
                u.getDesignation(),
                u.getUserName(),
                u.getPassword(),
                getQuestionFromNumber(u.getQ1()),
                getQuestionFromNumber(u.getQ2()),
                getQuestionFromNumber(u.getQ3()),
                u.getA1(),
                u.getA2(),
                u.getA3()
        );
    }

    @Override
    public boolean updateBasicInfo(UserDTO dto) throws Exception {
        return userDAO.updateUserBasicInfo(new User(
                dto.getUserId(),
                dto.getName(),
                dto.getNic(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getDesignation()
        ));
    }

    @Override
    public boolean updateUserBasic(UserDTO dto) throws Exception {
        return userDAO.updateUserBasicInfo(new User(
                dto.getUserId(),
                dto.getName(),
                dto.getNic(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getDesignation(),
                dto.getUserName()
        ));
    }

    @Override
    public boolean updateUserQA(UserDTO dto) throws Exception {
        return userDAO.updateUserQA(new User(
                dto.getUserId(),
                getQuestionNumberFromQuestion(dto.getQ1()),
                getQuestionNumberFromQuestion(dto.getQ2()),
                getQuestionNumberFromQuestion(dto.getQ3()),
                dto.getA1(),
                dto.getA2(),
                dto.getA3()
        ));
    }

    @Override
    public boolean updateUserPassword(UserDTO dto) throws Exception {
        return userDAO.updateUserPassword(new User(
                dto.getUserId(),
                dto.getPassword()
        ));
    }

    @Override
    public String getQuestionFromNumber(int no) throws Exception {
        return sqDAO.getQuestionFromNumber(no);
    }

    @Override
    public int getQuestionNumberFromQuestion(String question) throws Exception {
        return sqDAO.getQuestionNumberFromQuestion(question);
    }

    @Override
    public ArrayList<SqDTO> getAllSQ() throws Exception {
        ArrayList<SQ> all = sqDAO.getAll();
        ArrayList<SqDTO> list = new ArrayList<>();
        for (SQ sq : all) {
            list.add(new SqDTO(sq.getSqNo(), sq.getQuestion()));
        }
        return list;
    }

    @Override
    public ArrayList<UserDTO> getUsersDetails(String nic) throws Exception {
        ArrayList<User> all = userDAO.getUsersDetails(nic);
        ArrayList<UserDTO> allUsers = new ArrayList<>();
        for (User u : all) {
            UserDTO dto = new UserDTO(
                    u.getUserId(),
                    u.getName(),
                    u.getNic(),
                    u.getAddress(),
                    u.getMobile(),
                    u.getDesignation(),
                    u.getUserName(),
                    u.getPassword(),
                    getQuestionFromNumber(u.getQ1()),
                    getQuestionFromNumber(u.getQ2()),
                    getQuestionFromNumber(u.getQ3()),
                    u.getA1(),
                    u.getA2(),
                    u.getA3()
            );
            allUsers.add(dto);
        }
        return allUsers;
    }

    @Override
    public ArrayList<String> getAllQuestions() throws Exception {
        ArrayList<SQ> all = sqDAO.getAll();
        ArrayList<String> list = new ArrayList<>();
        for (SQ s : all) {
            list.add(s.getQuestion());
        }
        return list;
    }

    @Override
    public boolean isUserNameAvailable(String userName) throws Exception {
        return userDAO.isUserNameAvailable(userName);
    }

    @Override
    public boolean isPasswordAvailable(String password) throws Exception {
        return userDAO.isPasswordAvailable(password);
    }

    @Override
    public boolean updatePasswordAndSQ(UserDTO dto) throws Exception {
        return userDAO.updatePasswordAndSQ(new User(
                dto.getUserId(),
                dto.getPassword(),
                getQuestionNumberFromQuestion(dto.getQ1()),
                getQuestionNumberFromQuestion(dto.getQ2()),
                getQuestionNumberFromQuestion(dto.getQ3()),
                dto.getA1(),
                dto.getA2(),
                dto.getA3()
        ));
    }
}
