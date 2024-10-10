package com.aluracursos.currencyconversor.operations;

import  com.aluracursos.currencyconversor.api.ConsultCurrency;
import  com.aluracursos.currencyconversor.api.ExchangeRateResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class CurrencyConverter {
    private final ConsultCurrency consultCurrency;

    public CurrencyConverter() {
        this.consultCurrency = new ConsultCurrency();
    }

    public double convert(String originCurrency, String targetCurrency, double amount) {
        // Hacer la solicitud a la API para obtener las tasas de cambio
        String jsonResponse = consultCurrency.request(originCurrency);

        // Verificar si la respuesta de la API es nula o vacía
        if (jsonResponse == null || jsonResponse.isEmpty()) {
            System.out.println("Error: Could not get a valid response from the API.");
            return 0;
        }

        try {
            // Usar Gson para deserializar la respuesta JSON
            Gson gson = new Gson();
            ExchangeRateResponse response = gson.fromJson(jsonResponse, ExchangeRateResponse.class);

            // Verificar si la respuesta es exitosa
            if (response.getResult().equals("success")) {
                // Obtener la tasa de cambio de la moneda de destino
                Double exchangeRate = response.getConversionRates().get(targetCurrency);

                // Verificar si la tasa de cambio para la moneda de destino es válida
                if (exchangeRate != null) {
                    // Realizar la conversión
                    return amount * exchangeRate;
                } else {
                    System.out.println("Error: Exchange rate not found for currency " + targetCurrency);
                    return 0;
                }
            } else {
                // Si la respuesta no es exitosa
                System.out.println("Error: The API returned a failed status.");
                return 0;
            }
        } catch (JsonSyntaxException e) {
            // Capturar errores en la estructura del JSON
            System.out.println("Error: The API JSON could not be parsed. Check data format.");
            e.printStackTrace();
            return 0;
        } catch (Exception e) {
            // Capturar cualquier otro error inesperado
            System.out.println("Error: An unexpected error occurred during currency conversion.");
            e.printStackTrace();
            return 0;
        }
    }
}


