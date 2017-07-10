package stepik.javaBasicCourse;

/**
 * Решение задания 2.2.3 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте метод, который возвращает букву, стоящую в таблице <code>UNICODE</code> после символа "\" (обратный
 * слэш) на расстоянии <code>a</code>.
 * <blockquote><pre>{@code
 * Sample Input 1:32
 * Sample Output 1:|
 *
 * Sample Input 2:29
 * Sample Output 2:y
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l2_2_3_CharExpression {
    public static void main(String[] args) {
        System.out.println(charExpression(32));
        System.out.println(charExpression(29));
    }

    public static char charExpression(int a) {
        return (char) ('\\' + a);
    }
}
