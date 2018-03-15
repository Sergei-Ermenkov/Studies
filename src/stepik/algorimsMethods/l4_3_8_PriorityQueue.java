package stepik.algorimsMethods;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Решение задания 4.3.8 по курсу "Алгоритмы: теория и практика. Методы" на stepik.org
 * <p>Задание:
 * <p>Очередь с приоритетами
 * <p>Первая строка входа содержит число операций <code>1≤n≤10^5</code>. Каждая из последующих <code>n</code> строк
 * задают операцию одного из следующих двух типов:
 * <p>- Insert x, где <code>0≤x≤10^9<code> — целое число;
 * <p>- ExtractMax
 * <p>Первая операция добавляет число <code>x</code> в очередь с приоритетами, вторая — извлекает максимальное число и
 * выводит его.
 * <blockquote><pre>{@code
 * Sample Input:
 * 6
 * Insert 200
 * Insert 10
 * ExtractMax
 * Insert 5
 * Insert 500
 * ExtractMax
 * <p>
 * Sample Output:
 * 200
 * 500
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l4_3_8_PriorityQueue {
    public static void main(String[] args) {
        new l4_3_8_PriorityQueue().PriorityQueue();
    }

    private void PriorityQueue(){
        int numberOfCommand;
        PQ priorQueue = new PQ();

        Scanner input = new Scanner(System.in);
        numberOfCommand = input.nextInt();
        input.nextLine();

        for (int i = 0; i < numberOfCommand; i++) {
            String[] nextString = input.nextLine().split(" ");
            if ("Insert".equals(nextString[0])){
                priorQueue.insert(Integer.valueOf(nextString[1]));
            }
            if ("ExtractMax".equals(nextString[0])){
                System.out.println(priorQueue.extractMax().toString());
            }
        }
    }
}


class PQ {
    private ArrayList<Integer> massiv = new ArrayList<>();

    void insert(Integer number) {
        massiv.add(number);
        if (massiv.size() > 1) {
            siftUp(massiv.size());
        }
    }

    Integer extractMax() {
        Integer max = massiv.get(0);
        if (massiv.size() > 1) {
            massiv.set(0, massiv.get(massiv.size() - 1));
            massiv.remove(massiv.size() - 1);
            siftDown(1);
        } else {
            massiv.remove(massiv.size() - 1);
        }
        return max;
    }

    private void siftDown(int pos) {
        int nextStep;
        int leftChild = 2 * pos;
        int rightChild = 2 * pos + 1;
        boolean hasLeft = leftChild <= massiv.size();
        boolean hasRight = rightChild <= massiv.size();

        if (hasLeft){
            nextStep = leftChild;
            if(hasRight){
                nextStep =  massiv.get(leftChild - 1) > massiv.get(rightChild - 1) ? leftChild : rightChild;
            }

            if (massiv.get(pos - 1) < massiv.get(nextStep - 1)) {
                rotate(pos, nextStep);
                siftDown(nextStep);
            }
        }
    }

    private void siftUp(int pos) {
        if (pos > 1) {
            int parentPos = pos / 2;
            if (massiv.get(pos - 1) > massiv.get(parentPos - 1)) {
                rotate(pos, parentPos);
                siftUp(parentPos);
            }
        }
    }

    private void rotate(int first, int second) {
        int value = massiv.get(first - 1);
        massiv.set(first - 1, massiv.get(second - 1));
        massiv.set(second - 1, value);
    }
}
