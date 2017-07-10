package stepik.javaBasicCourse;

import java.io.*;

/**
 * Решение задания 5.2.8 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Напишите метод, читающий входной поток и вычисляющий контрольную сумму прочитанных данных.
 * <p>Контрольная сумма данных вычисляется по следующему алгоритму:
 * <p>1. Контрольная сумма представляет собой число типа <code>int</code>. Контрольная сумма пустого набора данных равна
 * нулю.
 * <p>2. Контрольная сумма непустого набора данных вычисляется по следующей рекуррентной формуле:
 * <blockquote><pre>{@code
 * Cn+1=rotateLeft(Cn) xor bn+1
 *     , где Cn — контрольная сумма первых n байт данных
 *     rotateLeft — циклический сдвиг бит числа на один бит влево (чтобы не изобретать велосипед, используйте Integer.rotateLeft)
 *     bn — n-ный байт данных.
 * }</pre></blockquote>
 * <p>Поскольку метод не открывал данный <code>InputStream</code>, то и закрывать его он не должен. Выброшенное из
 * методов <code>InputStream</code> исключение должно выбрасываться из метода.
 * <p>Пример:
 * <blockquote><pre>{@code
 * На вход подан InputStream, последовательно возвращающий три байта: 0x33 0x45 0x01.
 * В качестве контрольной суммы должно быть возвращено число 71.
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l5_2_8_CheckSumOfStream {
    public static void main(String[] args) throws IOException {
        byte[] data = {0x33, 0x45, 0x01};
        InputStream inputStream = new ByteArrayInputStream(data);
        System.out.println(checkSumOfStream(inputStream));
    }

    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int checkSum = 0;
        int read = inputStream.read();
        while (read >= 0) {
            checkSum = Integer.rotateLeft(checkSum, 1) ^ read;
            read = inputStream.read();
        }
        return checkSum;
    }
}
