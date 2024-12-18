package local.javi.app.domain.util.handler.impl;

import local.javi.app.common.annotation.domain.Utility;
import local.javi.app.domain.exception.ExtractionException;
import local.javi.app.domain.exception.FileAlreadyExistException;
import local.javi.app.domain.exception.FileCreationException;
import local.javi.app.domain.util.handler.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
@Utility
@RequiredArgsConstructor
public class FileHandlerImpl implements FileHandler {

    @Override
    public void checkIfFileOrFolderExists(String zipFileName, String outputFolder) {
        File zipFile = new File(zipFileName);
        File folder = new File(outputFolder);

        if (zipFile.exists()) {
            throw new FileAlreadyExistException("The ZIP file already exists: " + zipFileName);
        }
        if (folder.exists()) {
            throw new FileAlreadyExistException("The output folder already exists: " + outputFolder);
        }
    }

    @Override
    public void extractZip(String zipFileName, String outputFolder) {
        File destDir = new File(outputFolder);
        if (!destDir.exists() && !destDir.mkdirs()) {
            throw new FileCreationException("Failed to create output directory: " + outputFolder);
        }

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileName))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File file = new File(destDir, entry.getName());
                if (entry.isDirectory()) {
                    if (!file.exists() && !file.mkdirs()) {
                        throw new FileCreationException("Failed to create directory: " + file.getAbsolutePath());
                    }
                } else {
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
            throw new ExtractionException("Error while extracting the project: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.info("El archivo {} ha sido eliminado correctamente.", fileName);
            } else {
                log.error("No se pudo eliminar el archivo {}", fileName);
            }
        } else {
            log.warn("El archivo {} no existe.", fileName);
        }
    }

    @Override
    public void deleteDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            // Obtener todos los archivos dentro del directorio
            File[] files = directory.listFiles();

            if (files != null) {
                // Eliminar todos los archivos y subdirectorios dentro del directorio
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file.getAbsolutePath()); // Llamada recursiva para eliminar subdirectorios
                    } else {
                        boolean deleted = file.delete(); // Intentar eliminar el archivo
                        if (deleted) {
                            log.info("Archivo eliminado: {}", file.getAbsolutePath());
                        } else {
                            log.error("No se pudo eliminar el archivo: {}", file.getAbsolutePath());
                        }
                    }
                }
            }

            // Finalmente eliminar el directorio vacío
            boolean directoryDeleted = directory.delete();
            if (directoryDeleted) {
                log.info("El directorio {} ha sido eliminado correctamente.", directoryPath);
            } else {
                log.error("No se pudo eliminar el directorio {}", directoryPath);
            }
        } else {
            log.warn("El directorio {} no existe o no es un directorio válido.", directoryPath);
        }
    }



}