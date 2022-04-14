package rsreu.workcours.nbaprediction.user.logic;

import rsreu.workcours.nbaprediction.data.DAOFactory;
import rsreu.workcours.nbaprediction.data.DBType;
import rsreu.workcours.nbaprediction.data.UserDAO;
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
}
