package draft;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class FileCutter {

    private static final int DEFAULT_BUFFER_SIZE = 8192;
    // default part suffix
    private static final String PART_SUFFIX = ".part";
    // default unit size is 50MB
    private static final int DEFAULT_UNIT_SIZE = 50_000_000;

    private final int unitSize;

    public FileCutter() {
        this(null);
    }

    public FileCutter(Map<String, Object> options) {
        options = Objects.requireNonNullElse(options, new HashMap<>());
        this.unitSize = (Integer) options.getOrDefault("unitSize", DEFAULT_UNIT_SIZE);
    }

    @SneakyThrows
    public List<String> split(String pathname) {
        var is = new FileInputStream(pathname);
        try (is) {
            var pathnameList = new ArrayList<String>();
            for (int i = 1; ; i++) {
                var partFilename = pathname + PART_SUFFIX + i;
                var os = new FileOutputStream(partFilename);
                try (os) {
                    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
                    long transferred = 0;
                    int read;
                    while ((read = is.read(buffer, 0, DEFAULT_BUFFER_SIZE)) >= 0) {
                        os.write(buffer, 0, read);
                        transferred += read;
                        if (transferred + DEFAULT_BUFFER_SIZE > this.unitSize) {
                            break;
                        }
                    }
                }
                pathnameList.add(partFilename);
                if (read <= 0) {
                    break;
                }
            }
        }
        return pathnameList;
    }

    @SneakyThrows
    public void merge(List<String> partPathnameList, String pathname) {
        var os = new FileOutputStream(pathname);
        try (os) {
            for (String partPathname : partPathnameList) {
                var is = new FileInputStream(partPathname);
                try (is) {
                    is.transferTo(os);
                }
            }
        }
    }
}
