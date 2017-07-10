package stepik.javaBasicCourse;

import java.util.function.DoubleUnaryOperator;

/**
 * Решение задания 3.5.7 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте метод, выполняющий численное интегрирование заданной функции на заданном интервале по формуле левых
 * прямоугольников.
 * <p>Функция задана объектом, реализующим интерфейс <code>DoubleUnaryOperator</code>. Его метод
 * <code>applyAsDouble()</code> принимает значение аргумента и возвращает значение функции в заданной точке.
 * <p>Интервал интегрирования задается его конечными точками <code>a</code> и <code>b</code>, причем <code>a<=b</code>.
 * Для получения достаточно точного результата используйте шаг сетки не больше <code>10^−6</code>.
 * <p>Пример:
 * <blockquote><pre>{@code
 * Вызов
 * integrate(x -> 1, 0, 10);
 * должен возвращать значение 10.
 * }</pre></blockquote>
 * <p> P.S. Если задача слишком легкая, то дополнительно можете реализовать динамический выбор шага сетки по следующему
 * алгоритму:
 * <p>1. Вычислить приближенное значение интеграла функции при начальном значении шага сетки (например, 1).
 * <p>2. Вычислить приближенное значение интеграла функции при более мелком шаге сетки (например, уменьшить шаг сетки в
 * два раза).
 * <p>3. Если разность двух последовательных приближений интеграла функции достаточно мала, то завершаем алгоритм и
 * возвращаем текущее приближение в качестве результата.
 * <p>4. Иначе возвращаемся к шагу 2.
 *
 * @author Sergei Ermenkov
 */
public class l3_5_7_Integrate {
    public static void main(String[] args) {
        System.out.println(integrate(x -> 1, 0, 10));//10.0
        System.out.println(integrate(x -> x + 2, 0, 10));//70.0
        System.out.println(integrate(x -> Math.sin(x) / x, 1, 5));//0.603848
    }

    /**
     * Left-rectangle integration with dynamic grid step selection.
     *
     * @param f function is integrated
     * @param a lower bound of integration
     * @param b upper bound of integration
     * @return the value of the integral of the function on the interval
     */
    public static double integrate(DoubleUnaryOperator f, double a, double b) {
        double delta = 1;
        double sumFirst = 0;
        double sumSecond;
        do {
            sumSecond = sumFirst;
            sumFirst = 0;
            for (double x = a; x < b; x += delta) {
                sumFirst += f.applyAsDouble(x) * delta;
            }
            delta /= 2;
        } while (Math.abs(sumFirst - sumSecond) > 1e-6);
        return sumFirst;
    }

}