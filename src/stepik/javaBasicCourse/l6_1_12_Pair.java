package stepik.javaBasicCourse;

/**
 * Решение задания 6.1.12 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте generic-класс <code>Pair</code>, похожий на <code>Optional</code>, но содержащий пару элементов разных
 * типов и не запрещающий элементам принимать значение <code>null</code>.
 * <p>Реализуйте методы <code>getFirst()</code>, <code>getSecond()</code>, <code>equals()</code> и
 * <code>hashCode()</code>, а также статический фабричный метод <code>of()</code>. Конструктор должен быть закрытым
 * (<code>private</code>).
 * <p>С корректно реализованным классом <code>Pair</code> должен компилироваться и успешно работать следующий код:
 * <blockquote><pre>{@code
 * Pair<Integer, String> pair = Pair.of(1, "hello");
 * Integer i = pair.getFirst(); // 1
 * String s = pair.getSecond(); // "hello"
 *
 * Pair<Integer, String> pair2 = Pair.of(1, "hello");
 * boolean mustBeTrue = pair.equals(pair2); // true!
 * boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!
 * }</pre></blockquote>
 * <p>Пожалуйста, не меняйте модификатор доступа класса <code>Pair</code>. Для корректной проверки класс должен иметь
 * пакетную видимость.
 *
 * @author Sergei Ermenkov
 */
public class l6_1_12_Pair {
    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst(); // 1
        String s = pair.getSecond(); // "hello"

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!

        Pair<String, String> pair3 = Pair.of(null, "h");
        Pair<String, String> pair4 = Pair.of(null, "h");
        boolean mustBeTrue1 = pair3.equals(pair4);
        int a = pair3.hashCode();
        int b = pair4.hashCode();

        System.out.println("test");
    }
}

class Pair<X, Y> {
    private X object1;
    private Y object2;

    private Pair(X one, Y two) {
        object1 = one;
        object2 = two;
    }

    public static <X, Y> Pair of(X one, Y two) {
        return new Pair(one, two);
    }

    public X getFirst() {
        return object1;
    }

    public Y getSecond() {
        return object2;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (!(object instanceof Pair)) return false;
        Pair other = (Pair) object;
        return java.util.Objects.equals(object1, other.object1) && java.util.Objects.equals(object2, other.object2);

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (object1 == null ? 0 : object1.hashCode());
        result = 31 * result + (object2 == null ? 0 : object2.hashCode());
        return result;
    }


}