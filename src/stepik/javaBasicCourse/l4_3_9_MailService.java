package stepik.javaBasicCourse;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
Интерфейс: сущность, которую можно отправить по почте.
У такой сущности можно получить от кого и кому направляется письмо.
*/
interface Sendable {
    String getFrom();

    String getTo();
}

/*
Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
*/
interface MailService {
    Sendable processMail(Sendable mail);
}

/**
 * Решение задания 4.3.9 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>Это задачка совмещает тренировку по материалу предыдущих двух модулей – необходимо разобраться и написать
 * объект-ориентированный код и при этом коснуться свежих тем – исключений и логирования.
 * <p>Дан набор классов, описывающих работу гипотетической почтовой системы.
 * <p>Для начала рассмотрим код, описывающий все используемые сущности.
 * <blockquote><pre>{@code
 * /*
 * Интерфейс: сущность, которую можно отправить по почте.
 * У такой сущности можно получить от кого и кому направляется письмо.
 * * /
 * public static interface Sendable {
 *     String getFrom();
 *     String getTo();
 * }
 * }</pre></blockquote>
 * <p>У Sendable есть два наследника, объединенные следующим абстрактным классом:
 * <blockquote><pre>{@code
 * /*
 * Абстрактный класс,который позволяет абстрагировать логику хранения
 * источника и получателя письма в соответствующих полях класса.
 * * /
 * public static abstract class AbstractSendable implements Sendable {
 *
 *     protected final String from;
 *     protected final String to;
 *
 *     public AbstractSendable(String from, String to) {
 *         this.from = from;
 *         this.to = to;
 *     }
 *
 *     @Override public String getFrom() {
 *         return from;
 *     }
 *
 *     @Override public String getTo() {
 *         return to;
 *     }
 *
 *     @Override public boolean equals(Object o) {
 *         if (this == o) return true;
 *         if (o == null || getClass() != o.getClass()) return false;
 *
 *         AbstractSendable that = (AbstractSendable) o;
 *
 *         if (!from.equals(that.from)) return false;
 *         if (!to.equals(that.to)) return false;
 *
 *         return true;
 *     }
 * }
 * }</pre></blockquote>
 * <p>Первый класс описывает обычное письмо, в котором находится только текстовое сообщение.
 * <blockquote><pre>{@code
 * /*
 * Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
 * * /
 * public static class MailMessage extends AbstractSendable {
 *
 *     private final String message;
 *
 *     public MailMessage(String from, String to, String message) {
 *         super(from, to);
 *         this.message = message;
 *     }
 *
 *     public String getMessage() {
 *         return message;
 *     }
 *
 *     @Override
 *     public boolean equals(Object o) {
 *         if (this == o) return true;
 *         if (o == null || getClass() != o.getClass()) return false;
 *         if (!super.equals(o)) return false;
 *
 *         MailMessage that = (MailMessage) o;
 *
 *         if (message != null ? !message.equals(that.message) : that.message != null) return false;
 *
 *         return true;
 *     }
 * }
 * }</pre></blockquote>
 * <p>Второй класс описывает почтовую посылку:
 * <blockquote><pre>{@code
 * /*
 * Посылка, содержимое которой можно получить с помощью метода `getContent`
 * * /
 * public static class MailPackage extends AbstractSendable {
 *     private final Package content;
 *
 *     public MailPackage(String from, String to, Package content) {
 *         super(from, to);
 *         this.content = content;
 *     }
 *
 *     public Package getContent() {
 *         return content;
 *     }
 *
 *     @Override
 *     public boolean equals(Object o) {
 *         if (this == o) return true;
 *         if (o == null || getClass() != o.getClass()) return false;
 *         if (!super.equals(o)) return false;
 *
 *         MailPackage that = (MailPackage) o;
 *
 *         if (!content.equals(that.content)) return false;
 *
 *         return true;
 *     }
 * }
 * }</pre></blockquote>
 * <p>При этом сама посылка описывается следующим классом:
 * <blockquote><pre>{@code
 * /*
 * Класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность.
 * * /
 * public static class Package {
 *     private final String content;
 *     private final int price;
 *
 *     public Package(String content, int price) {
 *         this.content = content;
 *         this.price = price;
 *     }
 *
 *     public String getContent() {
 *         return content;
 *     }
 *
 *     public int getPrice() {
 *         return price;
 *     }
 *
 *     @Override
 *     public boolean equals(Object o) {
 *         if (this == o) return true;
 *         if (o == null || getClass() != o.getClass()) return false;
 *
 *         Package aPackage = (Package) o;
 *
 *         if (price != aPackage.price) return false;
 *         if (!content.equals(aPackage.content)) return false;
 *
 *         return true;
 *     }
 * }
 * }</pre></blockquote>
 * <p>Теперь рассмотрим классы, которые моделируют работу почтового сервиса:
 * <blockquote><pre>{@code
 * /*
 * Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
 * * /
 * public static interface MailService {
 *     Sendable processMail(Sendable mail);
 * }
 *
 * /*
 * Класс, в котором скрыта логика настоящей почты
 * * /
 * public static class RealMailService implements MailService {
 *
 *     @Override
 *     public Sendable processMail(Sendable mail) {
 *         // Здесь описан код настоящей системы отправки почты.
 *         return mail;
 *     }
 * }
 * }</pre></blockquote>
 * <p>Вам необходимо описать набор классов, каждый из которых является <code>MailService</code>:
 * <p>1) <code>UntrustworthyMailWorker</code> – класс, моделирующий ненадежного работника почты, который вместо того,
 * чтобы передать почтовый объект непосредственно в сервис почты, последовательно передает этот объект набору третьих
 * лиц, а затем, в конце концов, передает получившийся объект непосредственно экземпляру <code>RealMailService</code>.
 * У <code>UntrustworthyMailWorker</code> должен быть конструктор от массива <code>MailService</code> ( результат вызова
 * <code>processMail</code> первого элемента массива передается на вход <code>processMail</code> второго элемента, и
 * т. д.) и метод <code>getRealMailService</code>, который возвращает ссылку на внутренний экземпляр
 * <code>RealMailService</code>.
 * <p>2) <code>Spy</code> – шпион, который логгирует о всей почтовой переписке, которая проходит через его руки. Объект
 * конструируется от экземпляра <code>Logger</code>, с помощью которого шпион будет сообщать о всех действиях. Он следит
 * только за объектами класса <code>MailMessage</code> и пишет в логгер следующие сообщения (в выражениях нужно заменить
 * части в фигурных скобках на значения полей почты):
 * <p>2.1) Если в качестве отправителя или получателя указан <code>"Austin Powers"</code>, то нужно написать в лог
 * сообщение с уровнем <code>WARN: Detected target mail correspondence: from {from} to {to} "{message}"</code>
 * <p>2.2) Иначе, необходимо написать в лог сообщение с уровнем <code>INFO: Usual correspondence: from {from} to
 * {to}</code>
 * <p>3) <code>Thief</code> – вор, который ворует самые ценные посылки и игнорирует все остальное. Вор принимает в
 * конструкторе переменную <code>int</code> – минимальную стоимость посылки, которую он будет воровать. Также, в данном
 * классе должен присутствовать метод <code>getStolenValue</code>, который возвращает суммарную стоимость всех посылок,
 * которые он своровал. Воровство происходит следующим образом: вместо посылки, которая пришла вору, он отдает новую,
 * такую же, только с нулевой ценностью и содержимым посылки <code>"stones instead of {content}"</code>.
 * <p>4) <code>Inspector</code> – Инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в
 * виде исключения, если была обнаружена подобная посылка. Если он заметил запрещенную посылку с одним из запрещенных
 * содержимым (<code>"weapons"</code> и <code>"banned substance"</code>), то он бросает
 * <code>IllegalPackageException</code>. Если он находит посылку, состоящую из камней (содержит слово
 * <code>"stones"</code>), то тревога прозвучит в виде <code>StolenPackageException</code>. Оба исключения вы должны
 * объявить самостоятельно в виде непроверяемых исключений.
 * <p>Все классы должны быть определены как публичные и статические, так как в процессе проверки ваш код будет
 * подставлен во внешний класс, который занимается тестированием и проверкой структуры. Для удобства во внешнем
 * классе объявлено несколько удобных констант и импортировано все содержимое пакета <code>java.util.logging</code>. Для
 * определения, посылкой или письмом является <code>Sendable</code> объект воспользуйтесь оператором
 * <code>instanceof</code>.
 * <blockquote><pre>{@code
 * public static final String AUSTIN_POWERS = "Austin Powers";
 * public static final String WEAPONS = "weapons";
 * public static final String BANNED_SUBSTANCE = "banned substance";
 * }</pre></blockquote>
 *
 * @author Sergei Ermenkov
 */
