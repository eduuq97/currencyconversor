package com.aluracursos.currencyconversor.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultCurrency {
    public String request(String originCurrency) {
        // Inicializar variables necesarias para la solicitud de la API
        final String APIKEY = "9502018f077ac43a9de4f9da";
        final String URL = "https://v6.exchangerate-api.com/v6/" + APIKEY + "/latest/" + originCurrency;

        try {
            // Instanciar un nuevo cliente y una nueva solicitud
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            // Retorna el body de la respuesta
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error al realizar la solicitud.");
            return null;
        }
    }
}
