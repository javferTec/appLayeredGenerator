package local.javi.app.domain.util.http;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface HttpConnectionFactory {
    HttpURLConnection createConnection(String urlString) throws IOException;
}
