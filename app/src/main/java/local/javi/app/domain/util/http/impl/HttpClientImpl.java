package local.javi.app.domain.util.http.impl;

import local.javi.app.common.annotation.domain.Utility;
import local.javi.app.domain.util.http.HttpClient;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

@Utility
@RequiredArgsConstructor
public class HttpClientImpl implements HttpClient { // Clase HttpClientImpl para gestionar las conexiones HTTP

    private final HttpConnectionFactoryImpl connectionFactory;
    private final HttpResponseHandlerImpl responseHandler;

    // Verifica si una URL es accesible
    public boolean isUrlReachable(String urlString) {
        try {
            HttpURLConnection connection = connectionFactory.createConnection(urlString);
            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            return false;
        }
    }

    // Obtiene un InputStream de la URL proporcionada
    public InputStream getInputStream(String urlString) throws IOException {
        HttpURLConnection connection = connectionFactory.createConnection(urlString);
        connection.setRequestProperty("Accept", "application/zip");
        responseHandler.handleResponseCode(connection.getResponseCode());
        return connection.getInputStream();
    }

}
