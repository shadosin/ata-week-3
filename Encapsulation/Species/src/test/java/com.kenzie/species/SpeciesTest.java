package test.species;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpeciesTest {

    @Test
    public void setPopulation_withNegative_throwsException () {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Species("test", -2, 0.1));

    }

    @Test
    public void predictPopulation_withNegativeGrowthRate_returnsZero () {
        //GIVEN
        Species negativeGrowthRate = new Species("test", 2, 0.0);
        //WHEN
        negativeGrowthRate.setYearlyGrowthRatePercentage(-1);
        //THEN
        assertEquals(0, negativeGrowthRate.predictPopulation(100), "Expected zero");
    }

}
