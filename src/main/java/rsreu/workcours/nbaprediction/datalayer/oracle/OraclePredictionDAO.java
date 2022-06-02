package rsreu.workcours.nbaprediction.datalayer.oracle;

import rsreu.workcours.nbaprediction.data.Bettor;
import rsreu.workcours.nbaprediction.data.Prediction;
import rsreu.workcours.nbaprediction.data.dao.ConnectionCloser;
import rsreu.workcours.nbaprediction.data.dao.PredictionDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static rsreu.workcours.nbaprediction.data.dao.ConnectionCloser.closePreparedStatement;

public class OraclePredictionDAO implements PredictionDAO {
    private Connection connection;
    public OraclePredictionDAO(Connection connection){
        this.connection = connection;
    }
    @Override
    public List<Prediction> getPredictionsByMatchDate(Date date) throws SQLException {
        List<Prediction> predictions = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT id_match, total, superiority FROM nbapredictions WHERE id_match IN (SELECT id_match FROM nbaMatchsMod WHERE TRUNC (match_date) = ?)");
            preparedStatement.setDate(1, date);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    int idMatch = resultSet.getInt("id_match");
                    double total = resultSet.getDouble("total");
                    double superiority = resultSet.getDouble("superiority");
                    predictions.add(new Prediction(idMatch, total,superiority));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get all predictions by date");
        }finally {
            closePreparedStatement(preparedStatement);
        }
        return predictions;
    }

    @Override
    public void insertPrediction(int idMatch, double total, double superiority) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("INSERT INTO nbapredictions (id_match, total, superiority) VALUES (?,?,?)");
            preparedStatement.setInt(1, idMatch);
            preparedStatement.setDouble(2, total);
            preparedStatement.setDouble(3,superiority);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to insert new prediction");
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void updatePrediction(int idMatch, double total, double superiority) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("UPDATE nbapredictions SET total=?, superiority=? WHERE id_match = ?");
            preparedStatement.setDouble(1, total);
            preparedStatement.setDouble(2, superiority);
            preparedStatement.setInt(3,idMatch);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Fail to update prediction");
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public Prediction getPredictionByIdMatch(int idMatch) throws SQLException {
        Prediction prediction = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM nbapredictions WHERE id_match = ?");
            preparedStatement.setInt(1, idMatch);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    prediction = new Prediction(idMatch);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Fail to get prediction by idMatch");
        }finally {
            closePreparedStatement(preparedStatement);
        }
        return prediction;
    }
}
