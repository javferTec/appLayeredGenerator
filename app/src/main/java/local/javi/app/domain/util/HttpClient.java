package local.javi.app.domain.util;

import local.javi.app.common.annotation.domain.Utility;
import local.javi.app.domain.exception.ApiConnectionException;
import local.javi.app.domain.exception.ApiResponseException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Utility
@RequiredArgsConstructor
public class HttpClient { // Clase HttpClient para gestionar las conexiones HTTP

    // Verifica si una URL es accesible
    public boolean isUrlReachable(String urlString) {
        try {
            HttpURLConnection connection = createHttpConnection(urlString);
            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            return false;
        }
    }

    // Obtiene un InputStream de la URL proporcionada
    public InputStream getInputStream(String urlString) throws IOException {
        HttpURLConnection connection = createHttpConnection(urlString);
        connection.setRequestProperty("Accept", "application/zip");
        handleResponseCode(connection.getResponseCode());
        return connection.getInputStream();
    }

    // Crea una conexión HTTP
    private HttpURLConnection createHttpConnection(String urlString) throws IOException {
        try {
            URL url = new URI(urlString).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            return connection;
        } catch (URISyntaxException e) {
            throw new ApiConnectionException("Invalid URI syntax: " + urlString);
        }
    }

    // Maneja el código de respuesta HTTP
    private void handleResponseCode(int responseCode) {
        switch (responseCode) {
            case HttpURLConnection.HTTP_OK: break;
            case HttpURLConnection.HTTP_BAD_REQUEST:
                throw new ApiResponseException("Bad Request: The server could not understand the request.");
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                throw new ApiResponseException("Unauthorized: You do not have permission.");
            case HttpURLConnection.HTTP_FORBIDDEN:
                throw new ApiResponseException("Forbidden: Access is denied.");
            case HttpURLConnection.HTTP_NOT_FOUND:
                throw new ApiResponseException("Not Found: Resource not found.");
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                throw new ApiResponseException("Internal Error: Server error.");
            default:
                throw new ApiResponseException("Unexpected response code: " + responseCode);
        }
    }
}
