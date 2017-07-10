package stepik.javaBasicCourse;

/**
 * Решение задания 2.1.15 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>В Григорианском календаре год является високосным в двух случаях: либо он кратен 4, но при этом не кратен 100,
 * либо кратен 400.
 * <p>Реализуйте метод, вычисляющий количество високосных лет с начала нашей эры (первого года) до заданного года
 * включительно. На самом деле Григорианский календарь был введен значительно позже, но здесь для упрощения мы
 * распространяем его действие на всю нашу эру.
 * <p>Ввод-вывод обеспечивает проверяющая система. Вам надо только проанализировать переданное в метод значение и
 * вернуть результат. В программу всегда подается положительный номер года. Предполагается решение без циклов. Вам надо
 * придумать и записать несложную формулу, использующую только арифметические операторы.
 * <blockquote><pre>{@code
 * Sample Input 1:1
 * Sample Output 1:0
 *
 * Sample Input 2:4
 * Sample Output 2:1
 *
 * Sample Input 3:100
 * Sample Output 3:24
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l2_1_15_LeapYearCount {
    public static void main(String[] args) {
        System.out.println(leapYearCount(1));
        System.out.println(leapYearCount(4));
        System.out.println(leapYearCount(100));
    }

    public static int leapYearCount(int year) {
        return year / 4 - (year / 100) + (year / 400);
    }
}