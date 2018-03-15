package stepik.algorimsMethods;

import java.util.*;

/**
 * Решение задания 4.2.6 по курсу "Алгоритмы: теория и практика. Методы" на stepik.org
 * <p>Задание:
 * <p>Декодирование Хаффмана
 * <p>Восстановите строку по её коду и беспрефиксному коду символов.
 * <p>В первой строке входного файла заданы два целых числа <code>k</code> и <code>l</code> через пробел — количество
 * различных букв, встречающихся в строке, и размер получившейся закодированной строки, соответственно. В следующих
 * <code>k</code> строках записаны коды букв в формате <code>"letter: code"</code>. Ни один код не является префиксом
 * другого. Буквы могут быть перечислены в любом порядке. В качестве букв могут встречаться лишь строчные буквы
 * латинского алфавита; каждая из этих букв встречается в строке хотя бы один раз. Наконец, в последней строке записана
 * закодированная строка. Исходная строка и коды всех букв непусты. Заданный код таков, что закодированная строка имеет
 * минимальный возможный размер.
 * <p>В первой строке выходного файла выведите строку <code>s</code>. Она должна состоять из строчных букв латинского
 * алфавита. Гарантируется, что длина правильного ответа не превосходит <code>10^4</code> символов.
 * <blockquote><pre>{@code
 * Sample Input 1:
 * 1 1
 * a: 0
 * 0
 * Sample Output 1: a
 *
 * Sample Input 2:
 * 4 14
 * a: 0
 * b: 10
 * c: 110
 * d: 111
 * 01001100100111
 * Sample Output 2: abacabad
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l4_2_6_HuffmanDecoding {
    public static void main(String[] args) {
        new l4_2_6_HuffmanDecoding().huffmanDecoding();
    }

    private void huffmanDecoding(){
        int numberLater;
        Queue<TreeNode> nodesQueue = new PriorityQueue<>(Comparator.comparingInt(TreeNode::getCodeSize).reversed().thenComparing(TreeNode::getCode));
        StringBuilder decode = new StringBuilder();

        Scanner input = new Scanner(System.in);
        numberLater = input.nextInt();
        input.nextLine();

        for (int i = 0; i < numberLater; i++) {
            String[] nextString = input.nextLine().split(": ");
            nodesQueue.add(new Nod(nextString[0].charAt(0), nextString[1]));
        }

        while (nodesQueue.size() > 1){
            TreeNode node1 = nodesQueue.poll();
            TreeNode node2 = nodesQueue.poll();
            nodesQueue.add(new For(node1, node2));
        }

        StringBuilder codingString = new StringBuilder(input.nextLine());
        if (numberLater == 1) {
            while (codingString.length() > 0){
                decode.append(nodesQueue.element().decode(codingString));
                codingString.deleteCharAt(0);
            }
        }else {
            while (codingString.length() > 0) {
                decode.append(nodesQueue.element().decode(codingString));
            }
        }
        System.out.println(decode.toString());
    }
}

interface TreeNode {
    String getCode();
    int getCodeSize();
    Character decode(StringBuilder code);
}

class Nod implements TreeNode {
    private String code;
    private Character character;
    private int codeSize;

    Nod(Character character, String code) {
        this.code = code;
        this.character = character;
        codeSize = code.length();
    }

    @Override
    public Character decode(StringBuilder code) {
        return character;
    }

    @Override
    public int getCodeSize() {
        return codeSize;
    }

    @Override
    public String getCode() {
        return code;
    }

}

class For implements TreeNode {
    private String code;
    private TreeNode left;
    private TreeNode right;
    private int codeSize;

    For(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
        code = left.getCode().substring(0, left.getCode().length()-1);
        codeSize = code.length();
    }

    @Override
    public Character decode(StringBuilder code) {
        Character i = code.charAt(0);
        code.deleteCharAt(0);
        if (i == '0'){
            return left.decode(code);
        } else {
            return right.decode(code);
        }
    }

    @Override
    public int getCodeSize() {
        return codeSize;
    }

    @Override
    public String getCode() {
        return code;
    }

}
