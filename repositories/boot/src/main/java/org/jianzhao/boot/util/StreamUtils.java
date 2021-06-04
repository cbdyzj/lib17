package org.jianzhao.boot.util;

import lombok.SneakyThrows;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StreamUtils {

    private static final Charset utf8 = StandardCharsets.UTF_8;

    private StreamUtils() {
    }

    @SneakyThrows
    public static void copyLines(InputStream in, OutputStream out) {
        Assert.notNull(in, "No InputStream specified");
        Assert.notNull(out, "No OutputStream specified");

        var reader = new BufferedReader(new InputStreamReader(in, utf8));
        String l;
        while ((l = reader.readLine()) != null) {
            out.write(l.getBytes(utf8));
            out.write("\n".getBytes(utf8));
            out.flush();
        }
    }
}
