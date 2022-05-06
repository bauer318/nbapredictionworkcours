package rsreu.workcours.nbaprediction.data.dao;

import rsreu.workcours.nbaprediction.data.Match;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface MatchDAO {
    List<Match> getAllMatchs() throws SQLException;
    List<Match> getMatchsByDate(Date date) throws SQLException;
    int getLastIdMatch() throws SQLException;
    int getIdGuestTeamByIdMatch(int idMatch) throws SQLException;
    int getIdHomeTeamByIdMatch(int idMatch) throws SQLException;
    void insertMatch(int idGuestTeam, int idHomeTeam, Date date) throws SQLException;
    List<Match> getMatchsByHomeTeamGuestTeamMatchDate(int idGuestTeam, int idHomeTeam, Date date) throws SQLException;
}
