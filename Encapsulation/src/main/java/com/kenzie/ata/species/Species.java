package com.kenzie.ata.species;

public class Species {
    private String name;
    private int population;
    private double yearlyGrowthRatePercentage;

    public Species(String name, int population, double yearlyGrowthRatePercentage) {
        this.name = name;
        setPopulation(population);
        this.yearlyGrowthRatePercentage = yearlyGrowthRatePercentage;
    }


    public void setYearlyGrowthRatePercentage(double yearlyGrowthRatePercentage) {
        this.yearlyGrowthRatePercentage = yearlyGrowthRatePercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        if(population < 0) throw new IllegalArgumentException("Population must be positive");
        this.population = population;
    }

    public double getYearlyGrowthRatePercentage() {
        return yearlyGrowthRatePercentage;
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
