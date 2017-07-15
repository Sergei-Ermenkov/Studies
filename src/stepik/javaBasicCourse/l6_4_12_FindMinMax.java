package stepik.javaBasicCourse;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Решение задания 6.4.12 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Напишите метод, находящий в стриме минимальный и максимальный элементы в соответствии c порядком, заданным
 * <code>Comparator</code>'ом.
 * <p>Найденные минимальный и максимальный элементы передайте в <code>minMaxConsumer</code> следующим образом:
 * <blockquote><pre>{@code
 * minMaxConsumer.accept(min, max);
 * }</pre></blockquote>
 * <p>Если стрим не содержит элементов, то вызовите
 * <blockquote><pre>{@code
 * minMaxConsumer.accept(null, null);
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l6_4_12_FindMinMax {
    public static void main(String[] args) {
        Stream<BigInteger> strNull = Stream.of();
        Stream<BigInteger> str = Stream.of(BigInteger.valueOf(6), BigInteger.TEN, BigInteger.ONE, BigInteger.valueOf(9));
        //Можно записать "Comparator<BigInteger> compar = (first, next) -> first.compareTo(next);"
        Comparator<BigInteger> compar = BigInteger::compareTo;
        BiConsumer<BigInteger, BigInteger> ret = (first, next) -> {
            if (first != null && next != null) {
                System.out.printf("min: %s, max:%s%n", first.toString(), next.toString());
            } else {
                System.out.println("min: null, max: null");
            }
        };

        findMinMax(str, compar, ret);
        findMinMax(strNull, compar, ret);
    }

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> sortList = stream.sorted(order).collect(Collectors.toList());
        if (sortList.size() == 0) {
            minMaxConsumer.accept(null, null);
        } else {
            minMaxConsumer.accept(sortList.get(0), sortList.get(sortList.size() - 1));
        }
    }
}