package stepik.javaBasicCourse;

import java.math.BigInteger;
import java.util.stream.IntStream;

/**
 * Решение задания 2.4.8 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте метод, вычисляющий факториал заданного натурального числа.
 * <p>Факториал <code>N</code> вычисляется как (<code>1⋅2⋅...⋅N</code>).
 * <p>Поскольку это очень быстро растущая функция, то даже для небольших <code>N</code> вместимости типов
 * <code>int</code> и <code>long</code> очень скоро не хватит. Поэтому будем использовать <code>BigInteger</code>.
 * <p>Воспользуйтесь предоставленным шаблоном. Декларацию класса, метод <code>main</code> и обработку ввода-вывода
 * добавит проверяющая система.
 * <blockquote><pre>{@code
 * Sample Input 1:1
 * Sample Output 1:1
 *
 * Sample Input 2:3
 * Sample Output 2:6
 * }</pre></blockquote
 *
 * @author Sergei Ermenkov
 */
public class l2_4_8_Factorial {
    public static void main(String[] args) {
        System.out.println(factorial(1));
        System.out.println(factorial(3));
        System.out.println(factorialRecurs(1));
        System.out.println(factorialRecurs(3));
        System.out.println(factorialStream(1));
        System.out.println(factorialStream(3));
    }

    /**
     * Calculates factorial of given <code>value</code>.
     *
     * @param value positive number
     * @return factorial of <code>value</code>
     */
    public static BigInteger factorial(int value) {
        BigInteger bInt = BigInteger.ONE;
        for (int i = 1; i <= value; i++) {
            bInt = bInt.multiply(BigInteger.valueOf(i));
        }
        return bInt;
    }

    /**
     * Calculates factorial of given <code>value</code>.
     * <p>Uses a recursive algorithm.
     *
     * @param value positive number
     * @return factorial of <code>value</code>
     */
    public static BigInteger factorialRecurs(int value) {
        if (value == 1) return BigInteger.ONE;
        return factorial(value - 1).multiply(BigInteger.valueOf(value));
    }

    /**
     * Calculates factorial of given <code>value</code>.
     * <p>Uses Stream and functional programming.
     *
     * @param value positive number
     * @return factorial of <code>value</code>
     */
    public static BigInteger factorialStream(int value) {
        return IntStream.rangeClosed(1, value)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}