package stepik.algorimsMethods;

import java.util.Scanner;

/**
 * Решение задания 5.3.13 по курсу "Алгоритмы: теория и практика. Методы" на stepik.org
 * <p>Задание:
 * <p>Дано число <code>1 ≤n≤ 10^7</code>, необходимо найти последнюю цифру <code>n</code>-го числа Фибоначчи.
 * <p>Как мы помним, числа Фибоначи растут очень быстро, поэтому при их вычислении нужно быть аккуратным с переполнением.
 * В данной задаче, впрочем, этой проблемы можно избежать, поскольку нас интересует только последняя цифра числа Фибоначчи:
 * если <code>0≤a,b≤9</code> — последние цифры чисел <code>Fi</code> и <code>Fi+1</code> соответственно, то
 * <code>(a+b)mod10</code> — последняя цифра
 * числа <code>Fi+2</code>.
 * <blockquote><pre>{@code
 * Sample Input: 875577
 * Sample Output: 2
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l2_2_7_Fibonachi {
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
                sum = (n1 + n2) %2;
                n2 = n1;
                n1 = sum;
            }
        }
        System.out.println(sum);
    }
}