package objects;

import warriors.Hero;
import warriors.Swordsman;

public class Armory extends CastleBuildings {
    public Armory() {
        name = "Оружейная";
        cost = 5000;
        this.warriorsAvailableToBuy = new Swordsman();
    }
}
