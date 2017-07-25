package stepik.algorimsMethods;

import java.util.Scanner;

/**
 * * Решение задания 2.2.8 по курсу "Алгоритмы: теория и практика. Методы" на stepik.org
 * <p>Задание:
 * <p>По данным двум числам <code>1≤a,b≤2⋅10^9<code> найдите их наибольший общий делитель.
 * <blockquote><pre>{@code
 * Sample Input 1: 18 35
 * Sample Output 1: 1
 * Sample Input 2: 14159572 63967072
 * Sample Output 2: 4
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l2_3_5_EuclidGCD {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        System.out.println(euclidGCD(n, m));
    }

    public static int euclidGCD(int val1, int val2) {
        if (val1 == 0) return val2;
        if (val2 == 0) return val1;
        if (val1 >= val2) return euclidGCD(val1 % val2, val2);
        return euclidGCD(val1, val2 % val1);
    }
}