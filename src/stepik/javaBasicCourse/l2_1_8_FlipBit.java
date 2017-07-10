package stepik.javaBasicCourse;

/**
 * Решение задания 2.1.8 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте метод <code>flipBit</code>, изменяющий значение одного бита заданного целого числа на противоположное.
 * Данная задача актуальна, например, при работе с битовыми полями.
 * <p>Договоримся, что биты нумеруются от младшего (индекс 1) к старшему (индекс 32).
 * <p>Воспользуйтесь предоставленным шаблоном. Декларацию класса, метод <code>main</code> и обработку ввода-вывода
 * добавит проверяющая система.
 * <blockquote><pre>{@code
 * Sample Input:0 1
 * Sample Output:1
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l2_1_8_FlipBit {
    public static void main(String[] args) {
        System.out.println(flipBit(0, 1));
    }

    /**
     * Flips one bit of the given <code>value</code>.
     *
     * @param value    any number
     * @param bitIndex index of the bit to flip, 1 <= bitIndex <= 32
     * @return new value with one bit flipped
     */
    public static int flipBit(int value, int bitIndex) {
        /*
         * например для value=15, bitIndex=3
         * Чтобы поменять один бит в числе value в позиции bitIndex сначало создаем битовую маску:
         * (1 << bitIndex-1) == (1 << 2) == 0b100
         *
         * далее делаем XOR для числа и маски 15 == 0b1111
         *
         *    0b1111 XOR
         *    0b0100
         *    0b1011
         */
        return value ^ 1 << bitIndex - 1;


    }
}
