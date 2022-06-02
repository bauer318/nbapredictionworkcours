package rsreu.workcours.nbaprediction.data.dao;

import rsreu.workcours.nbaprediction.data.QtTeam;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface QtTeamDAO {
    void insertQtTeam(int id_match, int id_team) throws SQLException;

    List<QtTeam> getQtTeamByMatchDate(Date date) throws SQLException;

    void updateQtTeam(int id_match, int id_team, int qt1, int qt2, int qt3, int qt4, int qt5, int newIdTeam) throws SQLException;
    void updateQtTeam(int id_match, int id_team, int qt1, int qt2, int qt3, int qt4, int qt5) throws SQLException;

    int countTeamMatchWithMostQt4BeforeDate(int idTeam, Date date) throws SQLException;

    int countTeamMatchWithQt4BiggestThanAvg(int idTeam, double avg,Date date) throws SQLException;

    List<Integer> getListIdMatchWithBiggestQt4ThanAvg(int idTeam, double avg) throws SQLException;

    List<QtTeam> getListQtTeamByIdMatch(int idMatch) throws SQLException;

    QtTeam getQtTeamByIdTeamIdMatch(int idTeam, int idMatch) throws SQLException;

    double getQt13Average(int idTeam,Date date) throws SQLException;

    void deleteQtTeam(int id_match, int id_team) throws SQLException;
}
