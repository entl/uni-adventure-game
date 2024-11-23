package tests;

import tests.e2e.GamePlayTest;
import tests.functional.ItemInteractionTest;
import tests.functional.TrapInteractionTest;
import tests.unit.InventoryManagerTest;
import tests.unit.RoomFactoryTest;

public class Run {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide an argument: unit, functional, e2e, or all");
            return;
        }

        String command = args[0].toLowerCase(); // Convert the argument to lowercase to handle case-insensitivity

        switch (command) {
            case "unit":
                runUnitTests();
                break;
            case "functional":
                runFunctionalTests();
                break;
            case "e2e":
                runE2ETests();
                break;
            case "all":
                runAllTests();
                break;
            default:
                System.out.println("Invalid argument. Use 'unit', 'functional', or 'all'.");
                break;
        }
    }

    private static void runUnitTests() {
        System.out.println("Running unit tests...");
        InventoryManagerTest.runAllTests();
        RoomFactoryTest.runAllTests();
    }

    private static void runFunctionalTests() {
        System.out.println("Running functional tests...");
        ItemInteractionTest.runAllTests();
        TrapInteractionTest.runAllTests();
    }

    private static void runE2ETests() {
        System.out.println("Running end-to-end tests...");
        try {
            new GamePlayTest().runAllTests();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runAllTests() {
        System.out.println("Running all tests...");
        runUnitTests();
        runFunctionalTests();
    }
}
