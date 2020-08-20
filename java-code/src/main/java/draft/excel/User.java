package draft.excel;

import com.github.crab2died.annotation.ExcelField;
import lombok.Data;

@Data
public class User {

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @ExcelField(title = "名字", order = 1)
    private String name;

    @ExcelField(title = "年龄", order = 2)
    private Integer age;

}
