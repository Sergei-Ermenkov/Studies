package stepik.javaBasicCourse;

/**
 * Решение задания 2.1.6 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте метод, возвращающий <code>true</code>, если среди четырех его аргументов ровно два истинны (любые). Во
 * всех остальных случаях метод должен возвращать <code>false</code>.
 * <p>Воспользуйтесь шаблоном кода, который предлагает система. Ввод-вывод будет сделан за вас. Вам надо только
 * проанализировать переданные в метод <code>booleanExpression</code> значения <code>(a, b, c, d)</code> и вернуть
 * результат. Попробуйте составить формулу с использованием булевых операторов. Если не получается, вернитесь к этому
 * заданию после просмотра степов про условные операторы и циклы.
 * <p>При записи сложных выражений рекомендуется использовать скобки, чтобы не запутаться в порядке применения
 * операторов.
 * <p>В качестве примера уже указано заведомо некорректное решение задачи. Исправьте его.
 * <p>Совет тем, у кого не проходит какой-то из тестов. В данной задаче возможно всего 16 комбинаций значений входных
 * параметров. Их можно выписать на бумажку, посчитать для них правильные ответы и сравнить с тем, что выдает ваше
 * решение.
 * <blockquote><pre>{@code
 * Sample Input 1:false false false false
 * Sample Output 1:false
 *
 * Sample Input 2:true true true true
 * Sample Output 2:false
 *
 * Sample Input 3:false false true true
 * Sample Output 3:true
 * }</pre></blockquote
 *
 * @author Sergei Ermenkov
 */
public class l2_1_6_BooleanExpression {
    public static void main(String[] args) {
        System.out.println(booleanExpression(false, false, false, false));
        System.out.println(booleanExpression(true, true, true, true));
        System.out.println(booleanExpression(false, false, true, true));
    }

    /**
     * Boolean operations
     *
     * @param a first argument
     * @param b second argument
     * @param c third argument
     * @param d аourth argument
     * @return returns <code>true</code>, if four of its arguments are exactly two true (any).
     * In all other cases, the method returns <code>false</code>.
     */
    public static boolean booleanExpression(boolean a, boolean b, boolean c, boolean d) {
        return (!a && !b && c && d) || (!a && b && !c && d) || (!a && b && c && !d) || (a && !b && !c && d) ||
                (a && !b && c && !d) || (a && b && !c && !d);
    }
}
