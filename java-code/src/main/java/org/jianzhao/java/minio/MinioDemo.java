package org.jianzhao.java.minio;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.util.function.SingletonSupplier;

import static org.jianzhao.sugar.Sugar.println;

/**
 * @author cbdyzj
 * @since 2018-08-02
 */
public class MinioDemo {

    private static final String ENDPOINT = "http://localhost:9000";
    private static final String ACCESS_KEY = "access_key";
    private static final String SECRET_KEY = "secret_key";

    private static final SingletonSupplier<MinioClient> clientSupplier = SingletonSupplier.of(() -> {
        try {
            return new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
        } catch (InvalidEndpointException | InvalidPortException ex) {
            throw new IllegalArgumentException(ex);
        }
    });

    public static void main(String... args) {
        MinioClient client = clientSupplier.obtain();
        println(client);
    }
}
