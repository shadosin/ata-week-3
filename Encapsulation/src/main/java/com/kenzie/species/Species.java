package com.kenzie.species;

public class Species {
    public String name;
    public int population;
    public double yearlyGrowthRatePercentage;

    public Species(String name, int population, double yearlyGrowthRatePercentage) {
        this.name = name;
        this.population = population;
        this.yearlyGrowthRatePercentage = yearlyGrowthRatePercentage;
    }

    public void setYearlyGrowthRatePercentage(double yearlyGrowthRatePercentage) {
        //TODO: Implement method

    }

    public int predictPopulation(int years) {
        int remainingYears = years;
        int result = 0;
        double predictedPopulation = population;
        while (remainingYears > 0 && predictedPopulation > 0) {
            predictedPopulation += (yearlyGrowthRatePercentage / 100) * predictedPopulation;
            remainingYears--;
        }
        if (predictedPopulation > 0) {
            return (int) predictedPopulation;
        }
        return 0;
    }
}
