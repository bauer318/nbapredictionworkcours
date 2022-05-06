package rsreu.workcours.nbaprediction.data.dao;

import rsreu.workcours.nbaprediction.data.*;

public abstract class DAOFactory  implements  AutoCloseable{
    @Override
    public void close() throws Exception {

    }
    public static DAOFactory getInstance(DBType dbType) throws Exception {
        return dbType.getDAOFactory();
    }

    public abstract UserDAO getUserDAO();

    public abstract BettorDAO getBettorDAO();

    public abstract MatchDAO getMatchDAO();

    public abstract QtTeamDAO getQtTeamDAO();

    public abstract RantingDAO getRatingDAO();

    public abstract ResultDAO getResultDAO();

    public abstract TeamDAO getTeamDAO();

    public abstract OffensiveRantingDAO getOffensiveRantingDAO();

    public abstract DefensiveRantingDAO getDefensiveRantingDAO();
}
