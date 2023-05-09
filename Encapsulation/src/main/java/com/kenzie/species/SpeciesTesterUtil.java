package com.kenzie.species;

/**
 * The UtiA tester to see output of the Species class while in the process of encapulating.
 */
public class SpeciesTesterUtil {
    public static void main(String[] args) {
        Species cat = new Species("Asiatic Lion", 20000, 2.0);
        System.out.println("Current " + cat.name + " population : " + cat.population);
        cat.population = -2;
        System.out.println(cat.name + " population in 100 years: " + cat.predictPopulation(100));

        // TODO: Create dodo bird below this line:
        
    }

}
