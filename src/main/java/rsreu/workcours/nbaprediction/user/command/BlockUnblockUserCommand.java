package rsreu.workcours.nbaprediction.user.command;

import jakarta.servlet.http.HttpServletRequest;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.data.Bettor;
import rsreu.workcours.nbaprediction.data.User;
import rsreu.workcours.nbaprediction.loginlogout.logic.LoginLogic;
import rsreu.workcours.nbaprediction.user.logic.EditUserLogic;

import java.util.List;

public class BlockUnblockUserCommand implements ActionCommand {
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String PARAM_NAME_USER_BLOCKING_STATUS = "userBlockingStatus";
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/adminEditUser.jsp";
        String userIdStr = request.getParameter(PARAM_NAME_USER_ID);
        String userBlockingStatusStr = request.getParameter(PARAM_NAME_USER_BLOCKING_STATUS);
        int userBlockingStatus = Integer.parseInt(userBlockingStatusStr);
        int newUserBlockingStatus = EditUserLogic.changeUserBlockingStatus(userBlockingStatus);
        int userId = Integer.parseInt(userIdStr);
        EditUserLogic.setUserBlockingStatusById(userId,newUserBlockingStatus);
        List<User> adminModerators = EditUserLogic.getAdminModerators();
        request.setAttribute("adminModerators", adminModerators);
        List<Bettor> bettors = LoginLogic.getAllBettors();
        request.setAttribute("bettors", bettors);
        return page;
    }
}
