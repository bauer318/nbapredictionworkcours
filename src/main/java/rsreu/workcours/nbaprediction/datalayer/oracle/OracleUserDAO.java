package rsreu.workcours.nbaprediction.datalayer.oracle;

import rsreu.workcours.nbaprediction.data.dao.ConnectionCloser;
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
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM applicationusers");
            initUserFromDataBase(users, preparedStatement);
        } catch (SQLException e) {
            throw new SQLException("Error");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
        return users;
    }

    private void initUserFromDataBase(List<User> users, PreparedStatement preparedStatement) throws SQLException {
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
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
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
            throw new SQLException("Fail to get user by login");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
        return user;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM applicationusers WHERE id_user=?");
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
            throw new SQLException("Fail to get user bu id");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
        return user;
    }

    @Override
    public void setAuthorizationStatusByLogin(String login, int status) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("UPDATE applicationusers SET authorizationstatus=? WHERE login =?");
            preparedStatement.setInt(1, status);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("fail to set authorization status by login");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public void setUserBlockingStatusById(int id, int blockingStatus) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("UPDATE applicationusers SET blockingstatus=? WHERE id_user =?");
            preparedStatement.setInt(1, blockingStatus);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("fail to set user blocking status by id");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public int getGroupIDByLogin(String login) throws SQLException {
        int groupID = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT id_group FROM applicationusers WHERE login = ?");
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    groupID = resultSet.getInt("id_group");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get groupId by login");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
        return groupID;
    }

    @Override
    public void addAdminModerator(String login, String password, int id_group) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO applicationusers(id_group,login,password_,blockingstatus,authorizationstatus)  VALUES (?,?,?,0,0)");
            preparedStatement.setInt(1, id_group);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to add amin or moderator");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public void addBettor(String firstname, String lastname, String email) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO bettors(id_user,firstname, lastname,email)  VALUES ((SELECT MAX(id_user) FROM applicationusers),?,?,?)");
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to add bettor");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public List<User> getAdminModer() throws SQLException {
        List<User> adminModerator = new ArrayList<User>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT * FROM applicationusers WHERE id_group IN (1,2)");
            initUserFromDataBase(adminModerator, preparedStatement);
        } catch (SQLException e) {
            throw new SQLException("Fail to get admin or moderator");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
        return adminModerator;
    }

    @Override
    public void deleteUserByID(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("DELETE FROM applicationusers WHERE id_user = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to delete user by id");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
    }
    @Override
    public String getLoginByID(int id) throws SQLException {
        String login = "";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT login FROM applicationusers WHERE id_user=?");
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    login = resultSet.getString("login");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get login by id");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
        return login;
    }

    @Override
    public void updateUserByID(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("UPDATE applicationusers SET login=?, password_=? WHERE id_user=?");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to update user by id");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public int getUserIdByLogin(String login) throws SQLException {
        int id = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT id_user FROM applicationusers WHERE login=?");
            preparedStatement.setString(1, login);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    id = resultSet.getInt("id_user");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get user id by login");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
        return id;
    }


}
