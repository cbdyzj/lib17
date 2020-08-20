package draft.freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.jianzhao.sugar.Sugar.println;

public class FreeMarkerDemo {

    private String renderFileTemplate() throws Exception {
        var configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassForTemplateLoading(FreeMarkerDemo.class, "/templates");
        var template = configuration.getTemplate("demo.ftl");
        var os = new ByteArrayOutputStream();
        var writer = new OutputStreamWriter(os);
        template.process(Collections.singletonMap("name", "Ada"), writer);
        return os.toString(UTF_8);
    }

    private String renderStringTemplate() throws Exception {
        var configuration = new Configuration(Configuration.VERSION_2_3_28);
        var stringTemplateLoader = new StringTemplateLoader();
        configuration.setTemplateLoader(stringTemplateLoader);
        stringTemplateLoader.putTemplate("demo", "<h1>${name}<h1>");
        var template = configuration.getTemplate("demo");
        var os = new ByteArrayOutputStream();
        var writer = new OutputStreamWriter(os);
        template.process(Collections.singletonMap("name", "Ada"), writer);
        return os.toString(UTF_8);
    }

    public static void main(String[] args) throws Exception {
        var demo = new FreeMarkerDemo();
        println(demo.renderFileTemplate());
        println(demo.renderStringTemplate());
    }
}
