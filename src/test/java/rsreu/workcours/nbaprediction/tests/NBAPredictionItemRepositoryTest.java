package rsreu.workcours.nbaprediction.tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import rsreu.workcours.nbaprediction.data.QtTeam;
import rsreu.workcours.nbaprediction.data.UserTypeEnum;
import rsreu.workcours.nbaprediction.decimal.loginlogout.logic.LoginLogic;
import rsreu.workcours.nbaprediction.moderator.logic.AddMatchLogic;
import rsreu.workcours.nbaprediction.moderator.logic.AddPredictionLogic;
import rsreu.workcours.nbaprediction.moderator.logic.RantingLogic;

import java.util.List;

public class NBAPredictionItemRepositoryTest {

    private final int ID_MATCH_POSITIVE = 318;
    private final int ID_MATCH_NEGATIVE = 10000;
    private final int ID_TEAM_POSITIVE = 11;
    private final int ID_TEAM_NEGATIVE = 31;
    private final String LOGIN_POSITIVE = "1";
    private final String LOGIN_NEGATIVE = "-1";
    private final String PASSWORD_POSITIVE = "1";
    private final String PASSWORD_NEGATIVE = "-1";

    @Test
    public void testExistenceLoginPassword(){
        boolean expected = true;
        boolean actual = LoginLogic.checkLogin(LOGIN_POSITIVE,PASSWORD_POSITIVE);
        Assert.assertEquals("Check existence login and password positive test failed",expected,actual);
    }

    @Test
    public void testGetListQtTeamByTeamAvg(){
        List<List<QtTeam>> actual = AddPredictionLogic.getListQtTeamByTeamAVG(ID_TEAM_POSITIVE,25);
        Assert.assertEquals("Get list qt teams test failed", true,actual.size()>0);
    }

    @Test
    public void testNotExistenceResult(){
        boolean expected = true;
        boolean actual = AddPredictionLogic.isNotExistingResult(ID_MATCH_POSITIVE, ID_TEAM_NEGATIVE);
        Assert.assertEquals("Check not existence result test failed", expected, actual);
    }

    @Test
    public void testGetUserRoleById(){
        UserTypeEnum expected = UserTypeEnum.ADMINISTRATOR;
        UserTypeEnum actual = LoginLogic.getUserRoleById(21);
        Assert.assertEquals("Get user role by id test failed", expected, actual);
    }

    @Test
    public void testGetTeamNameById(){
        String expected = "Houston Rockets";
        String actual = AddMatchLogic.getTeamNameById(ID_TEAM_POSITIVE);
        Assert.assertEquals("Get team name by id test failed", expected, actual);
    }

    @Test
    public void testGetLastTeamRatingById(){
        Assert.assertEquals("Get last rating teams by id test failed", true, RantingLogic.getLastRantingByTeamId(ID_TEAM_POSITIVE)!=null);
    }

}
