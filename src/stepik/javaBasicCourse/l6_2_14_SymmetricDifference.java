package stepik.javaBasicCourse;

import java.util.*;

/**
 * Решение задания 6.2.14 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте метод, вычисляющий симметрическую разность двух множеств.
 * <p>Метод должен возвращать результат в виде нового множества. Изменять переданные в него множества не допускается.
 * <p>Пример:
 * <p>Симметрическая разность множеств <code>{1, 2, 3}</code> и <code>{0, 1, 2}</code> равна <code>{0, 3}</code>.
 *
 * @author Sergei Ermenkov
 */
public class l6_2_14_SymmetricDifference {
    public static void main(String[] args) {
        Integer[] int1 = {new Integer(1), new Integer(2), new Integer(3)};
        Integer[] int2 = {new Integer(0), new Integer(1), new Integer(2)};
        Set<Integer> set1 = new HashSet<>(Arrays.asList(int1));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(int2));
        Set<Integer> result = symmetricDifference(set1, set2);
        result.forEach((t) -> System.out.println(t.toString()));
    }

    // Реализованный метод
    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> result = new HashSet<>();
        for (T element : set1) {
            if (set2.contains(element)) continue;
            result.add(element);
        }
        for (T element : set2) {
            if (set1.contains(element)) continue;
            result.add(element);
        }
        return result;
    }
}
