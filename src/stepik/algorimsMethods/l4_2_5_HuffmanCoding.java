package stepik.algorimsMethods;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Решение задания 4.2.5 по курсу "Алгоритмы: теория и практика. Методы" на stepik.org
 * <p>Задание:
 * <p>Кодирование Хаффмана
 * <p>По данной непустой строке <code>s</code> длины не более <code>10^4</code>, состоящей из строчных букв латинского
 * алфавита, постройте оптимальный беспрефиксный код. В первой строке выведите количество различных букв <code>k</code>,
 * встречающихся в строке, и размер получившейся закодированной строки. В следующих <code>k</code> строках запишите
 * коды букв в формате <code>"letter: code"</code>. В последней строке выведите закодированную строку.
 * <blockquote><pre>{@code
 * Sample Input 1: a
 * Sample Output 1:
 * 1 1
 * a: 0
 * 0
 * <p>
 * Sample Input 2:abacabad
 * Sample Output 2:
 * 4 14
 * a: 0
 * b: 10
 * c: 110
 * d: 111
 * 01001100100111
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l4_2_5_HuffmanCoding {
    public static void main(String[] args) {
        new l4_2_5_HuffmanCoding().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        huffmanCoding(string);
    }

    private void huffmanCoding(String string) {
        Queue<TreeNodes> nodesQueue = new PriorityQueue<>(Comparator.comparingLong(TreeNodes::getWeight));
        Map<Character, String> hafmanTable = new HashMap<>();

        Map<Character, Long> chars = string.chars().mapToObj(ch -> ((char) ch))
                // сгруппировать по символам, внутри группы подсчитать количество
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (Map.Entry<Character, Long> entry : chars.entrySet()) {
            nodesQueue.add(new Node(entry.getValue(), entry.getKey()));
        }

        if (nodesQueue.size() == 1) {
            nodesQueue.element().makeHafmanTable(hafmanTable, "0");
        } else {
            while (nodesQueue.size() > 1) {
                TreeNodes node1 = nodesQueue.poll();
                TreeNodes node2 = nodesQueue.poll();
                nodesQueue.add(new Fork(node1, node2));
            }
            nodesQueue.element().makeHafmanTable(hafmanTable, "");
        }

        System.out.println(hafmanTable.size() + " " + nodesQueue.element().getCodeSize());
        for (Map.Entry<Character, String> entry : hafmanTable.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        string.chars().mapToObj(x -> hafmanTable.get((char) x)).forEach(System.out::print);
    }
}

interface TreeNodes {
    Long getWeight();

    void makeHafmanTable(Map<Character, String> hafmanTable, String code);

    int getCodeSize();
}

class Node implements TreeNodes {
    private Long weight;
    private Character character;
    private int codeSize;

    Node(Long weight, Character character) {
        this.weight = weight;
        this.character = character;
    }

    @Override
    public Long getWeight() {
        return weight;
    }

    @Override
    public void makeHafmanTable(Map<Character, String> hafmanTable, String code) {
        codeSize = code.length();
        hafmanTable.put(character, code);
    }

    @Override
    public int getCodeSize() {
        return codeSize * weight.intValue();
    }
}

class Fork implements TreeNodes {
    private TreeNodes left;
    private TreeNodes right;
    private Long weight;

    Fork(TreeNodes left, TreeNodes right) {
        this.left = left;
        this.right = right;
        weight = left.getWeight() + right.getWeight();
    }

    @Override
    public Long getWeight() {
        return weight;
    }

    @Override
    public void makeHafmanTable(Map<Character, String> hafmanTable, String code) {
        left.makeHafmanTable(hafmanTable, code + "0");
        right.makeHafmanTable(hafmanTable, code + "1");
    }

    @Override
    public int getCodeSize() {
        return left.getCodeSize() + right.getCodeSize();
    }
}