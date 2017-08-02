package stepik.algorimsMethods;

import java.util.*;

/**
 * Решение задания 4.1.9 по курсу "Алгоритмы: теория и практика. Методы" на stepik.org
 * <p>Задание:
 * <p>Покрыть отрезки точками
 * <p>По данным <code>n</code> отрезкам необходимо найти множество точек минимального размера, для которого каждый из
 * отрезков содержит хотя бы одну из точек.
 * <p>В первой строке дано число <code>1≤n≤100</code> отрезков. Каждая из последующих <code>n</code> строк содержит по
 * два числа <code>0≤l≤r≤10^9</code>, задающих начало и конец отрезка. Выведите оптимальное число <code>m</code> точек
 * и сами <code>m</code> точек. Если таких множеств точек несколько, выведите любое из них.
 * <blockquote><pre>{@code
 * Sample Input 1:
 * 3
 * 1 3
 * 2 5
 * 3 6
 * Sample Output 1:
 * 1
 * 3
 * <p>
 * Sample Input 2:
 * 4
 * 4 7
 * 1 3
 * 2 5
 * 5 6
 * Sample Output 2:
 * 2
 * 3 6
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l4_1_9_PointCover {

    public static void main(String[] args) {
        new l4_1_9_PointCover().run();
//     new l4_1_9_PointCover().run_test(new int[][]{{1, 6}, {3, 4}, {4, 7}, {7, 11}, {5, 7}, {9, 7}, {1, 13}});
    }

    private ArrayDeque<Integer> pointCover(int[][] intercept) {
        ArrayDeque<Integer> point = new ArrayDeque<>();
        Arrays.sort(intercept, Comparator.comparingInt(x -> x[1]));
        point.add(intercept[0][1]);
        for (int i = 1; i < intercept.length; i++) {
            if (intercept[i][0] > point.getLast()) point.add(intercept[i][1]);
        }
        return point;
    }

    private void run() {
        int numIntercept;
        int[][] intercept;
        Scanner scanner = new Scanner(System.in);
        numIntercept = scanner.nextInt();
        intercept = new int[numIntercept][2];
        for (int i = 0; i < numIntercept; i++) {
            intercept[i][0] = scanner.nextInt();
            intercept[i][1] = scanner.nextInt();
        }
        ArrayDeque<Integer> point = pointCover(intercept);

        System.out.println(point.size());
        point.forEach(x -> System.out.printf("%d ", x));
    }

    private void run_test(int[][] interceptsArray) {
        long startTime = System.currentTimeMillis();
        ArrayDeque<Integer> point = pointCover(interceptsArray);
        System.out.printf("%n%d ms", System.currentTimeMillis() - startTime);

        System.out.println(point.size());
        point.forEach(x -> System.out.printf("%d ", x));
    }
}