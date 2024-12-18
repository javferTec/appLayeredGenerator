package local.javi.app.domain.util.http.impl;


import local.javi.app.common.annotation.domain.Utility;
import local.javi.app.domain.exception.ApiConnectionException;
import local.javi.app.domain.util.http.HttpConnectionFactory;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Utility
@RequiredArgsConstructor
public class HttpConnectionFactoryImpl implements HttpConnectionFactory {

    public HttpURLConnection createConnection(String urlString) throws IOException {
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

}
