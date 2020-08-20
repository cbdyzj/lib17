package draft.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import draft.entity.User;

import static org.jianzhao.sugar.Sugar.*;

public class GroovyDemo {

    public static void main(String[] args) {
        println("evaluate()");
        evaluate();
        println("WithGroovy.with()");
        WithGroovy.with(new User(), """
                setId(0)
                setName("Ada")
                setAge(17)
                println(delegate.toString())
                """);
    }

    public static void evaluate() {
        var binding = new Binding();
        binding.setVariable("name", "Ada");
        binding.setVariable("age", 17);
        var shell = new GroovyShell(binding);
        var script = """
                class User {
                    String name 
                    Integer age
                    
                    User(name, age) {
                        this.name = name
                        this.age = age
                    }
                    
                    def greetings(){
                        "Hi, I am $name, $age."
                    }
                }
                def ada = new User(name, age)
                def c = {
                    println(greetings())
                }
                c.delegate = ada
                c.call()
                """;
        var evaluated = shell.evaluate(script);
        printf("evaluate: %s\n", evaluated);
    }
}
