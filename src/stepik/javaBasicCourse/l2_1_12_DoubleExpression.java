package stepik.javaBasicCourse;

/**
 * Решение задания 2.1.12 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте метод, возвращающий ответ на вопрос: правда ли, что <code>a + b = c</code>?
 * <p>Допустимая погрешность – <code>0.0001 (1E-4)</code>
 * <p>Можно использовать класс <code>Math</code> и его методы. Класс <code>Math</code> доступен всегда, импортировать
 * его не надо.
 * <blockquote><pre>{@code
 * Sample Input:0.1 0.2 0.3
 * Sample Output:true
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l2_1_12_DoubleExpression {
    public static void main(String[] args) {
        System.out.println(doubleExpression(0.1, 0.2, 0.3));
    }

    public static boolean doubleExpression(double a, double b, double c) {
        return Math.abs((a + b) - c) < 1e-4d;
    }
}