package rsreu.workcours.nbaprediction.datalayer.oracle;

import rsreu.workcours.nbaprediction.data.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDBDAOFactory extends DAOFactory {
    private static volatile OracleDBDAOFactory instance;
    private Connection connection;

    public OracleDBDAOFactory() {

    }

    public static OracleDBDAOFactory getInstance() throws ClassNotFoundException, SQLException {
        OracleDBDAOFactory factory = instance;
        if (instance == null) {
            synchronized (OracleDBDAOFactory.class) {
                instance = factory = new OracleDBDAOFactory();
                factory.connected();
            }
        }
        return factory;
    }

    public void connected() throws ClassNotFoundException, SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "SYSTEM";
        String password = "Pass123";
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new SQLException("Database has not been connected");
        }
    }

    @Override
    public UserDAO getUserDAO() {
        return new OracleUserDAO(this.connection);
    }

    @Override
    public BettorDAO getBettorDAO() {
        return new OracleBettorDAO(this.connection);
    }

    @Override
    public MatchDAO getMatchDAO() {
        return new OracleMatchDAO(this.connection);
    }

    @Override
    public QtTeamDAO getQtTeamDAO() {
        return new OracleQtTeamDAO(this.connection);
    }

    @Override
    public RantingDAO getRatingDAO() {
        return new OracleRantingDAO(this.connection);
    }

    @Override
    public ResultDAO getResultDAO() {
        return new OracleResultDAO(this.connection);
    }

    @Override
    public TeamDAO getTeamDAO() {
        return new OracleTeamDAO(this.connection);
    }

    @Override
    public OffensiveRantingDAO getOffensiveRantingDAO() {
        return new OracleOffensiveRantingDAO(this.connection);
    }

    @Override
    public DefensiveRantingDAO getDefensiveRantingDAO() {
        return new OracleDefensiveRantingDAO(this.connection);
    }

}
