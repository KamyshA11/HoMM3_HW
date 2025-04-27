package warriors;

import field.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hero extends Warrior {
    Map<Warrior, Integer> army = new HashMap<Warrior, Integer>();
    Point coordinates = new Point(0, 0);
    int movementPoints;
    String name;
    boolean mainHero = false;
    boolean haveGrail = false;
    Map<String, Integer> skills = new HashMap<String, Integer>();
    protected String consoleSymbol = "\u001B[40;97m" + " H ";
    String loyalty = "Игрок";

    public Hero(String heroName) {
        hp = 32;
        attack = 14;
        defence = 4;
        damage[0] = 4;
        damage[1] = 8;
        name = heroName;
        title = "Герой";
        cost = 2000;
    }

    public Map<Warrior, Integer> getArmy() {
        return army;
    }

    public void setArmy(Map<Warrior, Integer> army) {
        this.army = army;
    }

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Integer> skills) {
        this.skills = skills;
    }

    public String getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(String loyalty) {
        this.loyalty = loyalty;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public int getMovementPoints() {
        return movementPoints;
    }

    public void setMovementPoints(int movementPoints) {
        this.movementPoints = movementPoints;
    }

    public void updateMovementPoints() {
        if (this.getArmy().isEmpty()) {
            movementPoints = 1500;
        }
        else {
            Map<Integer, Integer> speed = new HashMap<Integer, Integer>();
            speed.put(3, 1500);
            speed.put(4, 1560);
            speed.put(5, 1630);
            speed.put(6, 1700);
            speed.put(7, 1760);
            speed.put(8, 1830);
            speed.put(9, 1900);
            speed.put(10, 1960);
            int minSpeed = 100;
            for (Map.Entry<Warrior, Integer> entry : army.entrySet()) {
                Warrior key = entry.getKey();
                Integer value = entry.getValue();
                if (value > 0) {
                    if (key.getSpeed() < minSpeed) {
                        minSpeed = key.getSpeed();
                    }
                }
            }
            if (minSpeed < 10) {
                movementPoints = speed.get(minSpeed);
            }
            else {
                movementPoints = 2000;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConsoleSymbol() {
        return consoleSymbol;
    }

    public void setConsoleSymbol(String consoleSymbol) {
        this.consoleSymbol = consoleSymbol;
    }

    public boolean isMainHero() {
        return mainHero;
    }

    public boolean hasArmy() {
        return (!this.army.isEmpty());
    }

    public void setMainHero(boolean mainHero) {
        this.mainHero = mainHero;
    }

    public void go(ArrayList<Point> path, Field field) {
        BaseCeil[][] dataField = field.getData();
        for (Point point : path.reversed()) {
            if (isAvailableToMove(this.movementPoints, point, field)) {
                makeStep(field, point, dataField);
            } else {
                System.out.println("Герой не смог дойти до назначенной точки.");
                return;
            }
        }
    }

    private void makeStep(Field field, Point point, BaseCeil[][] dataField) {
        dataField[point.getY()][point.getX()].setHasHero(true);
        dataField[point.getY()][point.getX()].setHero(this);
        dataField[coordinates.getY()][coordinates.getX()].setHasHero(false);
        dataField[coordinates.getY()][coordinates.getX()].setHero(null);
        field.setData(dataField);
        this.setCoordinates(point);
        this.movementPoints -= (int) Math.ceil(dataField[point.getY()][point.getX()].getCost());
    }

    private boolean isAvailableToMove(int movementPoints, Point point, Field field) {
        BaseCeil[][] dataField = field.getData();
        int costOfMove = (int) Math.ceil(dataField[point.getY()][point.getX()].getCost());
        return (movementPoints >= costOfMove);
    }

    public void draw(Field field) {
        BaseCeil[][] dataField = field.getData();
        boolean flag = false;
        for (int i = 0; i < field.getSize()[0] && !flag; i++) {
            for (int j = 0; j < field.getSize()[1]; j++) {
                if (dataField[i][j] instanceof CastleCeil) {
                    this.setCoordinates(new Point(i, j));
                    flag = true;
                }
            }
        }
        dataField[coordinates.getY()][coordinates.getX()].setHero(this);
        dataField[coordinates.getY()][coordinates.getX()].setHasHero(true);
    }

    public boolean canInteractWithCastle(Field field) {
        return (field.getData()[coordinates.getY()][coordinates.getX()] instanceof CastleCeil);
    }

    public boolean canInteractWithTreasure(Field field) {
        BaseCeil[][] dataField = field.getData();
        int x = coordinates.getX();
        int y = coordinates.getY();
        if ((x - 1 > -1) && (dataField[y][x - 1] instanceof BoxCeil)) {
            return true;
        }
        if ((y - 1 > -1) && (dataField[y - 1][x] instanceof BoxCeil)) {
            return true;
        }
        if ((x + 1 < field.getSize()[1]) && (dataField[y][x + 1] instanceof BoxCeil)) {
            return true;
        }
        if ((y + 1 < field.getSize()[0]) && (dataField[y + 1][x] instanceof BoxCeil)) {
            return true;
        }
        return false;
    }

    public boolean canInteractWithEnemy(Field field) {
        BaseCeil[][] dataField = field.getData();
        int x = coordinates.getX();
        int y = coordinates.getY();
        if ((x - 1 > -1) && (dataField[y][x - 1].isHasEnemy())) {
            return true;
        }
        if ((y - 1 > -1) && (dataField[y - 1][x].isHasEnemy())) {
            return true;
        }
        if ((x + 1 < field.getSize()[1]) && (dataField[y][x + 1].isHasEnemy())) {
            return true;
        }
        if ((y + 1 < field.getSize()[0]) && (dataField[y + 1][x].isHasEnemy())) {
            return true;
        }
        return false;
    }

    public boolean isHaveGrail() {
        return haveGrail;
    }

    public void showArmy() {
        for (Map.Entry<Warrior, Integer> entry : army.entrySet()) {
            Warrior key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key.getTitle() + ": " + value);
        }
    }

    public void show() {
        System.out.println("Имя героя: " + this.getName());
        System.out.println("Армия героя: ");
        this.showArmy();
        System.out.println("Очки движения: " + this.getMovementPoints());
        System.out.println("Координаты: " + this.getCoordinates().getX() + " " + this.getCoordinates().getY());
    }
}
