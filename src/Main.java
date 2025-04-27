import field.*;
import objects.Castle;
import objects.CastleBuildings;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import warriors.*;

/*
import javax.swing.*;
import java.lang.foreign.PaddingLayout;
import java.sql.SQLOutput;
*/
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Heroes of IU3");
        System.out.println("Добро пожаловать в игру!");
        System.out.println("Начать кампанию или покинуть программу? y/n (д/н)");
        Scanner scanner = new Scanner(System.in);
        String respond = scanner.nextLine();
        if (getAnswer(respond)) {
            startGame(scanner);
        }
    }

    private static boolean getAnswer(String respond) {
        return respond.equalsIgnoreCase("y") ||
                respond.equalsIgnoreCase("д") ||
                respond.equalsIgnoreCase("да");
    }

    private static void startGame(Scanner scanner) {
        Player player = new Player();
        Enemy enemy = new Enemy();

        Field field = new Field(14, 14);

        System.out.println("Дайте имя герою:");
        String name = scanner.nextLine();
        Hero hero = new Hero(name);
        hero.setMainHero(true);
        hero.draw(field);
        hero.setArmy(createNewArmy());

        player.addHero(hero);

        field.draw();

        while (!player.isMainHeroDead()) {
            System.out.println("\nВыберите действие: ");
            System.out.println("1 - Взаимодействовать с " +
                    ((player.getHeroes().size() == 1) ? "героем" : "героями") + "\n" +
                    "2 - Спать (INFO: очки действия восстанавливаются, ресурсы добываются, предсказания будут выдаться.)");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Список героев: ");
                for (int i = 0; i < player.getHeroes().size(); i++) {
                    System.out.println(i + " " + player.getHeroes().get(i).getName() +
                            "  (x: " + player.getHeroes().get(i).getCoordinates().getX() +
                            ", y: " + player.getHeroes().get(i).getCoordinates().getY() + ")");
                }
                System.out.println("Введите номер героя из списка: ");
                choice = scanner.nextInt();
                hero = player.getHeroes().get(choice);
                System.out.println("Список доступных действий: " +
                        "\n0 - Посмотреть информацию о герое" +
                        "\n1 - Отправить героя в новое место" +
                        ((hero.canInteractWithCastle(field)) ? "\n2 - Зайти в замок" : "") +
                        ((hero.canInteractWithTreasure(field)) ? "\n3 - Открыть сокровище" : "") +
                        ((hero.canInteractWithEnemy(field)) ? "\n4 - Бороться с врагом" : ""));
                choice = scanner.nextInt();
                if (choice == 0) {
                    hero.show();
                }
                else if (choice == 1) {
                    moveHero(scanner, field, hero);
                }
                else if (choice == 2 && hero.canInteractWithCastle(field)) {
                    interactWithCastle(scanner, field, hero, player);
                }
                else if (choice == 3 && hero.canInteractWithTreasure(field)) {
                    getTreasure(player);
                }
                else if (choice == 4 && hero.canInteractWithEnemy(field)) { // Для активации боя todo auto-battle
                    System.out.println("Герой " + hero.getName() + " решает сразиться с врагом.");
                    int yourHp = 0;
                    int yourDefence = 0;
                    int yourAttack = 0;
                    for (Map.Entry<Warrior, Integer> entry : hero.getArmy().entrySet()) {
                        Warrior key = entry.getKey();
                        Integer value = entry.getValue();
                        yourHp += key.getHp() * value;
                        yourDefence += key.getDefence() * value;
                        yourAttack += key.getAttack() * value;
                    }

                    int enemyHp = 0;
                    int enemyDefence = 0;
                    int enemyAttack = 0;

                    for (Map.Entry<Warrior, Integer> entry : enemy.heroes.getFirst().getArmy().entrySet()) {
                        Warrior key = entry.getKey();
                        Integer value = entry.getValue();
                        enemyHp += key.getHp() * value;
                        enemyDefence += key.getDefence() * value;
                        enemyAttack += key.getAttack() * value;
                    }
                    System.out.println("Ваш герой:\nHit Points: " + yourHp +
                            "\nDefence: " + yourDefence +
                            "\nAttack: " + yourAttack);
                    System.out.println("Ваш враг:\nHit Points: " + enemyHp +
                            "\nDefence: " + enemyDefence +
                            "\nAttack: " + enemyAttack);

                    if (yourDefence - enemyAttack + yourAttack - enemyDefence + yourHp > enemyHp) {
                        System.out.println("\n\n----------------\n\nВы победили! Игра окончена!");
                        break;
                    }
                    else {
                        if (hero.isMainHero()) {
                            System.out.println("\n\n----------------\n\nВаш главный герой умер! " +
                                    "Вы проиграли. Игра окончена!");
                            break;
                        }
                        else {
                            ArrayList<Hero> newHeroes = player.getHeroes();
                            newHeroes.remove(hero);
                            player.setHeroes(newHeroes);
                            System.out.println("Герой " + hero.getName() + " умирает. Вы можете продолжать игру " +
                                    "до тех пор, пока не умрёт главный герой.");
                        }
                    }

                }
                else if (choice == 5) { // Для прохождения через врата todo Gates
                    continue;
                }
                else if (choice == 6) { // Для взаимодействия с Обелиском todo Obelisc and Grail
                    continue;
                }
                else if (choice == 7) { // Для выкапывания Грааля todo Grail
                    continue;
                }
                else {
                    System.out.println("Что-то пошло не так. Попробуйте ввести значение заново.");
                }
            }
            else if (choice == 2) {
                startANewDay(player);
            }
            else {
                System.out.println("Что-то пошло не так. Попробуйте ввести значение заново.");
            }
        }
    }

    private static void getTreasure(Player player) {
        System.out.println("Ваш герой нашёл сокровище.");
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        int value = random.nextInt(1000) + 1000;
        System.out.println("В сокровище лежало " + value + " золота. Это золото ваше!");
        player.setResources(updateResources(player, "Золото", value));
        // field.removeTreasure(hero);
    }

    private static void interactWithCastle(Scanner scanner, Field field, Hero hero, Player player) {
        int choice;
        CastleCeil castleCeil = (CastleCeil) field.getData()
                [hero.getCoordinates().getX()][hero.getCoordinates().getY()];
        Castle castle = castleCeil.getCastle();

        System.out.println("Выберите действие внутри замка:\n" +
                "1 - Купить новое здание\n" +
                "2 - Взаимодействовать с возведёнными зданиями\n" +
                ((hero.isHaveGrail()) ? "3 - Отдать Грааль" : ""));
        choice = scanner.nextInt();
        if (choice == 1) {
            buyNewBuilding(scanner, castle, player);
        }
        else if (choice == 2) {
            System.out.println("Список имеющихся зданий: ");
            List<CastleBuildings> buildings = castle.getBuildings();
            boolean flag = false;
            for (int i = 0; i < buildings.size(); i++) {
                if (buildings.get(i).isBought()) {
                    flag = true;
                    System.out.println(i + " - " + buildings.get(i).getName());
                }
            }
            if (!flag) {
                System.out.println("У вас нет зданий в этом замке.");
            }
            else {
                System.out.print("Введите номер здания для взаимодействия: ");
                int chosenBuilding = scanner.nextInt();
                if (chosenBuilding < buildings.size() && chosenBuilding > -1) {
                    System.out.println("Вы выбрали здание " + buildings.get(chosenBuilding).getName());
                    if (chosenBuilding == 1) {
                        upgradeSpeed(scanner, player, hero);
                    }
                    else {
                        interactWithUsualBuilding(scanner, buildings, chosenBuilding, player, hero);
                    }
                }
                else {
                    System.out.println("Вы ошиблись номером. Попробуйте ещё раз.");
                }
            }
        }
        else if (hero.isHaveGrail() && choice == 3) { // todo Grail
            return;
        }
        else {
            System.out.println("Что-то пошло не так. Попробуйте ввести значение заново.");
        }
    }

    private static void interactWithUsualBuilding(Scanner scanner, List<CastleBuildings> buildings, int chosenBuilding, Player player, Hero hero) {
        System.out.println("В этом здании вы можете купить:");
        Warrior chosenWarrior = buildings.get(chosenBuilding).getWarriorsAvailableToBuy();
        System.out.println(chosenWarrior.getTitle() + " (Цена: " + chosenWarrior.getCost() + ")");
        System.out.println("Хотите ли приобрести Юнита? (Максимальное количество: " +
                (player.getResources().get("Золото") / chosenWarrior.getCost()) + ")");
        System.out.print("Введите желаемое количество или 0: ");
        int count = scanner.nextInt();
        if (count == 0) {
            return;
        } else {
            if (count <= player.getResources().get("Золото") / chosenWarrior.getCost()) {
                if (chosenWarrior instanceof Hero) {
                    buyHero(scanner, count, player);
                } else {
                    buyUnit(buildings, chosenBuilding, hero, count, player);
                }
            } else {
                System.out.println("У вас недостаточно средств на покупку. " +
                        "Попробуйте в другой раз");
            }
        }
    }

    private static void upgradeSpeed(Scanner scanner, Player player, Hero hero) {
        System.out.println("В конюшне вы можете увеличить скорость всех юнитов. (+1) (Цена: 1000)");
        System.out.println("Приобрести улучшение? y/n (д/н)");
        String respond = scanner.nextLine();
        if (getAnswer(respond) && player.getResources().get("Золото") >= 1000) {
            ArrayList<Hero> heroes = player.getHeroes();
            for (int i = 0; i < heroes.size(); i++) {
                Set<Warrior> set = hero.getArmy().keySet();
                Iterator<Warrior> it = set.iterator();
                for (int j = 0; j < set.size(); j++) {
                    Warrior war = it.next();
                    war.setSpeed(war.getSpeed() + 1);
                }
            }
            player.setResources(updateResources(player, "Золото", -1000));
            System.out.println("Скорость всех юнитов увеличена на 1.");
        }
    }

    private static void buyUnit(List<CastleBuildings> buildings, int chosenBuilding, Hero hero, int count, Player player) {
        Warrior chosenWarrior = getWarriorByBuilding(buildings, chosenBuilding, hero);
        Map<Warrior, Integer> newArmy = hero.getArmy();
        newArmy.replace(chosenWarrior, newArmy.get(chosenWarrior) + count);
        player.setResources(updateResources(player, "Золото", -1 * count * chosenWarrior.getCost()));
        System.out.println("Вы купили " + count + " юнитов " + chosenWarrior.getTitle());
    }

    public static Warrior getWarriorByBuilding(List<CastleBuildings> buildings, int chosenBuilding, Hero hero) {
        String chosenWarriorTitle = buildings.get(chosenBuilding).getWarriorsAvailableToBuy().getTitle();
        Map<Warrior, Integer> newArmy = hero.getArmy();
        Warrior chosenWarrior = new Pikeman();
        for (Map.Entry<Warrior, Integer> entry : newArmy.entrySet()) {
            Warrior key = entry.getKey();
            String title = key.getTitle();
            if (title.equals(chosenWarriorTitle)) {
                chosenWarrior = key;
            }
        }
        return chosenWarrior;
    }

    private static void buyHero(Scanner scanner, int count, Player player) {
        for (int i = 0; i < count; i++) {
            System.out.println("Введите имя героя: ");
            scanner.nextLine();
            String newName = scanner.nextLine();
            Hero newHero = new Hero(newName);
            newHero.setArmy(createNewArmy());
            player.addHero(newHero);
            System.out.println("Вы купили героя!");
        }
    }

    private static Map<Warrior, Integer> createNewArmy() {
        Map<Warrior, Integer> newArmy = new HashMap<>();
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        newArmy.put(new Pikeman(), random.nextInt(10));
        newArmy.put(new Archer(), random.nextInt(20) / 2);
        newArmy.put(new Swordsman(), 0);
        newArmy.put(new Cavalier(), 0);
        newArmy.put(new Champion(), 0);
        return newArmy;
    }

    private static void buyNewBuilding(Scanner scanner, Castle castle, Player player) { // todo Вернуться
        System.out.println("Список зданий, доступных для покупки: ");
        List<CastleBuildings> buildings = castle.getBuildings();
        boolean flag = false;
        for (int i = 0; i < buildings.size(); i++) {
            if (!buildings.get(i).isBought()) {
                flag = true;
                System.out.println(i + " - " + buildings.get(i).getName() +
                        " (цена: " + buildings.get(i).getCost() + ")");
            }
        }
        if (!flag) {
            System.out.println("У вас нет зданий, доступных для покупки, в этом замке.");
        }
        else {
            System.out.println("Введите номер здания, которое хотите купить:");
            int number = scanner.nextInt();
            if (number > -1 && number < buildings.size()) {
                if (!buildings.get(number).isBought()) {
                    if (checkGold(player, buildings, number)) {
                        playerBoughtNewBuilding(player, buildings, number);
                    }
                    else {
                        System.out.println("У Вас не хватило средств на покупку выбранного здания.");
                    }
                }
                else {
                    System.out.println("Здание уже куплено.");
                }
            }
        }
    }

    private static void playerBoughtNewBuilding(Player player, List<CastleBuildings> buildings, int number) {
        player.setResources(updateResources(player, "Золото", -1000));
        buildings.get(number).setBought(true);
        System.out.println("Вы купили здание " + buildings.get(number).getName() + ".");
        System.out.println("У вас осталось " + player.getResources().get("Золото"));
    }

    private static boolean checkGold(Player player, List<CastleBuildings> buildings, int number) {
        return player.getResources().get("Золото") > buildings.get(number).getCost();
    }

    private static void moveHero(Scanner scanner, Field field, Hero hero) {
        field.draw();
        Point result = getGetCoordinatesToMove(scanner);

        if (!checkCoordinates(result.getX(), result.getY(), field)) {
            legalHeroMoving(field, hero, result);
        }
        else {
            System.out.println("Не удалось переместить героя: некорректные координаты. Попробуйте ещё раз.");
        }
    }

    private static void legalHeroMoving(Field field, Hero hero, Point result) {
        ArrayList<Point> path = field.getPath(hero.getCoordinates().getX(), hero.getCoordinates().getY(),
                result.getX(), result.getY());
        hero.go(path, field);
        System.out.println("Текущие координаты героя: " + hero.getCoordinates().getX() + " "
                + hero.getCoordinates().getY());
        field.draw();
    }

    private static Point getGetCoordinatesToMove(Scanner scanner) {
        System.out.print("Введите координаты: ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new Point(x, y);
    }

    protected static boolean checkCoordinates(int x, int y, Field field) {
        return (((x < 0) || (x > field.getSize()[1])) || ((y < 0) || (y > field.getSize()[0])));
    }

    private static void startANewDay(Player player) {
        System.out.println("\n\nДень " + player.getDay() + " закончился.");
        player.setDay(player.getDay() + 1);

        System.out.println("За ночь ваш замок принёс 1000 золота");
        player.setResources(updateResources(player, "Золото", 1000));

        System.out.println("Очки действия " +
                ((player.getHeroes().size() == 1) ? "героя" : "всех героев") +
                " восстановлены.");
        updateMovementPointsForEachHero(player);

        System.out.println("День " + player.getDay() + " начался!\n\n");
    }

    private static void updateMovementPointsForEachHero(Player player) {
        ArrayList<Hero> heroes = player.getHeroes();
        for (int i = 0; i < heroes.size(); i++) {
            heroes.get(i).updateMovementPoints();
        }
    }

    private static Map<String, Integer> updateResources(Player player, String title, int count) {
        Map<String, Integer> newResources = player.getResources();
        if (checkTitle(newResources, title)) {
            newResources.replace(title, newResources.get(title) + count);
        }
        else {
            System.out.println("Не удалось обновить ресурсы. Попробуйте ввести правильное название");
        }
        return newResources;
    }

    private static boolean checkTitle(Map<String, Integer> newResources, String title) {
        if (newResources.containsKey(title)) {
            return true;
        }
        return false;
    }

}