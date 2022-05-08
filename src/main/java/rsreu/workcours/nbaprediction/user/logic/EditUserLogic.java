package rsreu.workcours.nbaprediction.user.logic;

import rsreu.workcours.nbaprediction.data.Bettor;
import rsreu.workcours.nbaprediction.data.dao.BettorDAO;
import rsreu.workcours.nbaprediction.data.dao.DAOFactory;
import rsreu.workcours.nbaprediction.data.DBType;
import rsreu.workcours.nbaprediction.data.dao.UserDAO;
import rsreu.workcours.nbaprediction.data.User;

import java.util.ArrayList;
import java.util.List;

public class EditUserLogic {
    public static List<User> getAdminModerators(){
        List<User> adminModerators = new ArrayList<User>();
        try(DAOFactory daoFactory = DAOFactory.getInstance(DBType.ORACLE)){
            UserDAO userDAO = daoFactory.getUserDAO();
            adminModerators = userDAO.getAdminModer();
        }catch (Exception e){
            e.printStackTrace();
        }
        return adminModerators;
    }
    public static User getUserById(int id){
        User user = null;
        try(DAOFactory daoFactory=DAOFactory.getInstance(DBType.ORACLE)){
            UserDAO userDAO = daoFactory.getUserDAO();
            user = userDAO.getUserById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    public static boolean isExistUser(User user){
        return user!=null;
    }
    public static Bettor getBettorByIdUser(int idUser){
        Bettor bettor = null;
        try(DAOFactory daoFactory = DAOFactory.getInstance(DBType.ORACLE)){
            BettorDAO bettorDAO = daoFactory.getBettorDAO();
            bettor = bettorDAO.getBettorByIdUser(idUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  bettor;
    }

    public static String getLoginByID(int id) {
        String login = "";
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            UserDAO userDAO = factory.getUserDAO();
            if(id>0) {
                login = userDAO.getLoginByID(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return login;
    }
    public static void updateUser(User user) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            UserDAO userDAO = factory.getUserDAO();
            if(user.getId()>0 && !user.getLogin().isEmpty() && !user.getPassword().isEmpty()) {
                userDAO.updateUserByID(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateBettor(Bettor bettor){
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            BettorDAO bettorDAO = factory.getBettorDAO();
            if(bettor.getIdBettor()>0 && !bettor.getFirstname().isEmpty() && !bettor.getLastname().isEmpty() && !bettor.getEmail().isEmpty()){
                bettorDAO.updateBettor(bettor);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void setUserBlockingStatusById(int id, int blockingStatus){
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            UserDAO userDAO = factory.getUserDAO();
            if(id>0 && (blockingStatus==0 || blockingStatus==1)){
                userDAO.setUserBlockingStatusById(id,blockingStatus);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static int changeUserBlockingStatus(int userBlockingStatus){
        int blockingStatus = userBlockingStatus;
        if(blockingStatus==0){
            blockingStatus = 1;
        }else {
            blockingStatus = 0;
        }
        return blockingStatus;
    }
}
