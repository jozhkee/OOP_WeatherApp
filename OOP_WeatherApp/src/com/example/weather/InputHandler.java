package com.example.weather;

import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String getCity() {
        System.out.print("Enter the city name: ");
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
