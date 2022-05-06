package rsreu.workcours.nbaprediction.datalayer.oracle;

import rsreu.workcours.nbaprediction.data.dao.BettorDAO;
import rsreu.workcours.nbaprediction.data.Bettor;

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
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
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

        }
        return bettors;
    }

}
