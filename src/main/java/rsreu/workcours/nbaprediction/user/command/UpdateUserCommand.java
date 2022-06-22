package rsreu.workcours.nbaprediction.user.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.data.Bettor;
import rsreu.workcours.nbaprediction.data.User;
import rsreu.workcours.nbaprediction.decimal.loginlogout.logic.LoginLogic;
import rsreu.workcours.nbaprediction.user.logic.AddUserLogic;
import rsreu.workcours.nbaprediction.user.logic.DeleteUserLogic;
import rsreu.workcours.nbaprediction.user.logic.EditUserLogic;

import java.util.List;

public class UpdateUserCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_FIRSTNAME = "firstname";
    private static final String PARAM_NAME_LASTNAME = "lastname";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_IS_BETTOR = "isBettor";

    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/adminEditUser.jsp";
        HttpSession session = request.getSession(false);
        boolean isDeleteUser = (Boolean)session.getAttribute("isDeleteUser");
        String isBettorStr = request.getParameter(PARAM_NAME_IS_BETTOR);
        boolean isBettor = Boolean.parseBoolean(isBettorStr);
        User clonedUser = (User) session.getAttribute("clonedUser");
        if(isDeleteUser){
            if(isBettor){
                DeleteUserLogic.deleteBettorById(clonedUser.getId());
                List<Bettor> bettors = LoginLogic.getAllBettors();
                session.setAttribute("bettors", bettors);
            }
            DeleteUserLogic.deleteUserByID(clonedUser.getId());
            List<User> adminModerators = EditUserLogic.getAdminModerators();
            session.setAttribute("adminModerators", adminModerators);
            request.setAttribute("open", "");
            return page;
        }
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String firstname = request.getParameter(PARAM_NAME_FIRSTNAME);
        String lastname = request.getParameter(PARAM_NAME_LASTNAME);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String clonedLogin = clonedUser.getLogin();
        request.setAttribute("open", "open");
        if (!clonedLogin.equals(login)) {
            if (AddUserLogic.isLoginExist(login)) {
                request.setAttribute("isExistLogin", true);
                return page;
            }
        }
        if (login.isEmpty() || password.isEmpty()) {
            clonedUser.setLogin(login);
            clonedUser.setPassword(password);
            session.setAttribute("clonedUser",clonedUser);
            request.setAttribute("incorrectData", true);
            return page;
        }
        if (isBettor) {
            Bettor clonedBettor = (Bettor) session.getAttribute("clonedUser");
            if(firstname.isEmpty() || lastname.isEmpty() || email.isEmpty()){
                clonedBettor.setFirstname(firstname);
                clonedBettor.setLastname(lastname);
                clonedBettor.setEmail(email);
                clonedBettor.setLogin(login);
                clonedBettor.setPassword(password);
                session.setAttribute("clonedUser",clonedBettor);
                request.setAttribute("incorrectData", true);
                return page;
            }
        }
        request.setAttribute("open", "");
        int id = clonedUser.getId();
        User user = new User(id, clonedUser.getIdGroup(), login, password, clonedUser.getBlockingStatus(), clonedUser.getAuthorizationStatus());
        EditUserLogic.updateUser(user);
        List<User> adminModerators = EditUserLogic.getAdminModerators();
        session.setAttribute("adminModerators", adminModerators);
        if (isBettor) {
            Bettor clonedBettor = (Bettor) session.getAttribute("clonedUser");
            id = clonedBettor.getId();
            Bettor bettor = new Bettor(id,
                    clonedBettor.getIdGroup(),
                    login,
                    password,
                    clonedUser.getBlockingStatus(),
                    clonedUser.getAuthorizationStatus(),
                    clonedBettor.getIdBettor(),
                    email,
                    firstname,
                    lastname);
            EditUserLogic.updateBettor(bettor);
            List<Bettor> bettors = LoginLogic.getAllBettors();
            session.setAttribute("bettors", bettors);
        }
        return page;
    }
}
