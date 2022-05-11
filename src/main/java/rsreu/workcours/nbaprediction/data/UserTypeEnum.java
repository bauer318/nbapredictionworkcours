package rsreu.workcours.nbaprediction.data;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import rsreu.workcours.nbaprediction.datetime.DateTimeWorker;
import rsreu.workcours.nbaprediction.loginlogout.logic.LoginLogic;
import rsreu.workcours.nbaprediction.moderator.logic.AddMatchLogic;
import rsreu.workcours.nbaprediction.moderator.logic.UpdatePointsLogic;
import rsreu.workcours.nbaprediction.user.logic.EditUserLogic;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
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

        @Override
        public User getUserById(int id) {
            return EditUserLogic.getUserById(id);
        }

        @Override
        public boolean isBettor() {
            return false;
        }

        @Override
        public String getUserRole() {
            return "АДМИНИСТРАТОР";
        }
    },MODERATOR(2) {
        @Override
        public String getUserIndexPage() {
            String page ="/jsp/commands/addMatch.jsp";
            return page;
        }

        @Override
        public void setUserMenu(HttpServletRequest request) {
            int currentHour = LocalTime.now().getHour();
            int currentMinute = LocalTime.now().getMinute();
            LocalTime currentTime = LocalTime.of(currentHour, currentMinute);
            LocalDate currentDate = LocalDate.now();
            HttpSession session = request.getSession();
            session.setAttribute("currentTime", currentTime);
            session.setAttribute("currentDate", currentDate);
            session.setAttribute("nbaTeams", AddMatchLogic.getNbaTeams());
            Date date = DateTimeWorker.localDateToDate(currentDate);
            session.setAttribute("selectedDate", date);
            session.setAttribute("matchsAdded", AddMatchLogic.getMatchsByDate(date));
            UpdatePointsLogic.setQtTeams(AddMatchLogic.getQtTeamsByDate(date));
            session.setAttribute("qtTeams", UpdatePointsLogic.getQtTeams());
        }

        @Override
        public User getUserById(int id) {
            return EditUserLogic.getUserById(id);
        }

        @Override
        public boolean isBettor() {
            return false;
        }

        @Override
        public String getUserRole() {
            return "МОДЕРАТОР";
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

        @Override
        public User getUserById(int id) {
            return EditUserLogic.getBettorByIdUser(id);
        }

        @Override
        public boolean isBettor() {
            return true;
        }

        @Override
        public String getUserRole() {
            return "ИГРОК";
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

        @Override
        public User getUserById(int id) {
            return null;
        }

        @Override
        public boolean isBettor() {
            return false;
        }

        @Override
        public String getUserRole() {
            return "UNKNOW";
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
    public abstract  User getUserById(int id);
    public abstract boolean isBettor();
    public abstract String getUserRole();
    public static UserTypeEnum getUserRole(int idGroup) {
        for(UserTypeEnum userRole : values()) {
            if(idGroup==userRole.idGroup) {
                return userRole;
            }
        }
        return UNKNOW;
    }
}
