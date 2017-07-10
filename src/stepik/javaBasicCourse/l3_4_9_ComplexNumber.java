package stepik.javaBasicCourse;

/**
 * Решение задания 3.4.9 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Дан класс <code>ComplexNumber</code>. Переопределите в нем методы <code>equals()</code> и <code>hashCode()</code>
 * так, чтобы <code>equals()</code> сравнивал экземпляры <code>ComplexNumber</code> по содержимому полей <code>re</code>
 * и <code>im</code>, а <code>hashCode()</code> был бы согласованным с реализацией <code>equals()</code>.
 * <p>Реализация <code>hashCode()</code>, возвращающая константу или не учитывающая дробную часть <code>re</code> и
 * <code>im</code>, засчитана не будет
 * <p>Пример:
 * <blockquote><pre>{@code
 * ComplexNumber a = new stepik.javaBasicCourse.ComplexNumber(1, 1);
 * ComplexNumber b = new stepik.javaBasicCourse.ComplexNumber(1, 1);
 * // a.equals(b) must return true
 * // a.hashCode() must be equal to b.hashCode()
 * }</pre></blockquote>
 * <p>Подсказка 1. Поищите в классе <code>javaBasicCourse.lang.Double</code> статический метод, который поможет в
 * решении задачи.
 * <p>Подсказка 2. Если задача никак не решается, можно позвать на помощь среду разработки, которая умеет сама
 * генерировать <code>equals()</code> и <code>hashCode()</code>. Если вы воспользовались помощью IDE, то разберитесь,
 * что было сгенерировано и почему оно выглядит именно так. Когда вас на собеседовании попросят на бумажке реализовать
 * <code>equals()</code> и <code>hashCode()</code> для какого-нибудь простого класса, то среда разработки помочь не
 * сможет.
 *
 * @author Sergei Ermenkov
 */
public class l3_4_9_ComplexNumber {
    public static void main(String[] args) {
        ComplexNumber a = new ComplexNumber(1, 1);
        ComplexNumber b = new ComplexNumber(1, 1);
        System.out.println(a.equals(b));
        System.out.println(a.hashCode() == b.hashCode());
    }
}

final class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexNumber)) return false;
        ComplexNumber that = (ComplexNumber) o;
        return Double.compare(that.re, re) == 0 && Double.compare(that.im, im) == 0;
    }

    @Override
    public int hashCode() {
        int result = 17;
        long temp;
        temp = Double.doubleToLongBits(re);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(im);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}