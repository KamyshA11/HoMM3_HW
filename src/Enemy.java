import field.Point;
import warriors.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Enemy {
    ArrayList<Hero> heroes = new ArrayList<Hero>();

    public Enemy() {
        Hero hero = new Hero("Враг");
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        Map<Warrior, Integer> newArmy = new HashMap<>();
        newArmy.put(new Pikeman(), random.nextInt(50));
        newArmy.put(new Archer(), random.nextInt(30) / 2);
        newArmy.put(new Swordsman(), random.nextInt(5));
        newArmy.put(new Cavalier(), random.nextInt(3));
        newArmy.put(new Champion(), random.nextInt(1));
        hero.setArmy(newArmy);
        hero.setLoyalty("Враг");
        hero.setCoordinates(new Point(14, 9));
        heroes.add(hero);
    }
}
