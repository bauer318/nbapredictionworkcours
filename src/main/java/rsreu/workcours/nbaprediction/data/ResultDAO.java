package rsreu.workcours.nbaprediction.data;

import java.sql.SQLException;
import java.util.List;

public interface ResultDAO {
    void insertNewResult(int idMatch, int idTeam, double average, short regime, short offensiveRegime,
                         short deffensiveRegime, short teamOffensive, short opponentDeffensive, double averageDifference,
                         double averagePercent, double mostQt4Percent, double qt13Average, String decision, int matchQt4,
                         short observation, int totalMatch, int interestedMatch) throws SQLException;

    Result getResultByTeamMatch(int idMatch, int idTeam) throws SQLException;

    List<Result> getListResultByMatch(int idMatch) throws SQLException;

    void updateResultByTeamMatch(int idMatch, int idTeam, double average, short regime, short offensiveRegime,
                                 short deffensiveRegime, short teamOffensive, short opponentDeffensive, double averageDifference,
                                 double averagePercent, double mostQt4Percent, double qt13Average, String decision, int matchQt4,
                                 short observation, int totalMatch, int interestedMatch) throws SQLException;

    List<Result> getListPrevResultByRegime(short regime, short offensiveRegime, short deffensiveRegime)
            throws SQLException;
    void updateResultByTeamMatch(int idMatch, int idTeam, int matchQt4, String decision) throws SQLException;
    XY getXY(int idTeam, double avg) throws SQLException;
}
