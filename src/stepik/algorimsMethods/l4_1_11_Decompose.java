package stepik.algorimsMethods;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Решение задания 4.1.11 по курсу "Алгоритмы: теория и практика. Методы" на stepik.org
 * <p>Задание:
 * <p>Различные слагаемые
 * <p>По данному числу <code>1≤n≤10^9</code> найдите максимальное число <code>k</code>, для которого nn можно
 * представить как сумму <code>k</code> различных натуральных слагаемых. Выведите в первой строке число <code>k</code>,
 * во второй — <code>k</code> слагаемых.
 * <blockquote><pre>{@code
 * Sample Input 1:
 * 4
 * Sample Output 1:
 * 2
 * 1 3
 * Sample Input 2:
 * 6
 * Sample Output 2:
 * 3
 * 1 2 3
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l4_1_11_Decompose {
    public static void main(String[] args) {
        new l4_1_11_Decompose().run();
    }

    private void run(){
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        ArrayDeque<Integer> row = decompose(num);
        System.out.println(row.size());
        row.forEach(x-> System.out.printf("%d ",x));
    }

    private ArrayDeque<Integer> decompose(int num){
        ArrayDeque<Integer> row = new ArrayDeque<>();
        row.add(1);
        int sum = 0;
        while (true){
            sum += row.getLast();
            if (sum > num) {
                sum -= row.pollLast();
                row.add((num-sum)+row.pollLast());
                return row;
            }
            row.add(row.getLast()+1);
        }
    }
}
