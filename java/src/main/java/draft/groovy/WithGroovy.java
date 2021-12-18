package draft.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class WithGroovy {

    public static <T> void with(T t, String block) {
        var binding = new Binding();
        binding.setVariable("_this", t);
        var shell = new GroovyShell(binding);
        var script = """
                def block = { 
                    %s
                }
                block.delegate = _this
                block.call()
                """.formatted(block);
        shell.evaluate(script);
    }
}
