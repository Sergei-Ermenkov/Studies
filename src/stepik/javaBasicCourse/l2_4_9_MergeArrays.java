package stepik.javaBasicCourse;

import java.util.Arrays;

/**
 * Решение задания 2.4.9 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте метод, сливающий два отсортированных по неубыванию массива чисел в один отсортированный в том же
 * порядке массив. Массивы могут быть любой длины, в том числе нулевой.
 * <p>Предполагается, что вы реализуете алгоритм слияния, имеющий линейную сложность: он будет идти по двум исходным
 * массивам и сразу формировать отсортированный результирующий массив. Так, чтобы сортировка полученного массива при
 * помощи <code>Arrays.sort()</code> уже не требовалась. К сожалению, автоматически это не проверить, так что это
 * остается на вашей совести :)
 * <p>Воспользуйтесь предоставленным шаблоном. Декларацию класса, метод <code>main</code> и обработку ввода-вывода
 * добавит проверяющая система.
 * <p>Пример:
 * <p>Если на вход подаются массивы <code>{0, 2, 2}</code> и <code>{1, 3}</code>, то на выходе должен получиться массив
 * <code>{0, 1, 2, 2, 3}</code>
 *
 * @author Sergei Ermenkov
 */
public class l2_4_9_MergeArrays {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeArrays(new int[]{0, 2, 2}, new int[]{1,3})));
    }

    /**
     * Merges two given sorted arrays into one
     *
     * @param a1 first sorted array
     * @param a2 second sorted array
     * @return new array containing all elements from <code>a1</code> and <code>a2</code>, sorted
     */
    public static int[] mergeArrays(int[] a1, int[] a2) {
        if (a1.length == 0) {
            return a2;
        }
        if (a2.length == 0) {
            return a1;
        }
        int[] concSort = new int[a1.length + a2.length];
        int firCount = 0, secCount = 0, sumCount = 0;
        while (sumCount < concSort.length) {
            if (firCount > a1.length - 1) {
                concSort[sumCount] = a2[secCount];
                secCount++;
                sumCount++;
                continue;
            } else if (secCount > a2.length - 1) {
                concSort[sumCount] = a1[firCount];
                firCount++;
                sumCount++;
                continue;
            }
            if (a1[firCount] < a2[secCount]) {
                concSort[sumCount] = a1[firCount];
                firCount++;
                sumCount++;
            } else {
                concSort[sumCount] = a2[secCount];
                secCount++;
                sumCount++;
            }
        }
        return concSort;
    }
}