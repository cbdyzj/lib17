package draft.curd;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {

    private static final String ROOT = "content";

    public String saveFile(InputStream inputStream, String filename, String... directories) {
        String replaced = this.replaceFilename(filename);
        try {
            Path contextPath = this.getOrCreateDirectories(directories);
            Path newPath = contextPath.resolve(replaced);
            Files.copy(inputStream, newPath);
        } catch (IOException e) {
            throw new RuntimeException("上传文件失败！", e);
        }
        return String.join("/", ROOT, String.join("/", directories), replaced);
    }

    private Path getOrCreateDirectories(String... directories) throws IOException {
        Path contentPath = Paths.get(ROOT, directories).toAbsolutePath().normalize();
        Files.createDirectories(contentPath);
        return contentPath;
    }

    private String replaceFilename(String origin) {
        return UUID.randomUUID().toString().replace("-", "") + origin;
    }

    public Optional<InputStream> findFile(String filePath) throws FileNotFoundException {
        if (!filePath.startsWith(ROOT)) {
            throw new RuntimeException("文件路径异常！");
        }
        Path path = Paths.get(filePath);
        File file = path.toFile();
        if (!file.exists() || file.isDirectory()) {
            return Optional.empty();
        }
        return Optional.of(new FileInputStream(file));
    }
}
