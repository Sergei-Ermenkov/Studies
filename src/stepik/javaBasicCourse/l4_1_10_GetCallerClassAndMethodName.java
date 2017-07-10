package stepik.javaBasicCourse;

/**
 * Решение задания 4.1.10 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте метод, позволяющий другим методам узнать, откуда их вызвали.
 * <p>Метод <code>getCallerClassAndMethodName()</code> должен возвращать имя класса и метода, откуда вызван метод,
 * вызвавший данный утилитный метод. Или <code>null</code> (нулевую ссылку, а не строку "null"), если метод, вызвавший
 * <code>getCallerClassAndMethodName()</code> является точкой входа в программу, т.е. его никто не вызывал.
 * <p>Это актуально, например, в библиотеках логирования, где для каждого сообщения в логе надо выводить класс и метод,
 * откуда сообщение было залогировано.
 * <p>Пример:
 * <blockquote><pre>{@code
 * package org.stepic.example;
 *
 * public class Apply {
 *     public static void main(String[] args) {
 *         System.out.println(getCallerClassAndMethodName());
 *         anotherMethod();
 *     }
 *
 *     private static void anotherMethod() {
 *         System.out.println(getCallerClassAndMethodName());
 *     }
 *
 *     public static String getCallerClassAndMethodName() {
 *         // ...
 *     }
 * }
 * }</pre></blockquote>
 * <p>При запуске эта программа должна вывести:
 * <blockquote><pre>{@code
 * null
 * org.stepic.example.Apply#main
 * }</pre></blockquote>
 * <p>P.S. При тестировании этой программы в среде разработки вы можете получить другой результат: вместо null в первой
 * строчке будет напечатан какой-то посторонний класс и метод. Это связано с тем, что среда разработки обычно запускает
 * не ваш класс, а свой собственный, который затем уже вызывает ваш. Чтобы этого избежать, запускайте программу командой
 * "java" в командной строке.
 * <p>P.P.S. Эта задача в уроке про исключения не случайно :)
 *
 * @author Sergei Ermenkov
 */
public class l4_1_10_GetCallerClassAndMethodName {
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

    public static String getCallerClassAndMethodName() {
        StackTraceElement[] stackTraceElements = new Exception().getStackTrace();
        if (stackTraceElements.length < 3) return null;
        return stackTraceElements[2].getClassName() + "#" + stackTraceElements[2].getMethodName();
    }
}
