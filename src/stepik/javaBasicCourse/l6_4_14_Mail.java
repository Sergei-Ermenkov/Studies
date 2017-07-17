package stepik.javaBasicCourse;

import java.util.*;
import java.util.function.*;

/**
 * Решение задания 6.4.14 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>В этой задаче вам предстоит самостоятельно написать набор классов таким образом, чтобы данный код успешно
 * компилировался и выполнялся.
 * <p>Вам предстоит использовать новые знания про <code>generics</code>, коллекции и <code>Stream API</code>.
 * <p>В приведенном коде используется оператор <code>assert</code>. Этот оператор используется для того, чтобы
 * проверять определенные инварианты в коде. С помощью него возможно писать небольшие тесты и следить за корректностью
 * своей программы (в обычной ситуации предпочтительно для этих целей использовать библиотеки для модульного
 * тестирования, которые выходят за рамки базового курса).
 * <p>Оператор выглядит следующим образом:
 * <blockquote><pre>{@code
 * assert предикат: сообщение;
 * }</pre></blockquote>
 * <p>Предикат – выражение с типом <code>boolean</code>. Если выражение является ложным, то в программе возникает
 * исключение <code>AssertionError</code> с соответствующим сообщением.
 * <p>По-умолчанию данная функциональность отключена. Чтобы ее включить, необходимо передать специальный ключ
 * <code>-ea</code> в параметры виртуальной машины. Сделать это можно прямо при запуске в консоли с помощью программы
 * <code>java</code>, либо указав этот ключ в настройках запуска программы в вашей <code>IDE</code>. В случае
 * <code>IntellijIDEA</code>, например, эта опция указывается в поле
 * <code>Run -> Edit Configurations... -> конфигурация запуска вашей программы -> VM Options</code>.
 * <p>Код, который необходимо заставить успешно работать:
 * <blockquote><pre>{@code
 * // Random variables
 * String randomFrom = "..."; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
 * String randomTo = "...";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
 * int randomSalary = 100;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.
 *
 * // Создание списка из трех почтовых сообщений.
 * MailMessage firstMessage = new MailMessage(
 *       "Robert Howard",
 *       "H.P. Lovecraft",
 *       "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
 * );
 *
 * assert firstMessage.getFrom().equals("Robert Howard"): "Wrong firstMessage from address";
 * assert firstMessage.getTo().equals("H.P. Lovecraft"): "Wrong firstMessage to address";
 * assert firstMessage.getContent().endsWith("Howard!"): "Wrong firstMessage content ending";
 *
 * MailMessage secondMessage = new MailMessage(
 *       "Jonathan Nolan",
 *       "Christopher Nolan",
 *       "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
 * );
 *
 * MailMessage thirdMessage = new MailMessage(
 *       "Stephen Hawking",
 *       "Christopher Nolan",
 *       "Я так и не понял Интерстеллар."
 * );
 *
 * List<MailMessage> messages = Arrays.asList(
 *       firstMessage, secondMessage, thirdMessage
 * );
 *
 * // Создание почтового сервиса.
 * MailService<String> mailService = new MailService<>();
 *
 * // Обработка списка писем почтовым сервисом
 * messages.stream().forEachOrdered(mailService);
 *
 * // Получение и проверка словаря "почтового ящика",
 * // где по получателю можно получить список сообщений, которые были ему отправлены
 * Map<String, List<String>> mailBox = mailService.getMailBox();
 *
 * assert mailBox.get("H.P. Lovecraft").equals(
 *       Arrays.asList(
 *             "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
 *       )
 * ): "wrong mailService mailbox content (1)";
 *
 * assert mailBox.get("Christopher Nolan").equals(
 *       Arrays.asList(
 *             "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
 *             "Я так и не понял Интерстеллар."
 *       )
 * ): "wrong mailService mailbox content (2)";
 *
 * assert mailBox.get(randomTo).equals(Collections.<String>emptyList()): "wrong mailService mailbox content (3)";
 *
 *
 * // Создание списка из трех зарплат.
 * Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
 * Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
 * Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);
 *
 * // Создание почтового сервиса, обрабатывающего зарплаты.
 * MailService<Integer> salaryService = new MailService<>();
 *
 * // Обработка списка зарплат почтовым сервисом
 * Arrays.asList(salary1, salary2, salary3).forEach(salaryService);
 *
 * // Получение и проверка словаря "почтового ящика",
 * // где по получателю можно получить список зарплат, которые были ему отправлены.
 * Map<String, List<Integer>> salaries = salaryService.getMailBox();
 * assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)): "wrong salaries mailbox content (1)";
 * assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)): "wrong salaries mailbox content (2)";
 * assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)): "wrong salaries mailbox content (3)";
 * }</pre></blockquote>
 * <p> В конечном итоге, вам нужно реализовать классы <code>MailService</code>, <code>MailMessage</code> и
 * <code>Salary</code> (и, вероятно, вспомогательные классы и интерфейсы) и отправить их код в форму. Все классы должны
 * быть публичными и статическими (ваши классы подставятся во внешний класс для тестирования).
 * <p> В идеологически правильном решении не должно фигурировать ни одного оператора <code>instanceof</code>.
 * <p> В классе для тестирования объявлены следующие импорты:
 * <blockquote><pre>{@code
 * import java.util.*;
 * import java.util.function.*;
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l6_4_14_Mail {
    public static void main(String[] args) {
        // Random variables
        String randomFrom = "RandomFrom"; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        String randomTo = "RandomTo";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        int randomSalary = 100;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.

        // Создание списка из трех почтовых сообщений.
        MailMessage_ firstMessage = new MailMessage_(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        assert firstMessage.getFrom().equals("Robert Howard") : "Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft") : "Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!") : "Wrong firstMessage content ending";

        MailMessage_ secondMessage = new MailMessage_(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage_ thirdMessage = new MailMessage_(
                "Stephen Hawking",
                "Christopher Nolan",
                "Я так и не понял Интерстеллар."
        );

        List<MailMessage_> messages = Arrays.asList(
                firstMessage, secondMessage, thirdMessage
        );

        // Создание почтового сервиса.
        MailService_<String> mailService = new MailService_<>();

        // Обработка списка писем почтовым сервисом
        messages.stream().forEachOrdered(mailService);

        // Получение и проверка словаря "почтового ящика",
        //   где по получателю можно получить список сообщений, которые были ему отправлены
        Map<String, List<String>> mailBox = mailService.getMailBox();

        assert mailBox.get("H.P. Lovecraft").equals(
                Arrays.asList(
                        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
                )
        ) : "wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").equals(
                Arrays.asList(
                        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                        "Я так и не понял Интерстеллар."
                )
        ) : "wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).equals(Collections.<String>emptyList()) : "wrong mailService mailbox content (3)";


        // Создание списка из трех зарплат.
        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

        // Создание почтового сервиса, обрабатывающего зарплаты.
        MailService_<Integer> salaryService = new MailService_<>();

        // Обработка списка зарплат почтовым сервисом
        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

        // Получение и проверка словаря "почтового ящика",
        //   где по получателю можно получить список зарплат, которые были ему отправлены.
        Map<String, List<Integer>> salaries = salaryService.getMailBox();
        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)) : "wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)) : "wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)) : "wrong salaries mailbox content (3)";
    }
}

// Общий интерфейс для классов MailMessage_ и Salary
interface Message<T> {
    String getTo();
    String getFrom();
    T getContent();
}

// Класс обобщение для  классов MailMessage_ и Salary. Вынесены общие методы.
class SimpleMessage<T> implements Message<T> {
    private String from, to;
    private T content;

    public SimpleMessage(String to, String from, T content) {
        this.to = to;
        this.from = from;
        this.content = content;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public T getContent() {
        return content;
    }
}

class MailMessage_ extends SimpleMessage<String> {
    public MailMessage_(String from, String to, String content) {
        super(to, from, content);
    }
}

class Salary extends SimpleMessage<Integer> {
    public Salary(String from, String to, Integer content) {
        super(to, from, content);
    }
}

class MailService_<U> implements Consumer<Message<U>> {
    private Map<String, List<U>> mailPackage;

    // implement here
    public MailService_() {
        mailPackage = new HashMap<String, List<U>>() {

            // Переопределение метода get() для HashMap.
            @Override
            public List<U> get(Object key) {
                if (this.containsKey(key)) {
                    return super.get(key);
                } else {
                    return Collections.emptyList();
                }
            }
        };
    }

    public Map<String, List<U>> getMailBox() {
        return mailPackage;
    }

    @Override
    public void accept(Message<U> message) {

        //Если при добавлении сообщения ArrayList для данного отправилеля отсутствует то создает новый.
        mailPackage.computeIfAbsent(message.getTo(), (name) -> new ArrayList<>()).add(message.getContent());
    }
}