package org.jianzhao.payroll.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class ExcelTest {

    @Test
    public void testBuildExcel() {
        var builder = new ExcelBuilder(mockHeader(), mockData());
        var excel = builder.build();
        Assertions.assertNotNull(excel);
        Assertions.assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    public void testParseExcel() {
        var builder = new ExcelBuilder(mockHeader(), mockData());
        var excel = builder.build();
        var parser = new ExcelParser(excel);
        var data = parser.parse(headerMapFunc());
        Assertions.assertNotNull(data);
        Assertions.assertIterableEquals(mockData(), data);
        Assertions.assertThrows(IllegalStateException.class, parser::parse);
    }

    private static Function<String, String> headerMapFunc() {
        var mockHeaderList = mockHeader();
        return headerName -> {
            for (Map<String, String> header : mockHeaderList) {
                if (Objects.equals(header.get(ExcelBuilder.HEADER_NAME), headerName)) {
                    return header.get(ExcelBuilder.HEADER_CODE);
                }
            }
            return headerName;
        };
    }

    private static List<Map<String, String>> mockHeader() {
        String codeKey = ExcelBuilder.HEADER_CODE;
        String nameKey = ExcelBuilder.HEADER_NAME;
        return List.of(
                Map.of(codeKey, "id", nameKey, "ID"),
                Map.of(codeKey, "name", nameKey, "名字"),
                Map.of(codeKey, "age", nameKey, "年龄")
        );
    }

    private static List<Map<String, String>> mockData() {
        return List.of(
                Map.of("id", "1", "name", "Ada", "age", "12"),
                Map.of("id", "2", "name", "Bob", "age", "11"),
                Map.of("id", "3", "name", "Cindy", "age", "14"),
                Map.of("id", "4", "name", "David", "age", "10")
        );
    }
}