public class l4_3_9_MailService {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static void main(String[] args) {
        Spy spy = new Spy(Logger.getLogger("MyLogger"));
        Thief thief = new Thief(10);
        Inspector inspector = new Inspector();
        MailService[] mailServices = new MailService[]{
                spy,
                thief,
                inspector,
        };
        UntrustworthyMailWorker man = new UntrustworthyMailWorker(mailServices);
        MailMessage mail1 = new MailMessage("Jack", "Georg", "Hi Georg");
        MailMessage mail2 = new MailMessage("Austin Powers", "Georg", "Hi Georg");
        MailMessage mail3 = new MailMessage("Jack", "Austin Powers", "Hi Georg");
        MailPackage package1 = new MailPackage("Georg", "Austin Powers", new Package("Aggs", 5));
        MailPackage package2 = new MailPackage("Georg", "Jack", new Package("Pets", 10));
        MailPackage package3 = new MailPackage("Austin Powers", "Jack", new Package(l4_3_9_MailService.WEAPONS, 15));
        MailPackage package4 = new MailPackage("Austin Powers", "Jack", new Package(l4_3_9_MailService.BANNED_SUBSTANCE, 15));
        MailPackage package5 = new MailPackage("Austin Powers", "Jack", new Package(" some stones like", 15));
        man.processMail(mail1);
        //man.processMail(mail3);
        //man.processMail(package1);
        //man.processMail(package1);
        //System.out.println(thief.getStolenValue());
    }
}

