package stepik.javaBasicCourse;

import java.io.*;
import java.util.Objects;

/**
 * Решение задания 5.4.8 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Дан сериализуемый класс <code>Animal</code>:
 * <blockquote><pre>{@code
 * class Animal implements Serializable {
 *     private final String name;
 *
 *     public Animal(String name) {
 *         this.name = name;
 *     }
 *
 *     @Override
 *     public boolean equals(Object obj) {
 *         if (obj instanceof Animal) {
 *             return Objects.equals(name, ((Animal) obj).name);
 *         }
 *         return false;
 *     }
 * }
 * }</pre></blockquote>
 * <p>Реализуйте метод, который из переданного массива байт восстановит массив объектов <code>Animal</code>. Массив байт
 * устроен следующим образом. Сначала идет число типа <code>int</code>, записанное при помощи
 * <code>ObjectOutputStream.writeInt(size)</code>. Далее подряд записано указанное количество объектов типа
 * <code>Animal</code>, сериализованных при помощи <code>ObjectOutputStream.writeObject(animal)</code>.
 * <p>Если вдруг массив байт не является корректным представлением массива экземпляров <code>Animal</code>, то метод
 * должен бросить исключение <code>IllegalArgumentException</code>.
 * <p>Причины некорректности могут быть разные. Попробуйте подать на вход методу разные некорректные данные и
 * посмотрите, какие исключения будут возникать. Вот их-то и нужно превратить в <code>IllegalArgumentException</code> и
 * выбросить. Если что-то забудете, то проверяющая система подскажет. Главное не глотать никаких исключений, т.е. не
 * оставлять нигде пустой <code>catch</code>.
 *
 * @author Sergei Ermenkov
 */
public class l5_4_8_DeserializeAnimalArray {
    public static void main(String[] args) throws IOException {
        byte[] data;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            //oos.writeInt(3);
            oos.writeObject(new Animal("Cat"));
            oos.writeObject(new Animal("Dog"));
            oos.writeObject(new Animal("Mouse"));
            data = outputStream.toByteArray();
        }
        //Восстановление из потока бит
        Animal[] arrayAnimal = deserializeAnimalArray(data);
    }

    static Animal[] deserializeAnimalArray(byte[] data) {
        Animal[] animalArray;
        int elementNumber;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            elementNumber = ois.readInt();
            animalArray = new Animal[elementNumber];
            for (int i = 0; i < elementNumber; i++) {
                animalArray[i] = (Animal) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            throw new IllegalArgumentException(e);
        }
        return animalArray;
    }
}

class Animal implements Serializable {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal) {
            return Objects.equals(name, ((Animal) obj).name);
        }
        return false;
    }
}

