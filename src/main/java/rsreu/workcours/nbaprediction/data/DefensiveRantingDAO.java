package rsreu.workcours.nbaprediction.data;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface DefensiveRantingDAO {

    List<DefensiveRanting> getAllDefensiveRantingSortedByDate(Date date) throws SQLException;
    void insertDefensiveRanting(int idTeam, Date date, String team, double average) throws SQLException;
    DefensiveRanting getDefensiveRantingByTeamDate(int idTeam, Date date) throws SQLException;
    void updateDefensiveRanting (int idTeam, Date date, double average) throws SQLException;
}
