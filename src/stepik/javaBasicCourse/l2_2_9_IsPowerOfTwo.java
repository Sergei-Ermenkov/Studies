package stepik.javaBasicCourse;

/**
 * Решение задания 2.2.9 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте метод, проверяющий, является ли заданное число по абсолютной величине степенью двойки.
 * <p>Решать можно разными способами:
 * <p>- воспользовавшись одним удобным статическим методом из класса <code>javaBasicCourse.lang.Integer;</code>
 * применив пару трюков из двоичной арифметики;
 * <p>- написав решение "в лоб" с циклом и условными операторами (можете вернуться к этой задаче после просмотра
 * соответствующих уроков).
 * <p>- воспользуйтесь предоставленным шаблоном. Декларацию класса, метод <code>main</code> и обработку ввода-вывода
 * добавит проверяющая система.
 * <blockquote><pre>{@code
 * Sample Input 1:0
 * Sample Output 1:false
 *
 * Sample Input 2:1
 * Sample Output 2:true
 *
 * Sample Input 3:-2
 * Sample Output 3:true
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l2_2_9_IsPowerOfTwo {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(0));
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(-2));
    }

    /**
     * Checks if given <code>value</code> is a power of two.
     *
     * @param value any number
     * @return <code>true</code> when <code>value</code> is power of two, <code>false</code> otherwise
     */
    public static boolean isPowerOfTwo(int value) {
        int abs = Math.abs(value);
        return (abs != 0) && ((abs & (abs - 1)) == 0);
    }
}
