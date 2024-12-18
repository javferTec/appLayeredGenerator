package local.javi.app.domain.util.download.impl;

import local.javi.app.common.annotation.domain.Utility;
import local.javi.app.domain.exception.ApiDownloadException;
import local.javi.app.domain.util.download.Downloader;
import local.javi.app.domain.util.http.impl.HttpClientImpl;
import lombok.RequiredArgsConstructor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Utility
@RequiredArgsConstructor
public class HttpDownloaderImpl implements Downloader {

    private final HttpClientImpl httpClient;

    @Override
    public void download(String url, String destination) {
        try (InputStream inputStream = httpClient.getInputStream(url);
             FileOutputStream fileOutputStream = new FileOutputStream(destination)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new ApiDownloadException("Error while downloading: " + e.getMessage(), e);
        }
    }
}