package rsreu.workcours.nbaprediction.data.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionCloser {
    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
