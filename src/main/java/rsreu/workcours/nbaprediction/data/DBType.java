package rsreu.workcours.nbaprediction.data;

import rsreu.workcours.nbaprediction.data.dao.DAOFactory;
import rsreu.workcours.nbaprediction.datalayer.oracle.OracleDBDAOFactory;

public enum DBType {
    ORACLE {
        public DAOFactory getDAOFactory() throws Exception {
            return OracleDBDAOFactory.getInstance();
        }
    };
    public abstract DAOFactory getDAOFactory() throws Exception;
}
