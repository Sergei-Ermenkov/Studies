package stepik.javaBasicCourse;

import java.util.Locale;
import java.util.Scanner;

/**
 * Решение задания 5.3.13 по курсу "Java.Базовый курс" на stepik.org
 * <h3>Примечание: Для окончания ввода данных в консоль необходимо нажать Enter и cmd+d (для mac OS)</h3>
 * <p>Задание:
 * <p>Напишите программу, читающую текст из <code>System.in</code> и выводящую в <code>System.out</code> сумму всех встреченных
 * в тексте вещественных чисел с точностью до шестого знака после запятой. Числом считается последовательность символов,
 * отделенная от окружающего текста пробелами или переводами строк и успешно разбираемая методом <code>Double.parseDouble.
 * </code>
 * <p>На этот раз вам надо написать программу полностью, т.е. объявить класс (с именем <code>Main</code> — таково
 * ограничение проверяющей системы), метод <code>main</code>, прописать все <code>import</code>'ы.
 * <blockquote><pre>{@code
 * Sample Input 1: 1 2 3
 * Sample Output 1: 6.000000
 *
 * Sample Input 2: a1 b2 c3
 * Sample Output 2: 0.000000
 *
 * Sample Input 3:-1e3
 *              18 .111 11bbb
 * Sample Output 3: -981.889000
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l5_3_13_Addition {
    public static void main(String[] args) {

        //поток для проверки
        //String source = " -1e3 ss \n18 11bbb .111";
        //InputStream stream = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));

        double summ = 0;
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        while (sc.hasNext()) {
            if (sc.hasNextDouble()) {
                summ += sc.nextDouble();
                continue;
            }
            sc.next();
        }
        System.out.printf("%.6f", summ);
    }
}