package rsreu.workcours.nbaprediction.datalayer.oracle;

import rsreu.workcours.nbaprediction.data.DefensiveRantingDAO;
import rsreu.workcours.nbaprediction.data.DefensiveRanting;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleDefensiveRantingDAO implements DefensiveRantingDAO {
    private Connection connection;

    public OracleDefensiveRantingDAO(Connection connection) {
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
    public List<DefensiveRanting> getAllDefensiveRantingSortedByDate(Date date) throws SQLException {
        List<DefensiveRanting> defensiveRantings = new ArrayList<DefensiveRanting>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM nbaDefensiveRantings WHERE TRUNC (defensive_ranting_date) = ? ORDER BY defensiveAverage ASC, id_team ASC");
            preparedStatement.setDate(1, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idTeam = resultSet.getInt("id_team");
                    Date ranting_date = resultSet.getDate("defensive_ranting_date");
                    String team = resultSet.getString("team_name");
                    double average = resultSet.getDouble("defensiveAverage");
                    DefensiveRanting defensiveRanting = new DefensiveRanting(idTeam, ranting_date, team, average);
                    defensiveRantings.add(defensiveRanting);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get defensive ranting");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return defensiveRantings;
    }

    @Override
    public void insertDefensiveRanting(int idTeam, Date date, String team, double average) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO nbaDefensiveRantings (id_team, defensive_ranting_date, team_name,defensiveAverage) VALUES (?,?,?,?)");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDate(2, date);
            preparedStatement.setString(3, team);
            preparedStatement.setDouble(4, average);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to insert defensive ranting");
        } finally {
            closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public DefensiveRanting getDefensiveRantingByTeamDate(int idTeam, Date date) throws SQLException {
        DefensiveRanting defensiveRanting = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM nbaDefensiveRantings WHERE id_team = ? AND TRUNC (defensive_ranting_date) = ?");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDate(2, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id_Team = resultSet.getInt("id_team");
                    Date ranting_date = resultSet.getDate("defensive_ranting_date");
                    String team = resultSet.getString("team_name");
                    double average = resultSet.getDouble("defensiveAverage");
                    defensiveRanting = new DefensiveRanting(id_Team, ranting_date, team, average);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get Defensive ranting");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return defensiveRanting;
    }

    @Override
    public void updateDefensiveRanting(int idTeam, Date date, double average) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE nbaDefensiveRantings SET defensiveAverage = ? WHERE id_team = ? AND TRUNC (defensive_ranting_date) = ?");
            preparedStatement.setDouble(1, average);
            preparedStatement.setInt(2, idTeam);
            preparedStatement.setDate(3, date);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to update Offensive Ranting");
        } finally {
            closePreparedStatement(preparedStatement);
        }

    }
}
