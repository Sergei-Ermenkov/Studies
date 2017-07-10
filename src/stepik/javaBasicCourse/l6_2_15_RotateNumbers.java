package stepik.javaBasicCourse;

import java.util.*;

/**
 * Решение задания 6.2.15 по курсу "Java. Базовый курс" на stepik.org
 * <h3>Примечание: Для окончания ввода данных в консоль необходимо нажать Enter и cmd+d (для mac OS)</h3>
 * <p>Задание:
 * <p>Напишите программу, которая прочитает из <code>System.in</code> последовательность целых чисел, разделенных
 * пробелами, затем удалит из них все числа, стоящие на четных позициях, и затем выведет получившуюся последовательность
 * в обратном порядке в <code>System.out</code>.
 * <p>Все числа влезают в <code>int</code>. Позиции чисел в последовательности нумеруются с нуля.
 * <p>В этом задании надо написать программу целиком, включая <code>import</code>'ы, декларацию класса <code>Main</code>
 * и метода <code>main</code>.
 * <blockquote><pre>{@code
 * Sample Input: 1 2 3 4 5 6 7
 * Sample Output: 6 4 2
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l6_2_15_RotateNumbers {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextInt()) {
                scanner.nextInt();
                if (scanner.hasNextInt()) list.add(Integer.valueOf(scanner.nextInt()));
            }
        }
        Collections.reverse(list);
        list.forEach(x -> System.out.printf("%s ", x));
    }
}