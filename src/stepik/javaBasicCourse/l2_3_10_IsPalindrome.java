package stepik.javaBasicCourse;

/**
 * Решение задания 2.3.10 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Реализуйте метод, проверяющий, является ли заданная строка палиндромом. Палиндромом называется строка, которая
 * читается одинаково слева направо и справа налево (в том числе пустая). При определении "палиндромности" строки
 * должны учитываться только буквы и цифры. А пробелы, знаки препинания, а также регистр символов должны игнорироваться.
 * Гарантируется, что в метод попадают только строки, состоящие из символов ASCII (цифры, латинские буквы, знаки
 * препинания). Т.е. русских, китайских и прочих экзотических символов в строке не будет.
 * <p>Воспользуйтесь предоставленным шаблоном. Декларацию класса, метод <code>main</code> и обработку ввода-вывода
 * добавит проверяющая система.
 * <p>Подсказки (не читайте, если хотите решить сами):
 * для удаления из строки всех символов, не являющихся буквами и цифрами, можно воспользоваться регулярным выражением
 * "<code>[^a-zA-Z0-9]</code>"; найдите в классе <code>String</code> метод, выполняющий замену по регулярному выражению;
 * для перестановки символов строки в обратном порядке можно воспользоваться методом <code>reverse()</code>, который
 * находится в классе <code>StringBuilder</code>;
 * в классе <code>String</code> есть методы для преобразования всей строки в верхний и нижний регистр.
 * <blockquote><pre>{@code
 * Sample Input:Madam, I'm Adam!
 * Sample Output:true
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l2_3_10_IsPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("Madam, I'm Adam!"));
        System.out.println(isPalindromeStream("Madam, I'm Adam!"));
    }

    /**
     * Checks if given <code>text</code> is a palindrome.
     *
     * @param text any string
     * @return <code>true</code> when <code>text</code> is a palindrome, <code>false</code> otherwise
     */
    public static boolean isPalindrome(String text) {
        StringBuilder sb = new StringBuilder();
        String newText = text.toLowerCase().replaceAll("[\\W_]", "");
        return sb.append(newText).reverse().toString().equals(newText);
    }

    /**
     * Checks if given <code>text</code> is a palindrome.
     * <p>Uses Stream and functional programming
     *
     * @param text any string
     * @return <code>true</code> when <code>text</code> is a palindrome, <code>false</code> otherwise
     */
    public static boolean isPalindromeStream(String text) {
        StringBuilder leftToRight = new StringBuilder();
        text.chars().filter(Character::isLetterOrDigit)
                .map(Character::toLowerCase)
                .forEach(leftToRight::appendCodePoint);
        StringBuilder rightToLeft = new StringBuilder(leftToRight).reverse();
        return leftToRight.toString().equals(rightToLeft.toString());
    }
}
