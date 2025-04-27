package objects;

import warriors.Cavalier;
import warriors.Hero;

public class Arena extends CastleBuildings {
    public Arena() {
        name = "Арена";
        cost = 4000;
        this.warriorsAvailableToBuy = new Cavalier();
    }
}
