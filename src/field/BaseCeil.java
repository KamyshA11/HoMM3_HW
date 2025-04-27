package field;

import warriors.Hero;
import warriors.Warrior;

import javax.lang.model.type.NullType;

public class BaseCeil {
    protected String consoleSymbol = "?";
    Hero object = null;
    boolean hasHero = false;
    boolean hasEnemy = false;
    protected boolean canThrough = true;
    int cost = 100;
    double penalty = 1;

    public void draw(){
        System.out.print(consoleSymbol);
    }

    public Hero getHero() {
        return object;
    }

    public void setHero(Hero hero) {
        this.object = hero;
    }

    public boolean isHasHero() {
        return hasHero;
    }

    public void setHasHero(boolean hasObject) {
        this.hasHero = hasObject;
    }

    public double getCost() {
        return (cost*penalty);
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isCanThrough() {
        return canThrough;
    }

    public void setCanThrough(boolean canThrough) {
        this.canThrough = canThrough;
    }

    public boolean isHasEnemy() {
        return hasEnemy;
    }

    public void setHasEnemy(boolean hasEnemy) {
        this.hasEnemy = hasEnemy;
    }
}
