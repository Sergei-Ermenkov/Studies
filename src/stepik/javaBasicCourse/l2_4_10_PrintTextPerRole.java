package stepik.javaBasicCourse;

import java.util.ArrayList;

/**
 * Решение задания 2.4.10 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Вам дан список ролей и сценарий пьесы в виде массива строчек.
 * <p>Каждая строчка сценария пьесы дана в следующем виде:
 * <blockquote><pre>{@code
 * Роль: текст
 * }</pre></blockquote
 * <p>Текст может содержать любые символы.
 * <p>Напишите метод, который будет группировать строчки по ролям, пронумеровывать их и возвращать результат в виде
 * готового текста (см. пример). Каждая группа распечатывается в следующем виде:
 * <blockquote><pre>{@code
 * Роль:
 * i) текст
 * j) текст2
 * ...
 * ==перевод строки==
 * }</pre></blockquote>
 * <p><code>i</code> и <code>j</code> -- номера строк в сценарии. Индексация строчек начинается с единицы, выводить
 * группы следует в соответствии с порядком ролей. Переводы строк между группами обязательны, переводы строк в конце
 * текста не учитываются.
 * <p>Заметим, что вам предстоит обработка огромной пьесы в 50 000 строк для 10 ролей – соответственно, неправильная
 * сборка результирующей строчки может выйти за ограничение по времени.
 * <p>Обратите внимание еще на несколько нюансов:
 * <p>имя персонажа может встречаться в строке более одного раза, в том числе с двоеточием;
 * название одной роли может быть префиксом названия другой роли (например, "Лука" и "Лука Лукич");
 * роль, у которой нет реплик, тоже должна присутствовать в выходном файле;
 * в качестве перевода строки надо использовать символ '<code>\n</code>' (перевод строки в стиле UNIX);
 * будьте внимательны, не добавляйте лишних пробелов в конце строк.
 * <blockquote><pre>{@code
 * Sample Input:
 * roles:
 * Городничий
 * Аммос Федорович
 * Артемий Филиппович
 * Лука Лукич
 *
 * textLines:
 * Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.
 * Аммос Федорович: Как ревизор?
 * Артемий Филиппович: Как ревизор?
 * Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.
 * Аммос Федорович: Вот те на!
 * Артемий Филиппович: Вот не было заботы, так подай!
 * Лука Лукич: Господи боже! еще и с секретным предписаньем!
 *
 *
 * Sample Output:
 * Городничий:
 * 1) Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.
 * 4) Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.
 *
 * Аммос Федорович:
 * 2) Как ревизор?
 * 5) Вот те на!
 *
 * Артемий Филиппович:
 * 3) Как ревизор?
 * 6) Вот не было заботы, так подай!
 *
 * Лука Лукич:
 * 7) Господи боже! еще и с секретным предписаньем!
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l2_4_10_PrintTextPerRole {

    public static void main(String[] args) {
        String[] roles = {
                "Аммос Федорович",
                "Артемий Филиппович",
                "Городничий",
                "Лука",
                "Лука Лукич",
        };
        String[] textLines = {
                "Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
                "Аммос Федорович: Как ревизор?",
                "Артемий Филиппович: Как ревизор?",
                "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
                "Аммос Федорович: Вот те на!",
                "Артемий Филиппович: Вот не было заботы, так подай!",
                "Лука Лукич: Господи боже! еще и с секретным предписаньем!",
        };
        System.out.println(printTextPerRole(roles, textLines));

    }

    /**
     * Grouping scenario by roles.
     *
     * @param roles roles
     * @param textLines scenario
     * @return scenario grouped by roles
     */
    public static String printTextPerRole(String[] roles, String[] textLines) {
        StringBuilder sumString = new StringBuilder();
        ArrayList<ArrayList<String>> rolCollect = new ArrayList<>();
        for (int i = 0; i < roles.length; i++) {
            rolCollect.add(new ArrayList<>());
        }
        for (int i = 0; i < textLines.length; i++) {
            for (int j = 0; j < roles.length; j++) {
                if (textLines[i].startsWith(roles[j].concat(":"))) {
                    rolCollect.get(j).add(textLines[i].replaceAll("^(.*?:)", i + 1 + ")"));
                    break;
                }
            }
        }
        for (int i = 0; i < rolCollect.size(); i++) {
            sumString.append(roles[i].concat(":\n"));
            for (String str : rolCollect.get(i)) {
                sumString.append(str.concat("\n"));
            }
            sumString.append("\n");
        }
        return sumString.toString();
    }
}