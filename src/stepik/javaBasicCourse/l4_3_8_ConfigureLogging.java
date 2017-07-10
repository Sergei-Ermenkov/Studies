package stepik.javaBasicCourse;

import java.util.logging.*;

/**
 * Решение задания 4.3.8 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>В этой задаче вам нужно реализовать метод, настраивающий параметры логирования. Конфигурирование в коде позволяет
 * выполнить более тонкую и хитрую настройку, чем при помощи <code>properties</code>-файла.
 * <p>Требуется выставить такие настройки, чтобы:
 * <p>- Логгер с именем "<code>org.stepic.java.logging.ClassA</code>" принимал сообщения всех уровней.
 * <p>- Логгер с именем "<code>org.stepic.java.logging.ClassB</code>" принимал только сообщения уровня
 * WARNING и серьезнее.
 * <p>- Все сообщения, пришедшие от нижестоящих логгеров на уровень "<code>org.stepic.java</code>",
 * независимо от серьезности сообщения печатались в консоль в формате XML (*) и не передавались вышестоящим обработчикам
 * на уровнях "<code>org.stepic</code>", "<code>org</code>" и "".
 * <p>Не упомянутые здесь настройки изменяться не должны.
 * <p>(*) В реальных программах мы бы конечно печатали XML не в консоль, а в файл. Но проверяющая система не разрешает
 * создавать файлы на диске, поэтому придется так.
 * <p>Подсказки:
 * <p><code>Level</code> есть не только у <code>Logger</code>, но и у <code>Handler</code>.
 * <p>Передача сообщения на обработку родительскому <code>Handler</code>'у регулируется свойством
 * <code>useParentHandlers</code>.
 *
 * @author Sergei Ermenkov
 */
public class l4_3_8_ConfigureLogging {
    public static void main(String[] args) {
        configureLogging();
        Logger.getLogger("org.stepic.javaBasicCourse").fine("Test");
    }

    private static void configureLogging() {
        Handler consoleHandler = new ConsoleHandler();
        Logger.getLogger("org.stepic.javaBasicCourse.logging.ClassA").setLevel(Level.ALL);
        Logger.getLogger("org.stepic.javaBasicCourse.logging.ClassB").setLevel(Level.WARNING);
        Logger.getLogger("org.stepic.javaBasicCourse").setLevel(Level.ALL);
        Logger.getLogger("org.stepic.javaBasicCourse").addHandler(consoleHandler);
        Logger.getLogger("org.stepic.javaBasicCourse").setUseParentHandlers(false);
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new XMLFormatter());
    }
}
