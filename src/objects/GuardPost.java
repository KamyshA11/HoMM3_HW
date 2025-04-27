package objects;

import warriors.Hero;
import warriors.Pikeman;

public class GuardPost extends CastleBuildings {
    public GuardPost() {
        name = "Сторожевой пост";
        cost = 1000;
        this.warriorsAvailableToBuy = new Pikeman();
    }
}
