package objects;

import warriors.Champion;
import warriors.Hero;

public class Cathedral extends CastleBuildings {
    public Cathedral() {
        name = "Собор";
        cost = 10000;
        this.warriorsAvailableToBuy = new Champion();
    }
}
