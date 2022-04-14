package rsreu.workcours.nbaprediction.data;

import java.sql.SQLException;
import java.util.Map;

public interface TeamDAO {
    Map<Integer, String> getAllNbaTeams() throws SQLException;
    String getTeamNameById(int id) throws SQLException;
}
