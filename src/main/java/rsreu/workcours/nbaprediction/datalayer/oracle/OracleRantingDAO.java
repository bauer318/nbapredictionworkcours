package rsreu.workcours.nbaprediction.datalayer.oracle;

import rsreu.workcours.nbaprediction.data.RantingDAO;
import rsreu.workcours.nbaprediction.data.Ranting;

import java.sql.*;

public class OracleRantingDAO implements RantingDAO {

    private Connection connection;

    public OracleRantingDAO(Connection connection) {
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
    public void insertNewRantingTeam(int idTeam, Date date, int numberMatch, int offensive, int defensive,
                                     int totalPoints, int totalOpponentsPoints) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO nbaRantingsMod (id_team,ranting_date,match_number,offensive,defensive,total_points, total_opponents_points) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDate(2, date);
            preparedStatement.setInt(3, numberMatch);
            preparedStatement.setInt(4, offensive);
            preparedStatement.setInt(5, defensive);
            preparedStatement.setInt(6, totalPoints);
            preparedStatement.setInt(7, totalOpponentsPoints);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to insert into nbaRatingsMod");
        } finally {
            closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public Ranting getTeamRantings(int idTeam, Date date) throws SQLException {
        Ranting ranting = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM nbaRantingsMod WHERE id_ranting = (SELECT MAX (id_ranting) FROM nbaRantingsMod WHERE id_team=? AND TRUNC(ranting_date)<? )");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDate(2, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id_ranting = resultSet.getInt("id_ranting");
                    int id_team = resultSet.getInt("id_team");
                    Date ranting_date = resultSet.getDate("ranting_date");
                    int match_number = resultSet.getInt("match_number");
                    int offensive = resultSet.getInt("offensive");
                    int defensive = resultSet.getInt("defensive");
                    int total_points = resultSet.getInt("total_points");
                    int total_opponents_points = resultSet.getInt("total_opponents_points");
                    ranting = new Ranting(id_ranting, id_team, ranting_date, match_number, offensive, defensive,
                            total_points, total_opponents_points);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get team ranting");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return ranting;
    }

    @Override
    public Ranting getTeamRantings(int idTeam) throws SQLException {
        Ranting ranting = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM nbaRantingsMod WHERE id_ranting = (SELECT MAX (id_ranting) FROM nbaRantingsMod WHERE id_team=?)");
            preparedStatement.setInt(1, idTeam);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id_ranting = resultSet.getInt("id_ranting");
                    int id_team = resultSet.getInt("id_team");
                    Date ranting_date = resultSet.getDate("ranting_date");
                    int match_number = resultSet.getInt("match_number");
                    int offensive = resultSet.getInt("offensive");
                    int defensive = resultSet.getInt("defensive");
                    int total_points = resultSet.getInt("total_points");
                    int total_opponents_points = resultSet.getInt("total_opponents_points");
                    ranting = new Ranting(id_ranting, id_team, ranting_date, match_number, offensive, defensive,
                            total_points, total_opponents_points);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get team ranting");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return ranting;
    }

    @Override
    public void updateRantingByIdTeamRantingDate(int idTeam, Date date, int offensive, int defensive)
            throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE nbaRantingsMod SET offensive = ?, defensive=? WHERE id_team=? AND TRUNC (ranting_date) = ?");
            preparedStatement.setInt(1, offensive);
            preparedStatement.setInt(2, defensive);
            preparedStatement.setInt(3, idTeam);
            preparedStatement.setDate(4, date);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to update Ranking");
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void updateRantingByIdTeamRantingDate(int idTeam, Date date, int numberMatch, int offensive, int defensive,
                                                 int totalPoints, int totalOpponentsPoints) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE nbaRantingsMod SET match_number=?,offensive = ?, defensive=?,total_points=?,total_opponents_points=? WHERE id_team=? AND TRUNC (ranting_date) = ?");
            preparedStatement.setInt(1, numberMatch);
            preparedStatement.setInt(2, offensive);
            preparedStatement.setInt(3, defensive);
            preparedStatement.setInt(4, totalPoints);
            preparedStatement.setInt(5, totalOpponentsPoints);
            preparedStatement.setInt(6, idTeam);
            preparedStatement.setDate(7, date);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to update Ranting");
        } finally {
            closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public Ranting getRantingByItTeamRantingDate(int idTeam, Date ranting_date) throws SQLException {
        Ranting ranting = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT * FROM nbaRantingsMod WHERE id_team = ? AND TRUNC (ranting_date) =?");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDate(2, ranting_date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id_ranting = resultSet.getInt("id_ranting");
                    int id_team = resultSet.getInt("id_team");
                    Date rantingDate = resultSet.getDate("ranting_date");
                    int match_number = resultSet.getInt("match_number");
                    int offensive = resultSet.getInt("offensive");
                    int defensive = resultSet.getInt("defensive");
                    int total_points = resultSet.getInt("total_points");
                    int total_opponents_points = resultSet.getInt("total_opponents_points");
                    ranting = new Ranting(id_ranting, id_team, rantingDate, match_number, offensive, defensive,
                            total_points, total_opponents_points);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get team ranting");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return ranting;
    }

}
