package stepik.algorimsMethods;

import java.util.Scanner;

/**
 * Решение задания 2.2.6 по курсу "Алгоритмы: теория и практика. Методы" на stepik.org
 * <p>Задание:
 * <p>Дано целое число <code>1≤n≤40</code>, необходимо вычислить <code>n</code>-е число Фибоначчи (напомним, что
 * <code>F0=0, F1=1</code> и <code>Fn=Fn−1+Fn−2</code> при <code>n≥2n≥2</code>).
 * <blockquote><pre>{@code
 * Sample Input:3
 * Sample Output:2
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l2_2_6_Fibonachi {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int n1 = 1;
        int n2 = 0;
        int sum = 0;
        if (n <= 1) {
            sum = n;
        }else {
            for (int i = 1; i < n; i++) {
                sum = n1 + n2;
                n2 = n1;
                n1 = sum;
            }
        }
        System.out.println(sum);
    }
}
