package com.university.gameElements.traps;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class TrapFactory {
    private Map<String, Supplier<ITrap>> trapsMap = new HashMap<>();
    private List<TrapProbability> trapProbabilities = new ArrayList<>();


    public TrapFactory() {
        addTrap("trap", Trap::new, 0.6);
        addTrap("mad scientist", MadScientists::new, 0.4);
    }

    public void addTrap(String name, Supplier<ITrap> trapSupplier, double probability) {
        trapsMap.put(name, trapSupplier);
        trapProbabilities.add(new TrapProbability(trapSupplier, probability));
    }

    public ITrap createRandomTrap() {
        double totalProbability = 0.0;
        for (TrapProbability trapProbability : trapProbabilities) {
            totalProbability += trapProbability.getProbability();
        }

        double randomValue = Math.random() * totalProbability;
        double cummulativeProbability = 0.0;

        for (TrapProbability trapProbability : trapProbabilities) {
            cummulativeProbability += trapProbability.getProbability();
            if (randomValue < cummulativeProbability) {
                return trapProbability.getTrapSupplier().get();
            }
        }

        throw new IllegalStateException("No trap was created");
    }


    private static class TrapProbability {
        private Supplier<ITrap> trapSupplier;
        private double probability;

        public TrapProbability(Supplier<ITrap> trapSupplier, double probability) {
            this.trapSupplier = trapSupplier;
            this.probability = probability;
        }

        public Supplier<ITrap> getTrapSupplier() {
            return trapSupplier;
        }

        public double getProbability() {
            return probability;
        }
    }
}
