package com.university.elements.boxes;

import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BoxFactory {

    private static final ILogger logger = LoggerFactory.getLogger(BoxFactory.class);

    private Map<String, Supplier<IBox>> boxMap = new HashMap<>();
    private List<BoxProbability> boxProbabilities = new ArrayList<>();

    /**
     * Constructs a BoxFactory with predefined boxes and their respective probabilities
     */
    public BoxFactory() {
        logger.debug("Initializing BoxFactory with predefined boxes");
        addBox("Chest", Chest::new, 0.7);
        addBox("Mimic", MimicChest::new, 0.3);
        logger.info("BoxFactory initialized with " + boxMap.size() + " box types.");
    }

    /**
     * Adds a new box type to the factory, associating it with a name, supplier, and probability
     *
     * @param name The name of the box
     * @param boxSupplier A supplier that creates instances of the box
     * @param probability The probability with which this box will be selected
     */
    public void addBox(String name, Supplier<IBox> boxSupplier, double probability) {
        boxMap.put(name, boxSupplier);
        boxProbabilities.add(new BoxProbability(boxSupplier, probability));
        logger.info("Added new box: " + name + " with probability: " + probability);
    }

    /**
     * Creates a random box based on the probability distribution of all registered box
     *
     * @return A randomly selected box instance
     */
    public IBox createRandomBox() {
        logger.debug("Creating a random box based on probabilities");
        double totalProbability = 0.0;
        for (BoxProbability boxProbability : boxProbabilities) {
            totalProbability += boxProbability.getProbability();
        }

        logger.debug("Total probability calculated: " + totalProbability);
        double randomValue = Math.random() * totalProbability;
        logger.debug("Generated random value: " + randomValue);

        double cumulativeProbability = 0.0;

        for (BoxProbability boxProbability : boxProbabilities) {
            cumulativeProbability += boxProbability.getProbability();
            if (randomValue < cumulativeProbability) {
                IBox box = boxProbability.getBoxSupplier().get();
                logger.info("Random box created: " + box.getClass().getSimpleName());
                return box;
            }
        }

        logger.error("No box was created. This indicates an error in the probability distribution.");
        throw new IllegalStateException("No box was created");
    }

    /**
     * A private helper class to store the probability and supplier for a specific box
     */
    private static class BoxProbability {
        private Supplier<IBox> boxSupplier;
        private double probability;

        /**
         * Constructs a boxProbability with a given supplier and probability
         *
         * @param boxSupplier A supplier that creates instances of the box
         * @param probability The probability with which this box will be selected
         */
        public BoxProbability(Supplier<IBox> boxSupplier, double probability) {
            this.boxSupplier = boxSupplier;
            this.probability = probability;
        }

        /**
         * Gets the supplier that creates instances of the box
         *
         * @return The supplier for the box
         */
        public Supplier<IBox> getBoxSupplier() {
            return boxSupplier;
        }

        /**
         * Gets the probability of this box being selected
         *
         * @return The probability of the box
         */
        public double getProbability() {
            return probability;
        }
    }
}
