package stepik.javaBasicCourse;

import java.util.Arrays;

/**
 * Решение задания 3.5.8 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <code></code>
 *
 * <p>Напишите класс <code>AsciiCharSequence</code>, реализующий компактное хранение последовательности ASCII-символов
 * (их коды влезают в один байт) в массиве байт. По сравнению с классом <code>String</code>, хранящим каждый символ как
 * <code>char</code>, <code>AsciiCharSequence</code> будет занимать в два раза меньше памяти.
 * <p>Класс <code>AsciiCharSequence</code> должен:
 * <p>-реализовывать интерфейс <code>CharSequence</code>;
 * <p>-иметь конструктор, принимающий массив байт;
 * <p>-определять методы <code>length()</code>, <code>charAt()</code>, <code>subSequence()</code> и
 * <code>toString()</code>
 * <p>Сигнатуры методов и ожидания по их поведению смотрите в описании интерфейса <code>CharSequence</code>
 * (JavaDoc или исходники).
 * <p>В данном задании методам <code>charAt()</code> и <code>subSequence()</code> всегда будут подаваться корректные
 * входные параметры, поэтому их проверкой и обработкой ошибок заниматься не нужно. Тем более мы еще не проходили
 * исключения.
 * <p>P.S. В Java 9 ожидается подобная оптимизация в самом классе String: http://openjdk.java.net/jeps/254
 *
 * @author Sergei Ermenkov
 */
public class l3_5_8_AsciiCharSequence {
    public static void main(String[] args) {
        byte[] example = {72, 101, 108, 108, 111, 33};
        AsciiCharSequence answer = new AsciiCharSequence(example);
        System.out.println("Последовательность - " + answer.toString());//Hello!
        System.out.println("Размер её - " + answer.length());//6
        System.out.println("Символ под № 1 - " + answer.charAt(1));//e
        System.out.println("Подпоследовательность - " + answer.subSequence(1, 5));//ello
        //проверка на нарушение инкапсуляции private поля
        System.out.println(answer.toString());//Hello!
        example[0] = 74;
        System.out.println(answer.toString());//Hello!
    }
}

class AsciiCharSequence implements java.lang.CharSequence {
    private byte[] asciiString;

    AsciiCharSequence(byte... chars) {
        asciiString = new byte[chars.length];
        System.arraycopy(chars, 0, asciiString, 0, chars.length);
    }

    @Override
    public int length() {
        return asciiString.length;
    }

    @Override
    public char charAt(int index) {
        return (char) asciiString[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(Arrays.copyOfRange(asciiString, start, end));
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int ch : asciiString) {
            string.append((char) ch);
        }
        return string.toString();
    }

}