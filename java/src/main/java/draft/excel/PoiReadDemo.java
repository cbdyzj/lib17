package draft.excel;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;

import java.io.File;

import static org.jianzhao.sugar.Sugar.println;

public class PoiReadDemo {

    public static void main(String... args) throws Exception {
        Assert.notEmpty(args, "参数为空！");
        var wb = (XSSFWorkbook) WorkbookFactory.create(new File(args[0]));
        var st = wb.getSheetAt(0);
        var rowIterator = st.rowIterator();
        while (rowIterator.hasNext()) {
            var row = (XSSFRow) rowIterator.next();
            println(row.toString());
        }
    }
}
