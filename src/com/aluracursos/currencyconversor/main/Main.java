package com.aluracursos.currencyconversor.main;

import com.aluracursos.currencyconversor.operations.CurrencySelector;
import com.aluracursos.currencyconversor.operations.CurrencyConverter;



import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String introApp = """
                ***************************************************
                   ¡Welcome to the most top currency conversor!
                ***************************************************
                """ ;
        Scanner scanner = new Scanner(System.in);
        CurrencySelector currencySelector = new CurrencySelector(5); // Límite de 5 intentos
        CurrencyConverter currencyConverter = new CurrencyConverter();

        System.out.println(introApp);
        System.out.println("Available currencies: ");
        for (int i = 0; i < currencySelector.getCurrencyList().size(); i++) {
            System.out.println((i + 1) + ". " + currencySelector.getCurrencyList().get(i));
        }

        // Seleccionar moneda de origen
        String originCurrency = currencySelector.selectCurrency(scanner, "origin");
        if (originCurrency == null) {
            scanner.close();
            return;
        }
        currencySelector.setOriginCurrency(originCurrency);

        // Seleccionar moneda de destino
        String targetCurrency = currencySelector.selectCurrency(scanner, "target");
        if (targetCurrency == null) {
            scanner.close();
            return;
        }
        currencySelector.setTargetCurrency(targetCurrency);

        // Solicitar la cantidad a convertir
        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        // Realizar la conversión
        double convertedAmount = currencyConverter.convert(originCurrency, targetCurrency, amount);

        // Mostrar el resultado
        System.out.printf("The converted amount of $ %.2f  %s to %s is: $ %.2f %s",amount,originCurrency,targetCurrency,convertedAmount,targetCurrency);

        scanner.close();
    }
}


