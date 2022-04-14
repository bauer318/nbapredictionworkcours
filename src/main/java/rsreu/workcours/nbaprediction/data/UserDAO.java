package rsreu.workcours.nbaprediction.data;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws SQLException;
    User getUserByLogin(String login) throws SQLException;
    void setAuthorizationStatusByLogin(String login, int status) throws SQLException;
    int getGroupIDByLogin(String login) throws SQLException;
    void addAdminModerator(String login, String password, int id_group) throws SQLException;
    void addBettor(String firstname, String lastname, String email) throws SQLException;
    List<User> getAdminModer() throws SQLException;
    void deleteUserByID(int id) throws SQLException;
    String getLoginByID(int id) throws SQLException;
    void udpdateUserByID(int id, String login, String password) throws SQLException;
}
