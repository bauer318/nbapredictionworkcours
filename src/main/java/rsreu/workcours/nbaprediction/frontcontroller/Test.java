package rsreu.workcours.nbaprediction.frontcontroller;

import rsreu.workcours.nbaprediction.data.User;
import rsreu.workcours.nbaprediction.data.UserTypeEnum;
import rsreu.workcours.nbaprediction.decimal.loginlogout.logic.LoginLogic;

public class Test {
    public static void main(String args[]){
        UserTypeEnum role = LoginLogic.getUserRoleByLogin("2");
        User user = role.getUserById(61);
        System.out.println(user.getIdGroup());
    }
}
