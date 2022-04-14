package rsreu.workcours.nbaprediction.loginlogout.command;

import jakarta.servlet.http.HttpServletRequest;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.data.UserTypeEnum;
import rsreu.workcours.nbaprediction.loginlogout.logic.LoginLogic;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final int AUTHORIZATION_STATUS = 1;
    @Override
    public String execute(HttpServletRequest request) {
        String page=null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        if(LoginLogic.checkLogin(login, password)) {
            LoginLogic.setAuthorizationStatus(login, AUTHORIZATION_STATUS);
            UserTypeEnum role = LoginLogic.getUserRoleByLogin(login);
            LoginLogic.setUserSession(request, login, role);
            role.setUserMenu(request);
            page = role.getUserIndexPage();
        }else {
            request.setAttribute("wrongloginpass", "Неправ");
            page="/jsp/commands/login.jsp";
        }
        return page;
    }
}
