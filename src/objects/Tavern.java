package objects;

import warriors.Hero;
import warriors.Warrior;


public class Tavern extends CastleBuildings {
    public Tavern() {
        name = "Таверна";
        cost = 0;
        this.warriorsAvailableToBuy = new Hero("");
    }
}
