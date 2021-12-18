package draft.process;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.jianzhao.sugar.Sugar.println;

public class ProcessDemo {

    public static void main(String... args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("ls");
        String string = StreamUtils.copyToString(process.getInputStream(), StandardCharsets.UTF_8);
        println(string);
    }
}

