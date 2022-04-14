package rsreu.workcours.nbaprediction.datalayer.oracle;

import rsreu.workcours.nbaprediction.data.MatchDAO;
import rsreu.workcours.nbaprediction.data.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleMatchDAO implements MatchDAO {
    private Connection connection;

    public OracleMatchDAO(Connection connection) {
        this.connection = connection;
    }

    private void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Match> getAllMatchs() throws SQLException {
        List<Match> matchs = new ArrayList<Match>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM nbaMatchsMod");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idMatch = resultSet.getInt("id_match");
                    int idGuestTeam = resultSet.getInt("id_team1");
                    int idHomeTeam = resultSet.getInt("id_team2");
                    Date matchDate = resultSet.getDate("match_date");
                    Time matchTime = resultSet.getTime("match_date");
                    Match match = new Match(idMatch, idGuestTeam, idHomeTeam, matchDate, matchTime);
                    matchs.add(match);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to getAllMatchs");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return matchs;
    }

    @Override
    public List<Match> getMatchsByDate(Date date) throws SQLException {
        List<Match> matchs = new ArrayList<Match>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT * FROM nbaMatchsMod WHERE TRUNC (match_date) = ? ORDER BY id_match ASC");
            preparedStatement.setDate(1, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idMatch = resultSet.getInt("id_match");
                    int idGuestTeam = resultSet.getInt("id_team1");
                    int idHomeTeam = resultSet.getInt("id_team2");
                    Date matchDate = resultSet.getDate("match_date");
                    Time matchTime = resultSet.getTime("match_date");
                    Match match = new Match(idMatch, idGuestTeam, idHomeTeam, matchDate, matchTime);
                    matchs.add(match);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get matchs by date");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return matchs;
    }

    @Override
    public int getLastIdMatch() throws SQLException {
        int lastIdMatch = 1;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT MAX(id_match) AS lastIdMatch FROM nbaMatchsMod");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    lastIdMatch = resultSet.getInt("lastIdMatch");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get the last id match");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return lastIdMatch;
    }

    @Override
    public int getIdGuestTeamByIdMatch(int idMatch) throws SQLException {
        int idGuestTeam = 1;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT id_team1 FROM nbaMatchsMod WHERE id_match=?");
            preparedStatement.setInt(1, idMatch);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    idGuestTeam = resultSet.getInt("id_team1");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get the guest team id");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return idGuestTeam;
    }

    @Override
    public int getIdHomeTeamByIdMatch(int idMatch) throws SQLException {
        int idHomeTeam = 1;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT id_team2 FROM nbaMatchsMod WHERE id_match=?");
            preparedStatement.setInt(1, idMatch);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    idHomeTeam = resultSet.getInt("id_team2");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get the home team id");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return idHomeTeam;
    }

    @Override
    public void insertMatch(int idGuestTeam, int idHomeTeam, Date date) throws SQLException {
        long dateLong = date.getTime();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("INSERT INTO nbaMatchsMod (id_team1,id_team2,match_date) VALUES (?,?,?)");
            preparedStatement.setInt(1, idGuestTeam);
            preparedStatement.setInt(2, idHomeTeam);
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(dateLong));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to insert new match");
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<Match> getMatchsByHomeTeamGuestTeamMatchDate(int idGuestTeam, int idHomeTeam, Date date)
            throws SQLException {
        List<Match> matchs = new ArrayList<Match>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM nbaMatchsMod WHERE id_team1=? AND id_team2=? AND TRUNC (match_date)=?");
            preparedStatement.setInt(1, idGuestTeam);
            preparedStatement.setInt(2, idHomeTeam);
            preparedStatement.setDate(3, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idMatch = resultSet.getInt("id_match");
                    int idGuestTeamVar = resultSet.getInt("id_team1");
                    int idHomeTeamVar = resultSet.getInt("id_team2");
                    Date matchDate = resultSet.getDate("match_date");
                    Time matchTime = resultSet.getTime("match_date");
                    Match match = new Match(idMatch, idGuestTeamVar, idHomeTeamVar, matchDate, matchTime);
                    matchs.add(match);
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Fail to get matchs");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return matchs;
    }
}
