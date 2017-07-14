package stepik.javaBasicCourse;

import java.util.stream.IntStream;

/**
 * Решение задания 6.4.11 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Напишите метод, возвращающий стрим псевдослучайных целых чисел. Алгоритм генерации чисел следующий:
 * <p>- Берется какое-то начальное неотрицательное число (оно будет передаваться в ваш метод проверяющей системой).
 * <p>- Первый элемент последовательности устанавливается равным этому числу.
 * <p>- Следующие элементы вычисляются по рекуррентной формуле <code>Rn+1=mid(Rn^2)</code>, где <code>mid</code> — это
 * функция, выделяющая второй, третий и четвертый разряд переданного числа. Например, <code>mid(123456)=345</code>
 * <p>Алгоритм, конечно, дурацкий и не выдерживающий никакой критики, но для практики работы со стримами сойдет :)
 * <p>Пример:
 * <blockquote><pre>{@code
 * pseudoRandomStream(13) должен вернуть стрим, состоящий из следующих чисел:
 *
 * 13, 16, 25, 62, 384, 745, 502, 200, 0, ... (дальше бесконечное количество нулей)
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l6_4_11_PseudoRandomStream {
    public static void main(String[] args) {
        pseudoRandomStream(13).limit(15).forEach(System.out::println);
    }

    public static IntStream pseudoRandomStream(int seed) {
        return IntStream.iterate(seed, n -> (n * n) / 10 % 1000);
    }
}