class UntrustworthyMailWorker implements MailService {
    private MailService[] listMailService;
    private RealMailService realMailService = new RealMailService();

    UntrustworthyMailWorker(MailService[] listMailService) {
        this.listMailService = new MailService[listMailService.length];
        System.arraycopy(listMailService, 0, this.listMailService, 0, listMailService.length);
    }

    @Override
    public Sendable processMail(Sendable mail) {
        for (MailService service : listMailService) {
            mail = service.processMail(mail);
        }
        return realMailService.processMail(mail);
    }
    RealMailService getRealMailService() {
        return realMailService;
    }
}

class Spy implements MailService {
    private Logger logger;

    Spy(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailMessage) {
            if (mail.getFrom().equals(l4_3_9_MailService.AUSTIN_POWERS) ||
                    mail.getTo().equals(l4_3_9_MailService.AUSTIN_POWERS)) {
                logger.log(Level.WARNING,
                        "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                        new Object[]{mail.getFrom(), mail.getTo(), ((MailMessage) mail).getMessage()});
            } else {
                logger.log(Level.INFO, "Usual correspondence: from {0} to {1}",
                        new Object[]{mail.getFrom(), mail.getTo()});
            }
        }
        return mail;
    }
}

class Thief implements MailService {
    private int stolenBarrierValue;
    private int totalStolenValue;

    Thief(int stolenBarrierValue) {
        this.stolenBarrierValue = stolenBarrierValue;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            int costPackage = ((MailPackage) mail).getContent().getPrice();
            if (costPackage >= stolenBarrierValue) {
                Package newPackage = new Package("stones instead of " + ((MailPackage) mail).getContent().getContent(), 0);
                MailPackage newMailPackage = new MailPackage(mail.getFrom(), mail.getTo(), newPackage);
                totalStolenValue += costPackage;
                mail = newMailPackage;
            }
        }
        return mail;
    }

    public int getStolenValue() {
        return totalStolenValue;
    }
}

class Inspector implements MailService {
    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            String content = new String(((MailPackage) mail).getContent().getContent());
            if (content.equals(l4_3_9_MailService.WEAPONS) || content.equals(l4_3_9_MailService.BANNED_SUBSTANCE)) {
                throw new IllegalPackageException();
            }
            if (content.contains("stones")) throw new StolenPackageException();
        }
        return mail;
    }
}

class StolenPackageException extends RuntimeException {
    public StolenPackageException() {
    }
}

class IllegalPackageException extends RuntimeException {
    public IllegalPackageException() {
    }
}

/*
Абстрактный класс,который позволяет абстрагировать логику хранения
источника и получателя письма в соответствующих полях класса.
*/
abstract class AbstractSendable implements Sendable {
    protected final String from;
    protected final String to;

    public AbstractSendable(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractSendable that = (AbstractSendable) o;
        if (!from.equals(that.from)) return false;
        if (!to.equals(that.to)) return false;
        return true;
    }

}

/*
Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
*/
class MailMessage extends AbstractSendable {
    private final String message;

    public MailMessage(String from, String to, String message) {
        super(from, to);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MailMessage that = (MailMessage) o;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return true;
    }

}

/*
Посылка, содержимое которой можно получить с помощью метода `getContent`
*/
class MailPackage extends AbstractSendable {
    private final Package content;

    public MailPackage(String from, String to, Package content) {
        super(from, to);
        this.content = content;
    }

    public Package getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MailPackage that = (MailPackage) o;
        if (!content.equals(that.content)) return false;
        return true;
    }

}

/*
Класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность.
*/
class Package {
    private final String content;
    private final int price;

    public Package(String content, int price) {
        this.content = content;
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        if (price != aPackage.price) return false;
        if (!content.equals(aPackage.content)) return false;
        return true;
    }
}

/*
Класс, в котором скрыта логика настоящей почты
*/
class RealMailService implements MailService {
    @Override
    public Sendable processMail(Sendable mail) {
        // Здесь описан код настоящей системы отправки почты.
        //А этот код для распичатывания того что на вызоде
        System.out.println();
        System.out.println("От: " + mail.getFrom() + " Кому: " + mail.getTo());
        if (mail instanceof MailMessage) {
            MailMessage m = (MailMessage) mail;
            System.out.println("Письмо");
            System.out.println("Сообщение: " + m.getMessage());
        }
        if (mail instanceof MailPackage) {
            Package p = (Package) ((MailPackage) mail).getContent();
            System.out.println("Посылка");
            System.out.println("Содержимое: " + p.getContent());
            System.out.println("Цена: " + p.getPrice());
        }
        return mail;
    }
}
