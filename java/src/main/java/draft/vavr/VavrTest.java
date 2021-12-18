package draft.vavr;

import org.jianzhao.sugar.Sugar;
import org.springframework.util.ResourceUtils;

import static io.vavr.API.Try;

public class VavrTest {

    public static void main(String[] args) {
        Try(() -> ResourceUtils.getFile("file:README.md").exists())
                .andThen(Sugar::println)
                .onFailure(Throwable::printStackTrace);
    }
}
