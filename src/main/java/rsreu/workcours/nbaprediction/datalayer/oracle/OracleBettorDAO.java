package rsreu.workcours.nbaprediction.datalayer.oracle;

import rsreu.workcours.nbaprediction.data.dao.BettorDAO;
import rsreu.workcours.nbaprediction.data.Bettor;
import rsreu.workcours.nbaprediction.data.dao.ConnectionCloser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleBettorDAO implements BettorDAO {
    private Connection connection;

    public OracleBettorDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Bettor> getAllBettors() throws SQLException {
        List<Bettor> bettors = new ArrayList<Bettor>();
        PreparedStatement preparedStatement = null;
        try {
             preparedStatement = connection.prepareStatement(
                    "SELECT applicationusers.id_user, login, password_,blockingstatus,authorizationstatus" +
                            ",id_bettor,firstname,lastname,email FROM applicationusers JOIN bettors ON applicationusers.id_user = bettors.id_user");
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    int idUser = resultSet.getInt("id_user");
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password_");
                    int blockingStatus = resultSet.getInt("blockingstatus");
                    int idBettor = resultSet.getInt("id_bettor");
                    String firstname = resultSet.getString("firstname");
                    String lastname = resultSet.getString("lastname");
                    String email = resultSet.getString("email");
                    int authorizationStatus = resultSet.getInt("authorizationstatus");
                    Bettor bettor = new Bettor(idUser,3,login,password,blockingStatus,authorizationStatus,idBettor,email,firstname,lastname);
                    bettors.add(bettor);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get all bettors");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
        return bettors;
    }

    @Override
    public Bettor getBettorByIdUser(int idUser) throws SQLException {
        Bettor bettor = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT applicationusers.id_user, login, password_,blockingstatus,authorizationstatus" +
                            ",id_bettor,firstname,lastname,email " +
                            "FROM applicationusers JOIN bettors ON applicationusers.id_user = bettors.id_user WHERE bettors.id_user=?");
            preparedStatement.setInt(1,idUser);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    String password = resultSet.getString("password_");
                    int blockingStatus = resultSet.getInt("blockingstatus");
                    int idBettor = resultSet.getInt("id_bettor");
                    String login = resultSet.getString("login");
                    String firstname = resultSet.getString("firstname");
                    String lastname = resultSet.getString("lastname");
                    String email = resultSet.getString("email");
                    int authorizationStatus = resultSet.getInt("authorizationstatus");
                    bettor = new Bettor(idUser,3,login,password,blockingStatus,authorizationStatus,idBettor,email,firstname,lastname);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get all bettors");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
        return bettor;
    }

    @Override
    public void updateBettor(Bettor bettor) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE bettors SET firstname=?, lastname=?,email=? WHERE id_user=?");
            preparedStatement.setString(1,bettor.getFirstname());
            preparedStatement.setString(2,bettor.getLastname());
            preparedStatement.setString(3, bettor.getEmail());
            preparedStatement.setInt(4,bettor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void deleteBettorById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("DELETE FROM bettors WHERE id_user = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error");
        }finally {
            ConnectionCloser.closePreparedStatement(preparedStatement);
        }
    }

}
