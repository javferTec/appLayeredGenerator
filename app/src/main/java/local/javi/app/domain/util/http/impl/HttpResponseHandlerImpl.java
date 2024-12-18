package local.javi.app.domain.util.http.impl;


import local.javi.app.common.annotation.domain.Utility;
import local.javi.app.domain.exception.ApiResponseException;
import local.javi.app.domain.util.http.HttpResponseHandler;
import lombok.RequiredArgsConstructor;

import java.net.HttpURLConnection;

@Utility
@RequiredArgsConstructor
public class HttpResponseHandlerImpl implements HttpResponseHandler {

    public void handleResponseCode(int responseCode) {
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
