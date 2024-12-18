package local.javi.app.domain.usecase;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.StringJoiner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import local.javi.app.common.annotation.domain.UseCase;
import local.javi.app.domain.exception.ApiConnectionException;
import local.javi.app.domain.exception.ApiDownloadException;
import local.javi.app.domain.model.project.spring.config.SpringProjectConfig;
import local.javi.app.domain.model.project.spring.config.SpringProjectDependency;
import local.javi.app.domain.util.HttpClient;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@UseCase
@RequiredArgsConstructor
public class SpringProjectCreateUseCase {

    // TODO: Dividir la lógica de creación de proyecto en casos de uso más pequeños
    // TODO: Establecer la ruta donde se guardará el proyecto en application.properties

    private static final String API_BASE_URL = "https://start.spring.io/";
    private final HttpClient httpClient;

    // Metodo principal para crear el proyecto
    public void createProject(SpringProjectConfig springProjectConfig) {
        checkBaseUrlConnectivity();
        List<SpringProjectDependency> dependencies = buildDependencies();
        String url = buildUrl(springProjectConfig, dependencies);
        String zipFileName = generateZipFileName(springProjectConfig);
        String outputFolder = generateOutputFolderName(springProjectConfig);

        downloadProject(url, zipFileName);
        extractZip(zipFileName, outputFolder);
    }

    // Verifica si la URL base es accesible
    private void checkBaseUrlConnectivity() {
        if (!httpClient.isUrlReachable(API_BASE_URL)) {
            throw new ApiConnectionException("Error connecting to Spring Initializr API. The project cannot be created.");
        }
    }

    // Construye la lista de dependencias predeterminadas
    private List<SpringProjectDependency> buildDependencies() {
        return List.of(
                new SpringProjectDependency("web"),
                new SpringProjectDependency("devtools"),
                new SpringProjectDependency("mariadb"),
                new SpringProjectDependency("lombok"),
                new SpringProjectDependency("jdbc"),
                new SpringProjectDependency("actuator")
        );
    }

    // Construye la URL con parámetros de configuración y dependencias
    private String buildUrl(SpringProjectConfig config, List<SpringProjectDependency> dependencies) {
        StringJoiner urlJoiner = new StringJoiner("&", API_BASE_URL + "starter.zip?", "");

        urlJoiner.add("groupId=" + encode(config.getGroupId()))
                .add("artifactId=" + encode(config.getArtifactId()))
                .add("name=" + encode(config.getName()))
                .add("description=" + encode(config.getDescription()))
                .add("packageName=" + encode(config.getPackageName()))
                .add("bootVersion=" + encode(config.getBootVersion()))
                .add("language=" + encode(config.getLanguage()))
                .add("javaVersion=" + encode(config.getJavaVersion()))
                .add("type=" + encode(config.getType()))
                .add("packaging=" + encode(config.getPackaging()))
                .add("dependencies=" + encode(joinDependencies(dependencies)));

        return urlJoiner.toString();
    }

    // Codifica un valor para URL
    private String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    // Une las dependencias en un formato adecuado para la URL
    private String joinDependencies(List<SpringProjectDependency> dependencies) {
        return String.join(",", dependencies.stream().map(SpringProjectDependency::getValue).toArray(String[]::new));
    }

    // Descargar el archivo ZIP
    private void downloadProject(String urlApi, String zipFileName) {
        try (InputStream inputStream = httpClient.getInputStream(urlApi);
             FileOutputStream fileOutputStream = new FileOutputStream(zipFileName)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            throw new ApiDownloadException("Error while downloading the project: " + e.getMessage(), e);
        }
    }

    // Descomprimir el archivo ZIP
    private void extractZip(String zipFileName, String outputFolder) {
        File destDir = new File(outputFolder);

        // Si el directorio no existe, intentamos crearlo
        if (!destDir.exists()) {
            boolean created = destDir.mkdir();  // Capturamos el valor de retorno
            if (!created) {
                throw new ApiDownloadException("Failed to create output directory: " + outputFolder);
            }
        }

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileName))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File file = new File(destDir, entry.getName());
                if (entry.isDirectory()) {
                    // Si es un directorio, lo creamos
                    if (!file.exists() && !file.mkdir()) {
                        throw new ApiDownloadException("Failed to create directory: " + file.getAbsolutePath());
                    }
                } else {
                    // Si es un archivo, lo extraemos
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = zis.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }
                }
                zis.closeEntry();
            }
        } catch (IOException e) {
            throw new ApiDownloadException("Error while extracting the project: " + e.getMessage(), e);
        }

        // Una vez extraído, eliminamos el archivo ZIP
        File zipFile = new File(zipFileName);
        if (!zipFile.delete()) {
            System.err.println("Warning: Failed to delete the ZIP file: " + zipFileName);
        }
    }

    // Genera el nombre del archivo ZIP en base al nombre del proyecto y el ID
    private String generateZipFileName(SpringProjectConfig springProjectConfig) {
        String projectName = springProjectConfig.getName().replace(" ", "-");
        String projectId = String.valueOf(springProjectConfig.getId());
        return projectName + "-" + projectId + ".zip";
    }

    // Genera el nombre de la carpeta donde se descomprimirá el proyecto, ahora incluye el ID
    private String generateOutputFolderName(SpringProjectConfig springProjectConfig) {
        String projectName = springProjectConfig.getName().replace(" ", "-");
        String projectId = String.valueOf(springProjectConfig.getId());
        return projectName + "-" + projectId + "-output";  // Incluye el ID en el nombre de la carpeta
    }

}
