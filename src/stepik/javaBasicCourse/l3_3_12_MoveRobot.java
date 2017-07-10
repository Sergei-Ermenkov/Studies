package stepik.javaBasicCourse;

enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT

}

/**
 * Решение задания 3.3.12 по курсу "Java.Базовый курс" на stepik.org
 * <p>Задание:
 * <p>На игровом поле находится робот. Позиция робота на поле описывается двумя целочисленным координатами: X и Y.
 * Ось X смотрит слева направо, ось Y — снизу вверх. (Помните, как рисовали графики функций в школе?)
 * <p>В начальный момент робот находится в некоторой позиции на поле. Также известно, куда робот смотрит: вверх, вниз,
 * направо или налево. Ваша задача — привести робота в заданную точку игрового поля.
 * <p>Робот описывается классом <code>Robot</code>. Вы можете пользоваться следующими его методами (реализация вам
 * неизвестна):
 * <blockquote><pre>{@code
 * public class Robot {
 *
 *     public Direction getDirection() {
 *         // текущее направление взгляда
 *     }
 *
 *     public int getX() {
 *         // текущая координата X
 *     }
 *
 *     public int getY() {
 *         // текущая координата Y
 *     }
 *
 *     public void turnLeft() {
 *         // повернуться на 90 градусов против часовой стрелки
 *     }
 *
 *     public void turnRight() {
 *         // повернуться на 90 градусов по часовой стрелке
 *     }
 *
 *     public void stepForward() {
 *         // шаг в направлении взгляда
 *         // за один шаг робот изменяет одну свою координату на единицу
 *     }
 * }
 * }</pre></blockquote>
 * <p><code>Direction</code>, направление взгляда робота,  — это перечисление:
 * <blockquote><pre>{@code
 * public enum Direction {
 *     UP,
 *     DOWN,
 *     LEFT,
 *     RIGHT
 * }
 * }</pre></blockquote>
 * <p>Пример:
 * <p>В метод передано: <code>toX == 3, toY == 0</code>; начальное состояние робота такое: <code>robot.getX() == 0,
 * robot.getY() == 0, robot.getDirection() == Direction.UP</code>
 * <p>Чтобы привести робота в указанную точку (3, 0), метод должен вызвать у робота следующие методы:
 * <blockquote><pre>{@code
 * robot.turnRight();
 * robot.stepForward();
 * robot.stepForward();
 * robot.stepForward();
 * }</pre></blockquote>
 * <p>P.S. Если вам понравилось это задание, то вам может прийтись по душе игра Robocode, в которой надо написать
 * алгоритм управления танком. Алгоритмы, написанные разными людьми, соревнуются между собой.
 *
 * @author Sergei Ermenkov
 */
public class l3_3_12_MoveRobot {
    public static void main(String[] args) {
        Robot robot = new Robot(0, 0, Direction.UP);
        moveRobot(robot, 3, 0);
    }

    public static void moveRobot(Robot robot, int toX, int toY) {
        while ((robot.getX() != toX) || (robot.getY() != toY)) {
            switch (robot.getDirection()) {
                case UP:
                    if (robot.getY() < toY) {
                        robot.stepForward();
                    } else {
                        robot.turnRight();
                    }
                    break;
                case DOWN:
                    if (robot.getY() > toY) {
                        robot.stepForward();
                    } else {
                        robot.turnRight();
                    }
                    break;
                case LEFT:
                    if (robot.getX() > toX) {
                        robot.stepForward();
                    } else {
                        robot.turnRight();
                    }
                    break;
                case RIGHT:
                    if (robot.getX() < toX) {
                        robot.stepForward();
                    } else {
                        robot.turnRight();
                    }
            }
        }
    }
}

class Robot {
    private int x;
    private int y;
    private Direction d;

    Robot(int x, int y, Direction d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    public Direction getDirection() {
        // текущее направление взгляда
        return d;
    }

    public int getX() {
        // текущая координата X
        return x;
    }

    public int getY() {
        // текущая координата Y
        return y;
    }

    public void turnLeft() {
        // повернуться на 90 градусов против часовой стрелки
    }

    public void turnRight() {
        switch (d) {
            // повернуться на 90 градусов по часовой стрелке
            case RIGHT:
                d = Direction.DOWN;
                break;
            case LEFT:
                d = Direction.UP;
                break;
            case DOWN:
                d = Direction.LEFT;
                break;
            case UP:
                d = Direction.RIGHT;
                break;
        }
        //Трассировка поворота
        System.out.printf("Вызван метод turnRight(). Направление взгляда теперь: %s%n", d.name());
    }

    public void stepForward() {
        // шаг в направлении взгляда
        // за один шаг робот изменяет одну свою координату на единицу
        switch (d) {
            case RIGHT:
                x++;
                break;
            case LEFT:
                x--;
                break;
            case DOWN:
                y--;
                break;
            case UP:
                y++;
                break;
        }
        //Трассировка движения
        System.out.printf("Вызван метод stepForward() координаты теперь x: %d; y: %d%n", x, y);
    }
}