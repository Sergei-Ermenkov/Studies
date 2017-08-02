package stepik.algorimsMethods;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Решение задания 4.1.10 по курсу "Алгоритмы: теория и практика. Методы" на stepik.org
 * <p>Задание:
 * <p>Непрерывный рюкзак
 * <p>Первая строка содержит количество предметов <code>1≤n≤10^3</code> и вместимость рюкзака <code>0≤W≤2⋅10^6</code>.
 * Каждая из следующих <code>n</code> строк задаёт стоимость <code>0≤ci≤2⋅10^6</code> и объём <code>0<wi≤2⋅10^6</code>
 * предмета (<code>n, W, ci, wi</code> — целые числа). Выведите максимальную стоимость частей предметов (от каждого
 * предмета можно отделить любую часть, стоимость и объём при этом пропорционально уменьшатся), помещающихся в данный
 * рюкзак, с точностью не менее трёх знаков после запятой.
 * <blockquote><pre>{@code
 * Sample Input:
 * 3 50
 * 60 20
 * 100 50
 * 120 30
 * Sample Output:
 * 180.000
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l4_1_10_Knapsack {
    public static void main(String[] args) {
        new l4_1_10_Knapsack().run();
    }

    private void run() {
        int itemNum;
        int knapsackVolume;
        double[][] items;
        Scanner scanner = new Scanner(System.in);
        itemNum = scanner.nextInt();
        knapsackVolume = scanner.nextInt();
        items = new double[itemNum][3];
        for (double[] item : items) {
            item[0] = scanner.nextInt();
            item[1] = scanner.nextInt();
            item[2] = item[0]/item[1];
        }
        System.out.printf("%.10f",knapsack(knapsackVolume, items));
    }

    private void run_test() {
        int knapsackVolume = 50;
        double[][] items = {{60, 20,0}, {0, 50,0}, {0, 30,0}};
        for (double[] item: items) {
            item[2]=item[0]/item[1];
        }

        System.out.printf("%.3f",knapsack(knapsackVolume, items));
    }

    private double knapsack(int knapsackVolume, double[][] items) {
        Arrays.sort(items, (item1, item2) -> Double.compare(item2[2], item1[2]));
        int knapsack = knapsackVolume;
        double price = 0;
        for (double[] item : items) {
                if (item[1] < knapsack) {
                    knapsack -= item[1];
                    price += item[0];
                } else {
                    price += knapsack * item[0] / item[1];
                    break;
                }
        }
        return price;
    }
}