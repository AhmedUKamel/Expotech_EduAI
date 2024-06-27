package org.ahmedukamel.eduai.saver.file;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.function.Supplier;

@Component
public class FileSaver {
    public String save(MultipartFile file, Path path) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        Supplier<String> filenameSupplier = () -> "%s.%s".formatted(UUID.randomUUID(), extension);

        String filename;
        do {
            filename = filenameSupplier.get();
        } while (Files.exists(path.resolve(filename)));

        Path filepath = path.resolve(filename);

        Files.copy(file.getInputStream(), filepath);

        return filename;
    }
}