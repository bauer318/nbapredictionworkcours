package rsreu.workcours.nbaprediction.data.dao;

import rsreu.workcours.nbaprediction.data.Ranting;

import java.sql.Date;
import java.sql.SQLException;

public interface RantingDAO {
    Ranting getTeamRantings(int idTeam, Date date) throws SQLException;
    Ranting getTeamRantings(int idTeam) throws SQLException;

    void insertNewRantingTeam(int idTeam, Date date, int numberMatch, int offensive, int defensive, int totalPoints,
                              int totalOpponentsPoints) throws SQLException;

    void updateRantingByIdTeamRantingDate(int idTeam, Date date, int offensive, int defensive) throws SQLException;

    void updateRantingByIdTeamRantingDate(int idTeam, Date date, int numberMatch, int offensive, int defensive,
                                          int totalPoints, int totalOpponentsPoints) throws SQLException;

    Ranting getRantingByItTeamRantingDate(int idTeam, Date ranting_date) throws SQLException;
}
