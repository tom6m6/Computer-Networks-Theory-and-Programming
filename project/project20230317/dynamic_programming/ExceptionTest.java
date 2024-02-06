package dynamic_programming;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

public class ExceptionTest {

    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("hello"));
        } catch (FileNotFoundException e) {
            System.out.println("捕获文件未找到异常");
        }
    }
}
