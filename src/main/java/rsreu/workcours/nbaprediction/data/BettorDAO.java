package rsreu.workcours.nbaprediction.data;

import java.sql.SQLException;
import java.util.List;

public interface BettorDAO {
    List<Bettor> getAllBettors() throws SQLException;
}
