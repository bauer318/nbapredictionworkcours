package rsreu.workcours.nbaprediction.tests;

import static org.assertj.core.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import rsreu.workcours.nbaprediction.moderator.logic.AddPredictionLogic;

public class PredictionProbabilityCalculationTest {

    @Test
    public void testProbabilitySuperiorDecision(){
        String expectedSuperiorDecision = "s";
        String actualSuperiorDecision = AddPredictionLogic.getDecision(32,30.02);
        Assert.assertEquals("Probability superior decision positive test failed",expectedSuperiorDecision,actualSuperiorDecision);
    }

    @Test
    public void testCalculateTotalQt(){
        double expectedTotal = 225;
        double actualTotal = AddPredictionLogic.getTotalPointsMatch(20,25,31,25,18,35,26,45);
        Assert.assertEquals("Calculate total nba match points positive test failed", expectedTotal, actualTotal,0.01);
    }

    @Test
    public void testProbabilityInferiorDecision(){
        String expectedSuperiorDecision = "i";
        String actualSuperiorDecision = AddPredictionLogic.getDecision(30,30.02);
        Assert.assertEquals("Probability inferior decision positive test failed",expectedSuperiorDecision,actualSuperiorDecision);
    }

    @Test
    public void testProbabilityEqualDecision(){
        String expectedSuperiorDecision = "e";
        String actualSuperiorDecision = AddPredictionLogic.getDecision(30,30);
        Assert.assertEquals("Probability equal decision positive test failed",expectedSuperiorDecision,actualSuperiorDecision);
    }

    @Test
    public void testCalculateTeamAverage(){
        double expectedTeamAverage = 25.3;
        double actualTeamAverage = AddPredictionLogic.getTeamAvg(25,20,31);
        Assert.assertEquals("Calculate team average positive test failed",expectedTeamAverage, actualTeamAverage,0.01);
    }

    @Test
    public void testCalculateDifferenceTeamsQt(){
        int expectedDifference = 2;
        int actualDifference = AddPredictionLogic.getDifference(25,20,31,25,18,31);
        Assert.assertEquals("Calculate qt teams difference positive test failed",expectedDifference,actualDifference);
    }

    @Test
    public void testCalculateAveragePercentage(){
        double expectedPercentage = 51.1;
        double actualPercentage = AddPredictionLogic.getAveragePercent(45,23);
        Assert.assertEquals("Calculate average percentage positive test failed",expectedPercentage,actualPercentage,0.01);
    }
}
