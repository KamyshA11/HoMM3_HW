import field.NumbericField;
import warriors.Hero;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Player {
    int day = 1; // todo Решить, где оставить день: здесь или в main
    ArrayList<Hero> heroes = new ArrayList<Hero>();
    Map<String, Integer> resources = new HashMap<String,Integer>();

    public Player() {
        resources.put("Золото", 25000);
        resources.put("Дерево", 10000);
        resources.put("Руда", 10000);
        resources.put("Сокровище/Драгоценный камень", 0);
        resources.put("Ртуть", 0);
        resources.put("Сера", 0);
        resources.put("Кристалл", 0);
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    public void addHero(Hero hero) {
        this.heroes.add(hero);
    }

    public boolean isMainHeroDead() {
        for (Hero hero: this.heroes) {
            if (hero.isMainHero() && hero.getHp() == 0) {
                return true;
            }
        }
        return false;
    }

    public Map<String, Integer> getResources() {
        return resources;
    }

    public void setResources(Map<String, Integer> resources) {
        this.resources = resources;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
