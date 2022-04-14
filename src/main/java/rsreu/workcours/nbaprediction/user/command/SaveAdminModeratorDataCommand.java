package rsreu.workcours.nbaprediction.user.command;

import jakarta.servlet.http.HttpServletRequest;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.loginlogout.logic.LoginLogic;
import rsreu.workcours.nbaprediction.data.Bettor;
import rsreu.workcours.nbaprediction.data.User;
import rsreu.workcours.nbaprediction.user.logic.AddUserLogic;
import rsreu.workcours.nbaprediction.user.logic.EditUserLogic;
import rsreu.workcours.nbaprediction.user.logic.SaveAdminModeratorDataLogic;

import java.util.List;

public class SaveAdminModeratorDataCommand implements ActionCommand {
    private static final String PARAM_NAME_ADMIN_MODERATOR_ID = "adminModeratorID";
    private static final String PARAM_NAME_LOGIN = "userLogin";
    private static final String PARAM_NAME_PASSWORD = "userPassword";
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/adminEditUser.jsp";
        String idAdminModeratorStr = request.getParameter(PARAM_NAME_ADMIN_MODERATOR_ID);
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        int idAdminModerator = Integer.parseInt(idAdminModeratorStr);
        if(!AddUserLogic.isLoginExist(login)) {
            SaveAdminModeratorDataLogic.updateUserByID(idAdminModerator, login, password);
        }else {
            String oldLogin = SaveAdminModeratorDataLogic.getLoginByID(idAdminModerator);
            SaveAdminModeratorDataLogic.updateUserByID(idAdminModerator, oldLogin, password);
        }
        List<User> adminModerators = EditUserLogic.getAdminModerators();
        request.setAttribute("adminModerators", adminModerators);
        List<Bettor> bettors = LoginLogic.getAllBettors();
        request.setAttribute("bettors", bettors);
        return page;
    }
}
