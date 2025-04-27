package objects;

import warriors.Archer;
import warriors.Hero;

public class ArchersTower extends CastleBuildings {
    public ArchersTower() {
        name = "Башня арбалетчиков";
        cost = 4000;
        this.warriorsAvailableToBuy = new Archer();
    }
}
