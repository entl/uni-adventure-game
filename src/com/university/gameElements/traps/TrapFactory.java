package com.university.gameElements.traps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A factory class responsible for creating traps in the game.
 * Traps can be created based on a probability distribution.
 */
public class TrapFactory {
    private Map<String, Supplier<ITrap>> trapsMap = new HashMap<>();
    private List<TrapProbability> trapProbabilities = new ArrayList<>();

    /**
     * Constructs a TrapFactory with predefined traps and their respective probabilities.
     */
    public TrapFactory() {
        addTrap("trap", Trap::new, 0.6);
        addTrap("mad scientist", MadScientists::new, 0.4);
    }

    /**
     * Adds a new trap type to the factory, associating it with a name, supplier, and probability.
     *
     * @param name The name of the trap.
     * @param trapSupplier A supplier that creates instances of the trap.
     * @param probability The probability with which this trap will be selected.
     */
    public void addTrap(String name, Supplier<ITrap> trapSupplier, double probability) {
        trapsMap.put(name, trapSupplier);
        trapProbabilities.add(new TrapProbability(trapSupplier, probability));
    }

    /**
     * Creates a random trap based on the probability distribution of all registered traps.
     *
     * @return A randomly selected trap instance.
     */
    public ITrap createRandomTrap() {
        double totalProbability = 0.0;
        for (TrapProbability trapProbability : trapProbabilities) {
            totalProbability += trapProbability.getProbability();
        }

        double randomValue = Math.random() * totalProbability;
        double cumulativeProbability = 0.0;

        for (TrapProbability trapProbability : trapProbabilities) {
            cumulativeProbability += trapProbability.getProbability();
            if (randomValue < cumulativeProbability) {
                return trapProbability.getTrapSupplier().get();
            }
        }

        throw new IllegalStateException("No trap was created");
    }

    /**
     * A private helper class to store the probability and supplier for a specific trap.
     */
    private static class TrapProbability {
        private Supplier<ITrap> trapSupplier;
        private double probability;

        /**
         * Constructs a TrapProbability with a given supplier and probability.
         *
         * @param trapSupplier A supplier that creates instances of the trap.
         * @param probability The probability with which this trap will be selected.
         */
        public TrapProbability(Supplier<ITrap> trapSupplier, double probability) {
            this.trapSupplier = trapSupplier;
            this.probability = probability;
        }

        /**
         * Gets the supplier that creates instances of the trap.
         *
         * @return The supplier for the trap.
         */
        public Supplier<ITrap> getTrapSupplier() {
            return trapSupplier;
        }

        /**
         * Gets the probability of this trap being selected.
         *
         * @return The probability of the trap.
         */
        public double getProbability() {
            return probability;
        }


    }
}