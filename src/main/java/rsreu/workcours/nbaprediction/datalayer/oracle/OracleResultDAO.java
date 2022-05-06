package rsreu.workcours.nbaprediction.datalayer.oracle;

import rsreu.workcours.nbaprediction.data.dao.ResultDAO;
import rsreu.workcours.nbaprediction.data.Result;
import rsreu.workcours.nbaprediction.data.XY;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleResultDAO implements ResultDAO {

    private Connection connection;

    public OracleResultDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Result> getListResultByMatch(int idMatch) throws SQLException {
        List<Result> results = new ArrayList<Result>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM nbaResultMod WHERE id_match=?");
            preparedStatement.setInt(1, idMatch);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id_match = resultSet.getInt("id_match");
                    int id_team = resultSet.getInt("id_team");
                    double average = resultSet.getDouble("average");
                    short regime = resultSet.getShort("regime");
                    short offensive_regime = resultSet.getShort("offensive_regime");
                    short deffensive_regime = resultSet.getShort("deffensive_regime");
                    short team_offensive = resultSet.getShort("team_offensive");
                    short opponent_deffensive = resultSet.getShort("opponent_defensive");
                    double averageDifference = resultSet.getDouble("average_difference");
                    double averagePercent = resultSet.getDouble("average_percent");
                    double mostQt4Percent = resultSet.getDouble("most_qt4_percent");
                    double qt13Average = resultSet.getDouble("qt13_average");
                    String decision = resultSet.getString("decision");
                    int matchQt4 = resultSet.getInt("match_qt4");
                    short observation = resultSet.getShort("observation");
                    int totalMatch = resultSet.getInt("total_match");
                    int interestedMatch = resultSet.getInt("interested_match");
                    Result result = new Result(id_match, id_team, average, regime, offensive_regime, deffensive_regime,
                            team_offensive, opponent_deffensive, averageDifference, averagePercent, mostQt4Percent,
                            qt13Average, decision, matchQt4, observation, totalMatch, interestedMatch);
                    results.add(result);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get list result by id match");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return results;
    }

    @Override
    public void insertNewResult(int idMatch, int idTeam, double average, short regime, short offensiveRegime,
                                short deffensiveRegime, short teamOffensive, short opponentDeffensive, double averageDifference,
                                double averagePercent, double mostQt4Percent, double qt13Average, String decision, int matchQt4,
                                short observation, int totalMatch, int interestedMatch) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("INSERT INTO nbaResultMod VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, idMatch);
            preparedStatement.setInt(2, idTeam);
            preparedStatement.setDouble(3, average);
            preparedStatement.setShort(4, regime);
            preparedStatement.setShort(5, offensiveRegime);
            preparedStatement.setShort(6, deffensiveRegime);
            preparedStatement.setShort(7, teamOffensive);
            preparedStatement.setShort(8, opponentDeffensive);
            preparedStatement.setDouble(9, averageDifference);
            preparedStatement.setDouble(10, averagePercent);
            preparedStatement.setDouble(11, mostQt4Percent);
            preparedStatement.setDouble(12, qt13Average);
            preparedStatement.setString(13, decision);
            preparedStatement.setInt(14, matchQt4);
            preparedStatement.setShort(15, observation);
            preparedStatement.setInt(16, totalMatch);
            preparedStatement.setInt(17, interestedMatch);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to insert Result");
        } finally {
            closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public Result getResultByTeamMatch(int idMatch, int idTeam) throws SQLException {
        Result result = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT regime, offensive_regime, deffensive_regime, average FROM nbaResultMod WHERE id_match=? AND id_team=? ORDER BY id_match ASC");
            preparedStatement.setInt(1, idMatch);
            preparedStatement.setInt(2, idTeam);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    short regime = resultSet.getShort("regime");
                    short offensiveRegime = resultSet.getShort("offensive_regime");
                    short defensiveRegime = resultSet.getShort("deffensive_regime");
                    result = new Result(regime, offensiveRegime, defensiveRegime);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to update Result");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return result;
    }

    @Override
    public void updateResultByTeamMatch(int idMatch, int idTeam, double average, short regime, short offensiveRegime,
                                        short deffensiveRegime, short teamOffensive, short opponentDeffensive, double averageDifference,
                                        double averagePercent, double mostQt4Percent, double qt13Average, String decision, int matchQt4,
                                        short observation, int totalMatch, int interestedMatch) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE nbaResultMod SET decision=?, match_qt4=?,observation=?, average=?,regime=?,offensive_regime=?, "
                            + "deffensive_regime=?,team_offensive=?,opponent_defensive=?,average_difference=?,average_percent=?,"
                            + "most_qt4_percent=?, qt13_average=?,total_match=?,interested_match=? WHERE id_match=? AND id_team=?");
            preparedStatement.setString(1, decision);
            preparedStatement.setInt(2, matchQt4);
            preparedStatement.setShort(3, observation);
            preparedStatement.setDouble(4, average);
            preparedStatement.setShort(5, regime);
            preparedStatement.setShort(6, offensiveRegime);
            preparedStatement.setShort(7, deffensiveRegime);
            preparedStatement.setShort(8, teamOffensive);
            preparedStatement.setShort(9, opponentDeffensive);
            preparedStatement.setDouble(10, averageDifference);
            preparedStatement.setDouble(11, averagePercent);
            preparedStatement.setDouble(12, mostQt4Percent);
            preparedStatement.setDouble(13, qt13Average);
            preparedStatement.setInt(14, totalMatch);
            preparedStatement.setInt(15, interestedMatch);
            preparedStatement.setInt(16, idMatch);
            preparedStatement.setInt(17, idTeam);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to update Result");
        } finally {
            closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public List<Result> getListPrevResultByRegime(short regime, short offensiveRegime, short deffensiveRegime)
            throws SQLException {
        List<Result> results = new ArrayList<Result>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT id_match, decision,average,match_qt4,average_difference FROM nbaResultMod WHERE regime=? AND offensive_regime=? AND deffensive_regime=? ORDER BY id_match ASC ");
            preparedStatement.setShort(1, regime);
            preparedStatement.setShort(2, offensiveRegime);
            preparedStatement.setShort(3, deffensiveRegime);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String decision = resultSet.getString("decision");
                    double average = resultSet.getDouble("average");
                    int matchQt4 = resultSet.getInt("match_qt4");
                    double avgDifference = resultSet.getDouble("average_difference");
                    Result result = new Result(decision, average, matchQt4, avgDifference);
                    results.add(result);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get result by regime");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return results;
    }

    @Override
    public void updateResultByTeamMatch(int idMatch, int idTeam, int matchQt4, String decision) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("UPDATE nbaResultMod SET decision=?, match_qt4=? WHERE id_match=? AND id_team=?");
            preparedStatement.setString(1, decision);
            preparedStatement.setInt(2, matchQt4);
            preparedStatement.setInt(3, idMatch);
            preparedStatement.setInt(4, idTeam);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to update result");
        } finally {
            closePreparedStatement(preparedStatement);
        }

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
    public XY getXY(int idTeam, double avg) throws SQLException {
        XY xy = null;
        int y1 = 0;
        int x1 = 0;
        int y2 = 0;
        int x2 = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT count(*) AS countMatch FROM nbaresultmod where id_team=? "
                            + "AND average>=? AND match_qt4>=? AND average_difference>=0 AND decision!=?");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDouble(2, avg);
            preparedStatement.setDouble(3, avg);
            preparedStatement.setString(4, "n");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    y1 = resultSet.getInt("countMatch");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to count match for y1");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT count(*) AS countMatch FROM nbaresultmod where id_team=? "
                            + "AND average>=? AND match_qt4<=? AND average_difference>=0 AND decision!=?");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDouble(2, avg);
            preparedStatement.setDouble(3, avg);
            preparedStatement.setString(4, "n");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    x1 = resultSet.getInt("countMatch");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to count match for y2");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT count(*) AS countMatch FROM nbaresultmod where id_team=? "
                            + "AND average>=? AND match_qt4>=? AND average_difference<=0 AND decision!=?");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDouble(2, avg);
            preparedStatement.setDouble(3, avg);
            preparedStatement.setString(4, "n");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    y2 = resultSet.getInt("countMatch");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to count match for x1");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT count(*) AS countMatch FROM nbaresultmod where id_team=? "
                            + "AND average>=? AND match_qt4<=? AND average_difference<=0 AND decision!=?");
            preparedStatement.setInt(1, idTeam);
            preparedStatement.setDouble(2, avg);
            preparedStatement.setDouble(3, avg);
            preparedStatement.setString(4, "n");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    x2 = resultSet.getInt("countMatch");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to count match for x2");
        } finally {
            closePreparedStatement(preparedStatement);
        }
        xy = new XY(y1, x1, y2, x2);
        return xy;
    }

}
