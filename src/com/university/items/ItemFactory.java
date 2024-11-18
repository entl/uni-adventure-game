package com.university.items;

import java.util.*;
import java.util.function.Supplier;

/**
 * The {@code ItemFactory} class is responsible for creating instances of various items in the game.
 * It stores item suppliers and their respective probabilities, allowing random item creation
 * based on a weighted probability distribution.
 */
public class ItemFactory {
    private Map<String, Supplier<IItem>> itemMap = new HashMap<>();
    private List<ItemProbability> itemProbabilities = new ArrayList<>();
    private Random random = new Random();

    /**
     * Constructs the {@code ItemFactory} and initializes the item map and probability distribution.
     * Items are added to the factory with specific creation probabilities.
     */
    public ItemFactory() {
        addItem("freeze spell", FreezeSpell::new, 0.2);
        addItem("teleportation spell", TeleportationSpell::new, 0.1);
        addItem("hammer", Hammer::new, 0.1);
        addItem("cake", Cake::new, 0.2);
        addItem("sandwich", Sandwich::new, 0.2);
        addItem("spanner", Spanner::new, 0.1);
        addItem("potion", Potion::new, 0.1);
        addItem("alarm clock", AlarmClock::new, 0.1);
        addItem("sausage", Sausage::new, 0.1);
    }

    /**
     * Adds a new item to the factory with the given name, supplier, and probability.
     *
     * @param itemName     The name of the item.
     * @param itemSupplier The supplier responsible for creating an instance of the item.
     * @param probability  The probability with which the item should be created.
     */
    public void addItem(String itemName, Supplier<IItem> itemSupplier, double probability) {
        itemMap.put(itemName, itemSupplier);
        itemProbabilities.add(new ItemProbability(itemSupplier, probability));
    }

    /**
     * Creates an item based on its name.
     *
     * @param itemName The name of the item to create.
     * @return An instance of the item, or {@code null} if no item exists by that name.
     */
    public IItem createItem(String itemName) {
        Supplier<IItem> item = itemMap.get(itemName);
        if (item != null) {
            return item.get();
        }
        return null;
    }

    /**
     * Creates a random item based on the probability distribution of the available items.
     *
     * @return An instance of a randomly chosen item based on probability.
     * @throws IllegalStateException if no item could be created.
     */
    public IItem createRandomItem() {
        double totalProbability = 0.0;
        for (ItemProbability itemProbability : itemProbabilities) {
            totalProbability += itemProbability.getProbability();
        }

        double randomValue = random.nextDouble() * totalProbability;
        double cumulativeProbability = 0.0;

        for (ItemProbability itemProbability : itemProbabilities) {
            cumulativeProbability += itemProbability.getProbability();
            if (randomValue < cumulativeProbability) {
                return itemProbability.getItemSupplier().get();
            }
        }

        throw new IllegalStateException("No item was created");
    }

    /**
     * Checks if an item with the given name exists in the factory.
     *
     * @param itemName The name of the item to check.
     * @return {@code true} if the item exists, {@code false} otherwise.
     */
    public boolean isItem(String itemName) {
        return itemMap.containsKey(itemName);
    }

    /**
     * Finds the best matching item name based on the input tokens.
     *
     * @param words The array of input tokens to match against item names.
     * @return The best matching item name or {@code null} if no match is found.
     */
    public String findBestMatchingItemName(String[] words) {
        String bestMatch = null;
        int highestMatchCount = 0;

        for (String itemName : itemMap.keySet()) {
            int matchCount = 0;

            // Count how many words from the input are present in the item name.
            for (String word : words) {
                if (itemName.contains(word)) {
                    matchCount++;
                }
            }

            if (matchCount > highestMatchCount) {
                highestMatchCount = matchCount;
                bestMatch = itemName;
            }
        }

        return bestMatch;
    }

    /**
     * A helper class used to store item probabilities. It associates a supplier with a probability.
     */
    private static class ItemProbability {
        private Supplier<IItem> itemSupplier;
        private double probability;

        /**
         * Constructs an {@code ItemProbability} with the given supplier and probability.
         *
         * @param itemSupplier The supplier that provides an instance of the item.
         * @param probability  The probability of selecting this item.
         */
        public ItemProbability(Supplier<IItem> itemSupplier, double probability) {
            this.itemSupplier = itemSupplier;
            this.probability = probability;
        }

        /**
         * Returns the supplier responsible for creating the item.
         *
         * @return The item supplier.
         */
        public Supplier<IItem> getItemSupplier() {
            return itemSupplier;
        }

        /**
         * Returns the probability associated with this item.
         *
         * @return The probability of selecting this item.
         */
        public double getProbability() {
            return probability;
        }

    }
}