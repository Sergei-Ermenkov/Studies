package stepik.javaBasicCourse;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Решение задания 6.3.8 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Давайте научимся комбинировать функции в более сложные функции. Для примера построим следующую комбинацию. Дан
 * предикат <code>condition</code> и две функции <code>ifTrue</code> и <code>ifFalse</code>. Напишите метод
 * <code>ternaryOperator</code>, который из них построит новую функцию, возвращающую значение функции
 * <code>ifTrue</code>, если предикат выполнен, и значение <code>ifFalse</code> иначе.
 * <p>Пример использования метода (можно было все свернуть в одну строчку, но для наглядности все элементы вынесены в
 * отдельные переменные):
 * <blockquote><pre>{@code
 * Predicate<Object> condition = Objects::isNull;
 * Function<Object, Integer> ifTrue = obj -> 0;
 * Function<CharSequence, Integer> ifFalse = CharSequence::length;
 * Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);
 * }</pre></blockquote>
 * <p>Результирующая функция будет для нулевых ссылок на <code>String</code> возвращать 0, а для ненулевых ссылок
 * возвращать длину строки.
 * <p>В качестве дополнительного задания самостоятельно разберите, почему у метода <code>ternaryOperator</code> такая
 * сложная сигнатура.
 *
 * @author Sergei Ermenkov
 */
public class l6_3_8_TernaryOperator {
    public static void main(String[] args) {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);

        System.out.printf("safeStringLength = %d", safeStringLength.apply("Test string"));
    }

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {
        return (str) -> condition.test(str) ? ifTrue.apply(str) : ifFalse.apply(str);
    }
}