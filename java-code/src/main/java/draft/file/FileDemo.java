package draft.file;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;

import static io.vavr.API.println;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.jianzhao.sugar.Sugar.ref;
import static org.jianzhao.sugar.Sugar.use;

public class FileDemo {

    @SneakyThrows
    public static void main(String[] args) {
        var file = new File("REAdME.md");
        var inputStream = new FileInputStream(file);
        var bytesRef = ref();
        use(inputStream, is -> bytesRef[0] = is.readAllBytes());
        var s = new String((byte[]) bytesRef[0], UTF_8);
        println(s);
    }
}
