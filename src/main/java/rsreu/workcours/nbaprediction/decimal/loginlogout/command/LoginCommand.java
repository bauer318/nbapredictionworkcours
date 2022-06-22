package rsreu.workcours.nbaprediction.decimal.loginlogout.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.data.UserTypeEnum;
import rsreu.workcours.nbaprediction.decimal.loginlogout.logic.LoginLogic;

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
            int userId = LoginLogic.getUserIdByLogin(login);
            HttpSession session = request.getSession();
            session.setAttribute("id",userId);
            session.setMaxInactiveInterval(5*3600);
        }else {
            request.setAttribute("wrongloginpass", "Неправильно логин или пароль");
            page="/jsp/commands/login.jsp";
        }
        return page;
    }
}
