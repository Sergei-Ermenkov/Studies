package myUtils;

/**
 * Класс для ориентировочного замера времени выполнения кода
 * <p>Пример использования класса:
 * <blockquote><pre>{@code
 * long time = Timer.measureNanoTime(() -> new BigDecimal("1234567").pow(100000));
 * }</pre></blockquote>
 */
public class Timer {

    /**
     * Замер времени выполнения алгоритма в милисекундах.
     *
     * @param runnable выражение для замера
     * @return время затраченное на выполнение операции
     */
    public static long measureMillisTime(Runnable runnable) {
        long startTime = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Замер времени выполнения алгоритма в наносекундах.
     *
     * @param runnable выражение для замера
     * @return время затраченное на выполнение операции
     */
    public static long measureNanoTime(Runnable runnable) {
        long startTime = System.nanoTime();
        runnable.run();
        return System.nanoTime() - startTime;
    }
}