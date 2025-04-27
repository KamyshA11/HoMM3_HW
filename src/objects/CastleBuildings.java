package objects;

import warriors.Warrior;

import java.util.ArrayList;
import java.util.List;

public abstract class CastleBuildings {
    boolean isBought = false;
    int levelOfBuilding = 1;
    Warrior warriorsAvailableToBuy;
    String name;
    int cost;

    public CastleBuildings() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevelOfBuilding() {
        return levelOfBuilding;
    }

    public void setLevelOfBuilding(int levelOfBuilding) {
        this.levelOfBuilding = levelOfBuilding;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public Warrior getWarriorsAvailableToBuy() {
        return warriorsAvailableToBuy;
    }

    public void setWarriorsAvailableToBuy(Warrior warriorsAvailableToBuy) {
        this.warriorsAvailableToBuy = warriorsAvailableToBuy;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
