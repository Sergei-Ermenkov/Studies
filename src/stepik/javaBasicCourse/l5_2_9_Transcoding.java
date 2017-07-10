package stepik.javaBasicCourse;

import java.io.*;

/**
 * Решение задания 5.2.9 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <code></code>
 *
 * <p>По историческим причинам на разных платформах принят разный способ обозначения конца строки в текстовом файле.
 * На Unix-системах конец строки обозначается символом с кодом 10 (<code>'\n'</code>), на Windows — двумя
 * последовательными символами с кодами 13 и 10 (<code>'\r' '\n'</code>).
 * <p>Напишите программу, которая будет преобразовывать переводы строк из формата Windows в формат Unix. Данные в
 * формате Windows подаются программе в <code>System.in</code>, преобразованные данные должны выводиться в
 * <code>System.out</code>. На этот раз вам надо написать программу полностью, т.е. объявить класс (с именем
 * <code>Main</code> — таково ограничение проверяющей системы), метод <code>main</code>, прописать все
 * <code>import</code>'ы.
 * <p>Требуется заменить все вхождения пары символов <code>'\r'</code> и <code>'\n'</code> на один символ
 * <code>'\n'</code>. Если на входе встречается одиночный символ <code>'\r'</code>, за которым не следует
 * <code>'\n'</code>, то символ <code>'\r'</code> выводится без изменения.
 * <p>Кодировка входных данных такова, что символ <code>'\n'</code> представляется байтом 10, а символ <code>'\r'</code>
 * — байтом 13. Поэтому программа может осуществлять фильтрацию на уровне двоичных данных, не преобразуя байты в
 * символы.
 * <p>Из-за буферизации данных в <code>System.out</code> в конце вашей программы надо явно вызвать
 * <code>System.out.flush()</code>. Иначе часть выведенных вами данных не будет видна проверяющей системе.
 * <p>Пример:
 * <blockquote><pre>{@code
 * Из System.in зачитаны следующие байты: 65 13 10 10 13.
 * Внимание! Это не строка "65 13 10 10 13", а последовательность чисел, возвращаемая методом System.in.read().
 *
 * В System.out должны быть выведены байты: 65 10 10 13
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l5_2_9_Transcoding {
    public static void main(String[] args) throws IOException {
        //byte[] data={0x0D,0x0A,0x33,0x0A,0x33,0x0D,0x33,0x45,0x0D,0x0A,0x01};
        byte[] data = {0x65, 0x13 ,0x10 ,0x10 ,0x13};
        InputStream inputStream = new ByteArrayInputStream(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int read = inputStream.read();
        while (read >= 0) {
            if (read == 13) {
                read = inputStream.read();
                if (read == 10) {
                    outputStream.write(read);
                    read = inputStream.read();
                    continue;
                }
                outputStream.write(new byte[]{13, (byte) read});
                read = inputStream.read();
                continue;
            }
            outputStream.write(read);
            read = inputStream.read();
        }
        byte[] result = outputStream.toByteArray();
        System.out.flush();
    }

}
