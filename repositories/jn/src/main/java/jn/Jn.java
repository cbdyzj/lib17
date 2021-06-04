package jn;

import com.sun.jna.Library;
import com.sun.jna.Native;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Jn {

    private static final String LIB_PATH = "/jn/native/libjn.dylib";

    // --- JNI

    static {
        loadJnLib();
    }

    @SneakyThrows
    private static void loadJnLib() {
        var resource = new ClassPathResource(LIB_PATH);
        var filename = "";
        if (resource.isFile()) {
            filename = resource.getFile().getAbsolutePath();
        } else {
            var temp = Files.createTempFile(resource.getFilename(), "tmp");
            Files.copy(resource.getInputStream(), temp, REPLACE_EXISTING);
            var file = temp.toFile();
            file.deleteOnExit();
            filename = file.getAbsolutePath();
        }
        System.load(filename);
    }

    public native int fibonacciJni(int n);

    // --- JNA

    public interface JnLibrary extends Library {

        int fibonacciJna(int n);
    }

    public int fibonacciJna(int n) {
        // https://java-native-access.github.io/jna/5.6.0/javadoc/com/sun/jna/NativeLibrary.html
        var instance = Native.load(LIB_PATH, JnLibrary.class);
        return instance.fibonacciJna(n);
    }

    // -- Java

    public int fibonacciJava(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n requires positive");
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return this.fibonacciJava(n - 1) + this.fibonacciJava(n - 2);
    }
}
