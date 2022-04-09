package com.asdvek.MinecraftASKimble.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/** Holds the Popomatic state */
public class Popomatic {
    private int diceValue = 1;      // value of the previous dice roll, updated only through naks()

    public int getDiceValue() { return this.diceValue; }

    public Popomatic() {
        System.out.println("Popomatic constructor");
        randomize();
    }
    public Popomatic(int initValue) {
        System.out.println("Popomatic constructor 2");
        this.diceValue = initValue;
    }

    /** Actuate popomatic (at least) three times to randomize its value */
    public int randomize() {
        for (int i = 0; i < 3; i++)
            naks();
        return this.diceValue;
    }
    public int randomize(int naksCount) {
        if (naksCount < 3) throw new IllegalArgumentException("naksCount should be at least 3.");
        for (int i = 0; i < naksCount; i++)
            naks();
        return this.diceValue;
    }

    int naks() {
        ArrayList<Integer> possibleValues = new ArrayList<Integer>() {{
            add(1); add(2); add(3); add(4); add(5); add(6);
        }};

        Random rand = new Random();
        int randomInt = rand.nextInt(1000);

        // The probability of rolling the opposite side.
        // 20,7% according to tactic. nearly 30% according to another study. set to 22% for now
        if (randomInt < 220) {
            int newValue = 7 - this.diceValue;
            this.diceValue = newValue;
            return newValue;
        }

        // The probability of rolling the same number is around 10%
        if (randomInt > 900) {
            return this.diceValue;
        }

        System.out.print(possibleValues);
        System.out.print(" ");
        possibleValues.remove(Integer.valueOf(7 - this.diceValue));
        possibleValues.remove(Integer.valueOf(this.diceValue));
        System.out.println(possibleValues);

        int newValue = possibleValues.get(rand.nextInt(4));
        this.diceValue = newValue;
        System.out.println("Result = " + newValue);
        return newValue;
    }

}
