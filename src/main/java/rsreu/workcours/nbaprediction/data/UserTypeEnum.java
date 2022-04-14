package rsreu.workcours.nbaprediction.data;

import jakarta.servlet.http.HttpServletRequest;
import rsreu.workcours.nbaprediction.loginlogout.logic.LoginLogic;

import java.util.List;

public enum UserTypeEnum {
    ADMINISTRATOR(1) {
        @Override
        public String getUserIndexPage() {
            String page = "/jsp/commands/adminIndex.jsp";
            return page;
        }

        @Override
        public void setUserMenu(HttpServletRequest request) {
            List<User> users = LoginLogic.getAllUsers();
            request.setAttribute("users", users);

        }
    },MODERATOR(2) {
        @Override
        public String getUserIndexPage() {
            String page ="/jsp/commands/addMatch.jsp";
            return page;
        }

        @Override
        public void setUserMenu(HttpServletRequest request) {
            // TODO Auto-generated method stub

        }
    },BETTOR(3) {
        @Override
        public String getUserIndexPage() {
            String page = "/jsp/commands/bettorIndex.jsp";
            return page;
        }

        @Override
        public void setUserMenu(HttpServletRequest request) {
            // TODO Auto-generated method stub

        }
    },UNKNOW(4) {
        @Override
        public String getUserIndexPage() {
            String page = "/jsp/login.jsp";
            return page;
        }

        @Override
        public void setUserMenu(HttpServletRequest request) {
            // TODO Auto-generated method stub

        }
    };

    private int idGroup;
    UserTypeEnum(int idGroup) {
        this.setIdGroup(idGroup);
    }
    public int getIdGroup() {
        return idGroup;
    }
    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
    public abstract String getUserIndexPage();
    public abstract void setUserMenu(HttpServletRequest request);
    public static UserTypeEnum getUserRole(int idGroup) {
        for(UserTypeEnum userRole : values()) {
            if(idGroup==userRole.idGroup) {
                return userRole;
            }
        }
        return UNKNOW;
    }
}
