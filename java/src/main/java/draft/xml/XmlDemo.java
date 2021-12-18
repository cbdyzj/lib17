package draft.xml;

import javax.xml.bind.JAXB;
import java.io.ByteArrayOutputStream;

import static org.jianzhao.sugar.Sugar.println;

public class XmlDemo {

    public static void main(String[] args) {
        Apple apple = new Apple();
        apple.setName("苹果1");
        apple.setType("红富士");
        var os = new ByteArrayOutputStream();

        JAXB.marshal(apple, os);
        println(os.toString());
    }
}
