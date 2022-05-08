package rsreu.workcours.nbaprediction.user.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.data.UserTypeEnum;
import rsreu.workcours.nbaprediction.loginlogout.logic.LoginLogic;
import rsreu.workcours.nbaprediction.data.Bettor;
import rsreu.workcours.nbaprediction.data.User;
import rsreu.workcours.nbaprediction.user.logic.EditUserLogic;

import java.util.List;

public class DeleteUserCommand implements ActionCommand {
    private static final String PARAM_NAME_USER_ID = "userId";
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("isDeleteUser",true);
        String page = "/jsp/commands/adminEditUser.jsp";
        String idUserStr = request.getParameter(PARAM_NAME_USER_ID);
        int idUser = Integer.parseInt(idUserStr);
        UserTypeEnum role = LoginLogic.getUserRoleById(idUser);
        User user = role.getUserById(idUser);
        session.setAttribute("clonedUser",user.clone());
        if(role.isBettor()){
            request.setAttribute("disabled","");
            session.setAttribute("isBettor",true);
        }
        request.setAttribute("userRole",role.getUserRole());
        request.setAttribute("regime","Удаление");
        request.setAttribute("action","Удалить");
        request.setAttribute("disabled","disabled");
        List<User> adminModerators = EditUserLogic.getAdminModerators();
        session.setAttribute("adminModerators", adminModerators);
        List<Bettor> bettors = LoginLogic.getAllBettors();
        session.setAttribute("bettors", bettors);
        request.setAttribute("open","open");
        return page;
    }
}
