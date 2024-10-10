package com.aluracursos.currencyconversor.operations;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CurrencySelector {
    private final int maxAttempts;
    private int errorCount = 0;
    private String originCurrency = "";
    private String targetCurrency = "";
    private final ArrayList<String> currencyList;

    public CurrencySelector(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.currencyList = new ArrayList<>();
        currencyList.add("United States Dollar (USD)");
        currencyList.add("Euro (EUR)");
        currencyList.add("Mexican Peso (MXN)");
        currencyList.add("Colombian Peso (COP)");
        currencyList.add("Chilean Peso (CLP)");
        currencyList.add("Argentine Peso (ARS)");
        currencyList.add("Brazilian Real (BRL)");
    }

    public String selectCurrency(Scanner scanner, String type) {
        boolean validInput = false;
        int selectedOption = 0;

        while (!validInput && errorCount < maxAttempts) {
            try {
                System.out.print("Choose the currency of " + type + " (1-" + currencyList.size() + "): ");
                selectedOption = scanner.nextInt();
                if (selectedOption < 1 || selectedOption > currencyList.size()) {
                    throw new IllegalArgumentException("Please, choose a valid option between 1 and " + currencyList.size());
                }
                validInput = true;
            } catch (InputMismatchException e) {
                errorCount++;
                System.out.println("Error: you must type a number between 1 and " + currencyList.size());
                scanner.next(); // Limpiar la entrada inválida
            } catch (IllegalArgumentException e) {
                errorCount++;
                System.out.println(e.getMessage());
            }
        }

        if (errorCount >= maxAttempts) {
            System.out.println("You have reached the maximum number of attempts.");
            return null;
        }

        return currencyCode(selectedOption);
    }

    private String currencyCode(int option) {
        return switch (option) {
            case 1 -> "USD";
            case 2 -> "EUR";
            case 3 -> "MXN";
            case 4 -> "COP";
            case 5 -> "CLP";
            case 6 -> "ARS";
            case 7 -> "BRL";
            default -> null;
        };
    }

    public String getOriginCurrency() {
        return originCurrency;
    }

    public void setOriginCurrency(String originCurrency) {
        this.originCurrency = originCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public ArrayList<String> getCurrencyList() {
        return currencyList;
    }
}

