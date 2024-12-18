package local.javi.app.domain.util.http;

import java.io.IOException;
import java.io.InputStream;

public interface HttpClient {
    boolean isUrlReachable(String urlString);
    InputStream getInputStream(String urlString) throws IOException;
}
