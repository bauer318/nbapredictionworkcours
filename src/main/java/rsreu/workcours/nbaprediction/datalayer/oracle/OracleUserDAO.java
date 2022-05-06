package rsreu.workcours.nbaprediction.datalayer.oracle;

import rsreu.workcours.nbaprediction.data.dao.UserDAO;
import rsreu.workcours.nbaprediction.data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleUserDAO implements UserDAO {

    private Connection connection;

    public OracleUserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<User>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM applicationusers");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id_user");
                    int idGroup = resultSet.getInt("id_group");
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password_");
                    int blockingStatus = resultSet.getInt("blockingstatus");
                    int authorizationStatus = resultSet.getInt("authorizationstatus");
                    User user = new User(id, idGroup, login, password, blockingStatus, authorizationStatus);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error");
        }
        return users;
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT login, password_ FROM applicationusers WHERE login=?");
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String userLogin = resultSet.getString("login");
                    String password = resultSet.getString("password_");
                    user = new User(userLogin, password);
                }
            }
        } catch (SQLException e) {

        }
        return user;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM applicationusers WHERE id_user=?");
            preparedStatement.setInt(1,id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idGroup = resultSet.getInt("id_group");
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password_");
                    int blockingStatus = resultSet.getInt("blockingstatus");
                    int authorizationStatus = resultSet.getInt("authorizationstatus");
                    user = new User(id, idGroup, login, password, blockingStatus, authorizationStatus);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error");
        }
        return user;
    }

    @Override
    public void setAuthorizationStatusByLogin(String login, int status) throws SQLException {
        try {
            PreparedStatement prepareStatement = connection
                    .prepareStatement("UPDATE applicationusers SET authorizationstatus=? WHERE login =?");
            prepareStatement.setInt(1, status);
            prepareStatement.setString(2, login);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error");
        }

    }

    @Override
    public int getGroupIDByLogin(String login) throws SQLException {
        int groupID = 0;
        try {
            PreparedStatement prepareStatement = connection
                    .prepareStatement("SELECT id_group FROM applicationusers WHERE login = ?");
            prepareStatement.setString(1, login);
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                while (resultSet.next()) {
                    groupID = resultSet.getInt("id_group");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error");
        }
        return groupID;
    }

    @Override
    public void addAdminModerator(String login, String password, int id_group) throws SQLException {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(
                    "INSERT INTO applicationusers(id_group,login,password_,blockingstatus,authorizationstatus)  VALUES (?,?,?,0,0)");
            prepareStatement.setInt(1, id_group);
            prepareStatement.setString(2, login);
            prepareStatement.setString(3, password);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error");
        }

    }

    @Override
    public void addBettor(String firstname, String lastname, String email) throws SQLException {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(
                    "INSERT INTO bettors(id_user,firstname, lastname,email)  VALUES ((SELECT MAX(id_user) FROM applicationusers),?,?,?)");
            prepareStatement.setString(1, firstname);
            prepareStatement.setString(2, lastname);
            prepareStatement.setString(3, email);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error");
        }

    }

    @Override
    public List<User> getAdminModer() throws SQLException {
        List<User> adminModerator = new ArrayList<User>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM applicationusers WHERE id_group IN (1,2)");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id_user");
                    int idGroup = resultSet.getInt("id_group");
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password_");
                    int blockingStatus = resultSet.getInt("blockingstatus");
                    int authorizationStatus = resultSet.getInt("authorizationstatus");
                    User user = new User(id, idGroup, login, password, blockingStatus, authorizationStatus);
                    adminModerator.add(user);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error");
        }
        return adminModerator;
    }

    @Override
    public void deleteUserByID(int id) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM applicationusers WHERE id_user = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error");
        }

    }

    @Override
    public String getLoginByID(int id) throws SQLException {
        String login = "";
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT login FROM applicationusers WHERE id_user=?");
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    login = resultSet.getString("login");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error");
        }
        return login;
    }

    @Override
    public void udpdateUserByID(int id, String login, String password) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE applicationusers SET login=?, password_=? WHERE id_user=?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error");
        }

    }

}
