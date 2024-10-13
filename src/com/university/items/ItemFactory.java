package com.university.items;

import java.util.*;
import java.util.function.Supplier;

public class ItemFactory {
    private Map<String, Supplier<IItem>> itemMap = new HashMap<>();
    private List<ItemProbability> itemProbabilities = new ArrayList<>();
    private Random random = new Random();

    // it is easier to store items as a map of item name to item supplier.
    // supplier is a functional interface that takes no arguments and returns an object
    // ::new is a syntax sugar for creating a new instance of an object
    // it is possible to use lambda expressions instead () -> new FreezeSpell()
    public ItemFactory() {
        addItem("freeze spell", FreezeSpell::new, 0.2);
        addItem("teleportation spell", TeleportationSpell::new, 0.2);
        addItem("hammer", Hammer::new, 0.2);
        addItem("cake", Cake::new, 0.4);
    }

    public void addItem(String itemName, Supplier<IItem> itemSupplier, double probability) {
        itemMap.put(itemName, itemSupplier);
        itemProbabilities.add(new ItemProbability(probability, itemSupplier));
    }


    public IItem createItem(String itemName) {
        Supplier<IItem> item = itemMap.get(itemName);
        if (item != null) {
            return item.get();
        }
        return null;
    }


    // create random item using cumulative probability distribution
    // https://stackoverflow.com/questions/9330394/how-to-pick-an-item-by-its-probability
    // https://stackoverflow.com/questions/16489449/select-element-from-array-with-probability-proportional-to-its-value/16490300#16490300
    public IItem createRandomItem() {
        double totalProbability = 0.0;
        for (ItemProbability itemProbability : itemProbabilities) {
            totalProbability += itemProbability.getProbability();
        }

        double randomValue = random.nextDouble() * totalProbability;
        double cummulitiveProbability = 0.0;

        for (ItemProbability itemProbability : itemProbabilities) {
            cummulitiveProbability += itemProbability.getProbability();
            if (randomValue < cummulitiveProbability) {
                return itemProbability.getItemSupplier().get();
            }
        }

        throw new IllegalStateException("No item was created");
    }

    public boolean isItem(String itemName) {
        return itemMap.containsKey(itemName);
    }

    public String findBestMatchingItemName(String[] words) {
        String bestMatch = null;
        int highestMatchCount = 0;

        for (String itemName : itemMap.keySet()) {
            int matchCount = 0;

            // count how many words from the input are present in the item name.
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

    // inner class to store item probabilities
    // it seems easier to store probabilities within item factory
    // From my perspective, if we add probabilities to the item class it will break the single responsibility principle
    private static class ItemProbability {
        private double probability;
        private Supplier<IItem> itemSupplier;

        public ItemProbability(double probability, Supplier<IItem> itemSupplier) {
            this.probability = probability;
            this.itemSupplier = itemSupplier;
        }

        public double getProbability() {
            return probability;
        }

        public Supplier<IItem> getItemSupplier() {
            return itemSupplier;
        }
    }
}
