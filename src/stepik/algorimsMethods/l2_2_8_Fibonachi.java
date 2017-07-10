package stepik.algorimsMethods;

import java.util.Scanner;

/**
 * Решение задания 2.2.8 по курсу "Алгоритмы: теория и практика. Методы" на stepik.org
 * <p>Задание:
 * <p> Даны целые числа <code>1≤n≤10^18</code> и <code>2≤m≤10^5</code>, необходимо найти остаток от деления
 * <code>n</code>-го числа Фибоначчи на <code>m</code>.
 * <blockquote><pre>{@code
 * Sample Input: 10 2
 * Sample Output: 1
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l2_2_8_Fibonachi {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long n = s.nextLong();
        int m = s.nextInt();
        System.out.println(fibPowMod(n, m));
    }

    public static long fibPowMod(long n, int m) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        // матрица A для возведения в степень n
        // |a11 a12|
        // |a21 a22|
        long a11 = 1;
        long a12 = 1;
        long a21 = 1;
        long a22 = 0;

        // единичная матрица O для результата
        // |o11 o12|
        // |o21 o22|
        long o11 = 1;
        long o12 = 0;
        long o21 = 0;
        long o22 = 1;

        // вспомогательные числа для произведения матриц
        long r11;
        long r12;
        long r21;

        // Если степень нечетная то
        // O=(O*A) mod m
        // A=(A*A) mod m
        //
        // Если степень четная то
        // A=(A*A) mod m
        //
        // При n=7
        // O=(O*A) mod m
        // A=(A*A) mod m
        // n=3
        // O=(O*A) mod m
        // A=(A*A) mod m
        // n=1
        // O=(O*A) mod m
        // пустое действие A=(A*A) mod m
        //
        //
        // При n=5
        // O=(O*A) mod m
        // A=(A*A) mod m
        // n=2
        // A=(A*A) mod m
        // n=1
        // O=(O*A) mod m
        // пустое действие A=(A*A) mod m

        while (n > 0) {
            //если степень четная то O=(O*A) mod m
            if (n % 2 == 1) {
                r11 = o11;
                r21 = o21;
                o11 = (o11 * a11 + o12 * a21) % m;
                o12 = (r11 * a12 + o12 * a22) % m;
                o21 = (o21 * a11 + o22 * a21) % m;
                o22 = (r21 * a12 + o22 * a22) % m;
            }
            //если степень нечетная A=(A*A) mod m
            r11 = a11;
            r21 = a21;
            r12 = a12;
            a11 = (a11 * a11 + a12 * a21) % m;
            a12 = (r11 * a12 + a12 * a22) % m;
            a21 = (a21 * r11 + a22 * a21) % m;
            a22 = (r21 * r12 + a22 * a22) % m;
            n /= 2;
        }
        return o12;
    }
}