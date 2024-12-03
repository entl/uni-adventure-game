package com.university.utils.input;

import java.util.Scanner;

public class ConsoleInputHandler implements IInputHandler {
    private final Scanner scanner;

    public ConsoleInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }


    @Override
    public void close(){
        scanner.close();
    }
}
