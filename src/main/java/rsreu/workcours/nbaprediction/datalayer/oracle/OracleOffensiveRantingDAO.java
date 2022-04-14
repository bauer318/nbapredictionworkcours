package rsreu.workcours.nbaprediction.datalayer.oracle;

import rsreu.workcours.nbaprediction.data.OffensiveRantingDAO;
import rsreu.workcours.nbaprediction.data.OffensiveRanting;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleOffensiveRantingDAO implements OffensiveRantingDAO {
    private Connection connection;

    public OracleOffensiveRantingDAO(Connection connection) {
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
    public List<OffensiveRanting> getAllOffensiveRantingSortedByDate(Date date) throws SQLException {
        List<OffensiveRanting> offensiveRantings = new ArrayList<OffensiveRanting>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM nbaOffensiveRantings WHERE TRUNC (offensive_ranting_date) = ? ORDER BY offensiveAverage DESC, id_team ASC");
            preparedStatement.setDate(1, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idTeam = resultSet.getInt("id_team");
                    Date ranting_date = resultSet.getDate("offensive_ranting_date");
                    String team = resultSet.getString("team_name");
                    double average = resultSet.getDouble("offensiveAverage");
                    OffensiveRanting offensiveRanting = new OffensiveRanting(idTeam, ranting_date, team, average);
                    offensiveRantings.add(offensiveRanting);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get offensive ranting");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return offensiveRantings;
    }

    @Override
    public void insertOffensiveRanting(int idTeam, Date date, String team, double average) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO nbaOffensiveRantings (id_team, offensive_ranting_date, team_name,offensiveAverage) VALUES (?,?,?,?)");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDate(2, date);
            preparedStatement.setString(3, team);
            preparedStatement.setDouble(4, average);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to insert offensive ranting");
        } finally {
            closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public OffensiveRanting getOffensiveRantingByTeamDate(int idTeam, Date date) throws SQLException {
        OffensiveRanting offensiveRanting = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM nbaOffensiveRantings WHERE id_team = ? AND TRUNC (offensive_ranting_date) = ?");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDate(2, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id_Team = resultSet.getInt("id_team");
                    Date ranting_date = resultSet.getDate("offensive_ranting_date");
                    String team = resultSet.getString("team_name");
                    double average = resultSet.getDouble("offensiveAverage");
                    offensiveRanting = new OffensiveRanting(id_Team, ranting_date, team, average);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get Offensive ranting");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return offensiveRanting;
    }

    @Override
    public void updateOffensiveRanting(int idTeam, Date date, double average) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE nbaOffensiveRantings SET offensiveAverage = ? WHERE id_team = ? AND TRUNC (offensive_ranting_date) = ?");
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
