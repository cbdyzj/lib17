package draft.js;

import javax.script.*;

import static org.jianzhao.sugar.Sugar.println;

public class JSDemo {

    public static void main(String[] args) throws ScriptException {
        graalJS();
        nashorn();
    }

    public static void graalJS() throws ScriptException {
        var manager = new ScriptEngineManager();
        var engine = manager.getEngineByName("graal.js");
        runScript(engine);
    }

    public static void nashorn() throws ScriptException {
        var manager = new ScriptEngineManager();
        var engine = manager.getEngineByName("nashorn");
        runScript(engine);
    }

    public static void runScript(ScriptEngine engine) throws ScriptException {
        println(engine);
        var s = "print('hello js')";
        engine.eval(s, engine.createBindings());
    }
}
