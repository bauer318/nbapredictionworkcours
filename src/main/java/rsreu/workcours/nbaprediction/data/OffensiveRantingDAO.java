package rsreu.workcours.nbaprediction.data;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface OffensiveRantingDAO {
    List<OffensiveRanting> getAllOffensiveRantingSortedByDate(Date date) throws SQLException;
    void insertOffensiveRanting(int idTeam, Date date, String team, double average) throws SQLException;
    OffensiveRanting getOffensiveRantingByTeamDate(int idTeam, Date date) throws SQLException;
    void updateOffensiveRanting(int idTeam, Date date, double average) throws SQLException;
}
