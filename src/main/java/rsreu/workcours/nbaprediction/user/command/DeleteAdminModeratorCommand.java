package rsreu.workcours.nbaprediction.user.command;

import jakarta.servlet.http.HttpServletRequest;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.loginlogout.logic.LoginLogic;
import rsreu.workcours.nbaprediction.data.Bettor;
import rsreu.workcours.nbaprediction.data.User;
import rsreu.workcours.nbaprediction.user.logic.DeleteAdminModeratorLogic;
import rsreu.workcours.nbaprediction.user.logic.EditUserLogic;

import java.util.List;

public class DeleteAdminModeratorCommand implements ActionCommand {
    private static final String PARAM_NAME_ADMIN_MODERATOR_ID = "adminModeratorID";
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/adminEditUser.jsp";
        String idAdminModeratorStr = request.getParameter(PARAM_NAME_ADMIN_MODERATOR_ID);
        int idAdminModerator = Integer.parseInt(idAdminModeratorStr);
        DeleteAdminModeratorLogic.deleteUserByID(idAdminModerator);
        List<User> adminModerators = EditUserLogic.getAdminModerators();
        request.setAttribute("adminModerators", adminModerators);
        List<Bettor> bettors = LoginLogic.getAllBettors();
        request.setAttribute("bettors", bettors);
        return page;
    }
}
