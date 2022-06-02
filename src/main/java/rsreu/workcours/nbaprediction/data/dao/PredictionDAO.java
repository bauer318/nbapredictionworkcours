package rsreu.workcours.nbaprediction.data.dao;

import rsreu.workcours.nbaprediction.data.Prediction;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface PredictionDAO {
    List<Prediction> getPredictionsByMatchDate(Date date) throws SQLException;
    void insertPrediction(int idMatch, double total, double superiority) throws SQLException;
    void updatePrediction(int idMatch, double total, double superiority) throws SQLException;
    Prediction getPredictionByIdMatch(int idMatch) throws SQLException;
    void deletePredictionByIdMatch(int idMatch) throws  SQLException;
}
