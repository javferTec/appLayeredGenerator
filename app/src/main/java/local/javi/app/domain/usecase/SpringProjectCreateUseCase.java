package local.javi.app.domain.usecase;

import local.javi.app.common.annotation.domain.UseCase;
import local.javi.app.domain.configuration.SpringProjectSettings;
import local.javi.app.domain.model.project.spring.config.SpringProjectConfig;
import local.javi.app.domain.model.project.spring.config.SpringProjectDependency;
import local.javi.app.domain.util.build.DependencyBuilder;
import local.javi.app.domain.util.build.UrlBuilder;
import local.javi.app.domain.util.download.Downloader;
import local.javi.app.domain.util.handler.FileHandler;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class SpringProjectCreateUseCase {

    private final DependencyBuilder dependencyBuilder;
    private final UrlBuilder urlBuilder;
    private final FileHandler fileHandler;
    private final Downloader downloader;
    private final SpringProjectSettings springProjectSettings;

    public void execute(SpringProjectConfig config) {
        // Obtener dependencias
        List<SpringProjectDependency> dependencies = dependencyBuilder.buildDependencies();

        // Construir URL usando el API Base URL y la configuración del proyecto
        String url = urlBuilder.buildUrl(springProjectSettings.getApiBaseUrl(), config, dependencies);

        // Construir el nombre del archivo .zip y la carpeta de salida usando el nombre y ID del proyecto
        String projectName = config.getName().replaceAll(" ", "_");
        String zipFileName = projectName + "-" + config.getId() + ".zip"; // Formato: nombre-id.zip
        String outputFolder = springProjectSettings.getOutputDirectory() + "/" + projectName + "-" + config.getId(); // Formato: nombre-id

        // Verificar si el archivo o la carpeta ya existen
        fileHandler.checkIfFileOrFolderExists(zipFileName, outputFolder);

        // Descargar el archivo .zip
        downloader.download(url, zipFileName);

        // Extraer el archivo .zip en la carpeta de salida
        fileHandler.extractZip(zipFileName, outputFolder);

        // Eliminar el archivo .zip después de extraerlo
        fileHandler.deleteFile(zipFileName);

        //Eliminar el directorio creado después de extraer el archivo .zip
        //fileHandler.deleteDirectory(outputFolder);
    }
}
