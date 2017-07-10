package stepik.javaBasicCourse;

enum Label {
    SPAM, NEGATIVE_TEXT, TOO_LONG, OK
}

interface TextAnalyzer {
    Label processText(String text);
}

/**
 * Решение задания 3.5.9 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Пришло время попробовать реализовать иерархию классов определенного вида и решить конкретную задачу.
 * <p>Представим, вы делаете систему фильтрации комментариев на каком-то веб-портале, будь то новости, видео-хостинг, а
 * может даже для системы онлайн-обучения :)
 * <p>Вы хотите фильтровать комментарии по разным критериям, уметь легко добавлять новые фильтры и модифицировать
 * старые.
 * <p>Допустим, мы будем фильтровать спам, комментарии с негативным содержанием и слишком длинные комментарии.
 * Спам будем фильтровать по наличию указанных ключевых слов в тексте.
 * Негативное содержание будем определять по наличию одного из трех смайликов – :( =( :|
 * Слишком длинные комментарии будем определять исходя из данного числа – максимальной длины комментария.
 * <p>Вы решили абстрагировать фильтр в виде следующего интерфейса:
 * <blockquote><pre>{@code
 * interface TextAnalyzer {
 *     Label processText(String text);
 * }
 * }</pre></blockquote>
 * <p>Label – тип-перечисление, которые содержит метки, которыми будем помечать текст:
 * <blockquote><pre>{@code
 * enum Label {
 *     SPAM, NEGATIVE_TEXT, TOO_LONG, OK
 * }
 * }</pre></blockquote>
 * <p>Дальше, вам необходимо реализовать три класса, которые реализуют данный интерфейс: <code>SpamAnalyzer</code>,
 * <code>NegativeTextAnalyzer</code> и <code>TooLongTextAnalyzer</code>.
 * <p>-1. <code>SpamAnalyzer</code> должен конструироваться от массива строк с ключевыми словами. Объект этого класса
 * должен сохранять в своем состоянии этот массив строк в приватном поле <code>keywords</code>.
 * <p>-2. <code>NegativeTextAnalyzer</code> должен конструироваться конструктором по-умолчанию.
 * <p>-3. <code>TooLongTextAnalyzer</code> должен конструироваться от <code>int</code>'а с максимальной допустимой
 * длиной комментария. Объект этого класса должен сохранять в своем состоянии это число в приватном поле
 * <code>maxLength</code>.
 * <p>Наверняка вы уже заметили, что <code>SpamAnalyzer</code> и <code>NegativeTextAnalyzer</code> во многом похожи –
 * они оба проверяют текст на наличие каких-либо ключевых слов (в случае спама мы получаем их из конструктора, в случае
 * негативного текста мы заранее знаем набор грустных смайликов) и в случае нахождения одного из ключевых слов
 * возвращают <code>Label</code> (<code>SPAM</code> и <code>NEGATIVE_TEXT</code> соответственно), а если ничего не
 * нашлось – возвращают <code>OK</code>.
 * <p>Давайте эту логику абстрагируем в абстрактный класс <code>KeywordAnalyzer</code> следующим образом:
 * <p>-1. Выделим два абстрактных метода <code>getKeywords</code> и <code>getLabel</code>, один из которых будет
 * возвращать набор ключевых слов, а второй метку, которой необходимо пометить положительные срабатывания. Нам незачем
 * показывать эти методы потребителям классов, поэтому оставим доступ к ним только для наследников.
 * <p>-2. Реализуем <code>processText</code> таким образом, чтобы он зависел только от <code>getKeywords</code> и
 * <code>getLabel</code>.
 * <p>-3. Сделаем <code>SpamAnalyzer</code> и <code>NegativeTextAnalyzer</code> наследниками
 * <code>KeywordAnalyzer</code> и реализуем абстрактные методы.
 * <p>Последний штрих – написать метод <code>checkLabels</code>, который будет возвращать метку для комментария по
 * набору анализаторов текста. <code>checkLabels</code> должен возвращать первую не-OK метку в порядке данного набора
 * анализаторов, и OK, если все анализаторы вернули OK.
 * <p>Используйте, пожалуйста, модификатор доступа по-умолчанию для всех классов.
 * <p>В итоге, реализуйте классы <code>KeywordAnalyzer</code>, <code>SpamAnalyzer</code>,
 * <code>NegativeTextAnalyzer</code> и <code>TooLongTextAnalyzer</code> и метод <code>checkLabels</code>.
 * <code>TextAnalyzer</code> и <code>Label</code> уже подключены, лишние импорты вам не потребуются.
 *
 * @author Sergei Ermenkov
 */
public class l3_5_9_TextAnalyzer {
    public static void main(String[] args) {
        TextAnalyzer[] analyzers = new TextAnalyzer[]{
                new SpamAnalyzer("гол", "хрень"),
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(10),
        };
        l3_5_9_TextAnalyzer mai = new l3_5_9_TextAnalyzer();
        System.out.println(mai.checkLabels(analyzers, "В хрень всв"));
    }

    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer analyzer : analyzers) {
            Label leb;
            leb = analyzer.processText(text);
            if (leb != Label.OK) {
                return leb;
            }
        }
        return Label.OK;
    }
}

abstract class KeywordAnalyzer implements TextAnalyzer {
    protected abstract String[] getKeywords();

    protected abstract Label getLabel();

    @Override
    public Label processText(String text) {
        for (String str : getKeywords()) {
            if (text.contains(str)) {
                return getLabel();
            }
        }
        return Label.OK;
    }
}

class SpamAnalyzer extends KeywordAnalyzer {
    private String[] keywords;

    SpamAnalyzer(String... keys) {
        keywords = new String[keys.length];
        System.arraycopy(keys, 0, keywords, 0, keys.length);
    }

    @Override
    protected String[] getKeywords() {
        return keywords;
    }

    @Override
    protected Label getLabel() {
        return Label.SPAM;
    }
}

class NegativeTextAnalyzer extends KeywordAnalyzer {
    private String[] negativ = {":(", "=(", ":|"};

    @Override
    protected String[] getKeywords() {
        return negativ;
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}

class TooLongTextAnalyzer implements TextAnalyzer {
    private int maxLength;
    TooLongTextAnalyzer(int max) {
        maxLength = max;
    }

    @Override
    public Label processText(String text) {
        if (text.length() > maxLength) {
            return Label.TOO_LONG;
        }
        return Label.OK;
    }
}

