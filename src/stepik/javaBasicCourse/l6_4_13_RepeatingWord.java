package stepik.javaBasicCourse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Решение задания 6.4.13 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Напишите программу, читающую из <code>System.in</code> текст в кодировке UTF-8, подсчитывающую в нем частоту
 * появления слов, и в конце выводящую 10 наиболее часто встречающихся слов.
 * <p>Словом будем считать любую непрерывную последовательность символов, состоящую только из букв и цифр. Например, в
 * строке "Мама мыла раму 33 раза!" ровно пять слов: "Мама", "мыла", "раму", "33" и "раза".
 * <p>Подсчет слов должен выполняться без учета регистра, т.е. "МАМА", "мама" и "Мама" — это одно и то же слово.
 * Выводите слова в нижнем регистре.
 * <p>Если в тексте меньше 10 уникальных слов, то выводите сколько есть.
 * <p>Если в тексте некоторые слова имеют одинаковую частоту, т.е. их нельзя однозначно упорядочить только по частоте,
 * то дополнительно упорядочите слова с одинаковой частотой в лексикографическом порядке.
 * <p>Задача имеет красивое решение через стримы без циклов и условных операторов. Попробуйте придумать его.
 * <blockquote><pre>{@code
 * Sample Input 1:
 * Мама мыла-мыла-мыла раму!
 * Sample Output 1:
 * мыла
 * мама
 * раму
 *
 * Sample Input 2:
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim.
 * Sample Output 2:
 * consectetur
 * faucibus
 * ipsum
 * lorem
 * adipiscing
 * amet
 * dolor
 * eget
 * elit
 * mi
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l6_4_13_RepeatingWord {
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            in.lines()
                    .flatMap((String line) -> Stream.of(line.split("[^а-яА-ЯA-Za-z0-9Ёё]+")))
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted((first, next) -> !first.getValue().equals(next.getValue())
                            ? next.getValue().compareTo(first.getValue())
                            : first.getKey().compareTo(next.getKey()))
                    .limit(10)
                    .forEach(word -> System.out.println(word.getKey()));
        }
    }
}