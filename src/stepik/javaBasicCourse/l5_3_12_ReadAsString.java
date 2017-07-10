package stepik.javaBasicCourse;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Решение задания 5.3.12 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте метод, который зачитает данные из <code>InputStream</code> и преобразует их в строку, используя заданную
 * кодировку.
 * <p>Пример:
 * <p><code>InputStream</code> последовательно возвращает четыре байта: <code>48 49 50 51</code>.
 * <p>Метод, вызванный для такого <code>InputStream</code> и кодировки ASCII, должен вернуть строку <code>"0123"</code>.
 *
 * @author Sergei Ermenkov
 */
public class l5_3_12_ReadAsString {
    public static void main(String[] args) throws IOException {
        byte[] data = {48, 49, 50, 51};
        InputStream inputStream = new ByteArrayInputStream(data);
        System.out.println(readAsString(inputStream, StandardCharsets.US_ASCII));


    }

    static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        int read;
        StringBuilder string = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, charset);
        while ((read = in.read()) >= 0) {
            string.append((char) read);
        }
        return string.toString();
    }
}
