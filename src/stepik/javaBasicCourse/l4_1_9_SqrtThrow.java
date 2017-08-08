package stepik.javaBasicCourse;

/**
 * Решение задания 4.1.9 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте метод <code>sqrt()</code>, вычисляющий квадратный корень числа. В отличие от <code>Math.sqrt()</code>,
 * это метод при передаче отрицательного параметра должен бросать исключение <code>IllegalArgumentException</code>
 * с сообщением "Expected non-negative number, got ?", где вместо вопросика будет подставлено фактически переданное в
 * метод число.
 *
 * @author Sergei Ermenkov
 */
public class l4_1_9_SqrtThrow {
    public static void main(String[] args) {
        System.out.println(sqrt(0));
    }

    public static double sqrt(double x) {
        if (x < 0) throw new IllegalArgumentException("Expected non-negative number, got " + x);
        return StrictMath.sqrt(x);
    }
}