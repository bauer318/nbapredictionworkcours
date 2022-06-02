package rsreu.workcours.nbaprediction.bettor;

import rsreu.workcours.nbaprediction.data.DBType;
import rsreu.workcours.nbaprediction.data.Match;
import rsreu.workcours.nbaprediction.data.Prediction;
import rsreu.workcours.nbaprediction.data.dao.DAOFactory;
import rsreu.workcours.nbaprediction.data.dao.PredictionDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PredictionLogic {

    public static List<Prediction> getPredictionsByMatchDate(Date date){
        List<Prediction> predictions = new ArrayList<>();
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            PredictionDAO predictionDAO = factory.getPredictionDAO();
            predictions = predictionDAO.getPredictionsByMatchDate(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return predictions;
    }

    public static  void insertPrediction(int idMatch, double total, double superiority){
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            PredictionDAO predictionDAO = factory.getPredictionDAO();
            predictionDAO.insertPrediction(idMatch, total, superiority);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static  void updatePrediction(int idMatch, double total, double superiority){
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            PredictionDAO predictionDAO = factory.getPredictionDAO();
            predictionDAO.updatePrediction(idMatch, total, superiority);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static  boolean isExistPrediction(int idMatch){
        boolean result = false;
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            PredictionDAO predictionDAO = factory.getPredictionDAO();
            result = predictionDAO.getPredictionByIdMatch(idMatch) != null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
