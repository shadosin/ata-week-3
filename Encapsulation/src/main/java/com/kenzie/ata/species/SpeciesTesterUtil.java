package com.kenzie.ata.species;

/**
 * The UtiA tester to see output of the Species class while in the process of encapulating.
 */
public class SpeciesTesterUtil {
    public static void main(String[] args) {
        Species cat = new Species("Asiatic Lion", 20000, 2.0);
        System.out.println("Current " + cat.getName() + " population : " + cat.getPopulation());
        //cat.setPopulation(-2);
        System.out.println(cat.getName() + " population in 100 years: " + cat.predictPopulation(100));

        // TODO: Create dodo bird below this line:
        Species bird = new Species("Dodo Bird", 0, 0.0);
        System.out.println("Current " + bird.getName() + " population : " + bird.getPopulation());
        System.out.println(bird.getName() + " population in 100 years: " + bird.predictPopulation(100));
        
    }


}
