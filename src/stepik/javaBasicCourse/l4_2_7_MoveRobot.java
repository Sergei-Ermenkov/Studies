package stepik.javaBasicCourse;

//Подключение к роботу(соединение)
interface RobotConnection extends AutoCloseable {
    void moveRobotTo(int x, int y);

    @Override
    void close();
}

//Установка соединения
interface RobotConnectionManager {
    RobotConnection getConnection();
}

/**
 * Решение задания 4.2.7 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Вспомним нашего старого знакомого робота из задачи 3.3.12. Теперь мы будем давать роботу команды удаленно,
 * подключаясь к нему по беспроводному каналу связи.
 * <p>Подключение к роботу представляется в программе интерфейсом <code>RobotConnection</code>:
 * <blockquote><pre>{@code
 * public interface RobotConnection extends AutoCloseable {
 *     void moveRobotTo(int x, int y);
 *     @Override
 *     void close();
 * }
 * }</pre></blockquote>
 * <p>Да, робот с тех пор поумнел и стал понимать команду на перемещение в заданную точку (метод
 * <code>moveRobotTo</code>). Ему больше не нужны пошаговые инструкции.
 * <p><code>RobotConnection</code> — это временно устанавливаемое соединение, которое надо закрывать, когда оно больше
 * не нужно. Для закрытия соединения в интерфейсе есть метод <code>close()</code>.
 * <p>За установку соединения отвечает <code>RobotConnectionManager</code>:
 * <blockquote><pre>{@code
 * public interface RobotConnectionManager {
 *     RobotConnection getConnection();
 * }
 * }</pre></blockquote>
 * <p>Метод <code>getConnection()</code> делает попытку соединиться с роботом и возвращает установленное соединение,
 * через которое можно отдавать роботу команды.
 * <p>Установка соединения может завершиться неуспешно, а также уже установленное соединение может внезапно разорваться.
 * Всякое бывает. Поэтому любой метод <code>RobotConnectionManager</code> и <code>RobotConnection</code> может бросить
 * непроверяемое исключение <code>RobotConnectionException</code>:
 * <blockquote><pre>{@code
 * public class RobotConnectionException extends RuntimeException {
 * <p>
 *     public RobotConnectionException(String message) {
 *         super(message);
 *     }
 * <p>
 *     public RobotConnectionException(String message, Throwable cause) {
 *         super(message, cause);
 *     }
 * }
 * }</pre></blockquote>
 * <p>Ваша задача — реализовать метод который устанавливает соединение с роботом, отдает ему команду на перемещение в
 * заданную точку и затем закрывает соединение, выполняя при необходимости повтор этой последовательности до трех раз.
 * <p>При этом:
 * <p>-1. Попытка отдать команду роботу считается успешной, если удалось установить соединение и выполнить метод
 * <code>RobotConnection.moveRobotTo()</code> без исключений. Ошибка закрытия соединения не важна и должна
 * игнорироваться.
 * <p>-2. Если первая попытка подключиться и отдать команду оказалась неуспешной, то закрываем соединение и выполняем
 * вторую попытку. Если вторая тоже не удалась, то выполняется третья. После третьей неудачи метод должен бросить
 * исключение <code>RobotConnectionException</code>.
 * <p>-3. Метод отвечает за открытие и закрытие соединения. Если соединение удалось установить, то оно обязательно
 * должно быть закрыто несмотря на возможные исключения, случившиеся в дальнейшем во время работы метода.
 * <p>4. Если во время работы метода случилось исключение любого типа, отличного от
 * <code>RobotConnectionException</code>, метод должен завершиться и выбросить это исключение, не выполняя повторных
 * попыток пообщаться с роботом. Единственное, что метод должен сделать перед выбросом этого исключения — закрыть
 * открытое соединение <code>RobotConnection</code>.
 *
 * @author Sergei Ermenkov
 */
public class l4_2_7_MoveRobot {
    public static void main(String[] args) {
        moveRobot(() -> new RobotConnection() {
            @Override
            public void moveRobotTo(int x, int y) {
            }

            @Override
            public void close() {
            }
        }, 5, 6);
    }

    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        int trys = 3;
        while (trys > 0) {
            try (RobotConnection robotConnect = robotConnectionManager.getConnection()) {
                robotConnect.moveRobotTo(toX, toY);
                trys = 0;
            } catch (RobotConnectionException r) {
                if (--trys == 0) {
                    throw r;
                }
            }
        }
    }
}

//Исключения
class RobotConnectionException extends RuntimeException {
    public RobotConnectionException(String message) {
        super(message);
    }

    public RobotConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}


