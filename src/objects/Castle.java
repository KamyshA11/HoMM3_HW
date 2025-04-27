package objects;

import java.util.ArrayList;
import java.util.List;

public class Castle {
    String loyalty;
    List<CastleBuildings> buildings = new ArrayList<CastleBuildings>();

    public Castle(String loyalty) {
        this.loyalty = loyalty;
        this.buildings.add(new Tavern());
        this.buildings.getFirst().setBought(true);
        this.buildings.add(new Stable());
        this.buildings.add(new ArchersTower());
        this.buildings.add(new GuardPost());
        this.buildings.get(3).setBought(true);
        this.buildings.add(new Armory());
        this.buildings.add(new Arena());
        this.buildings.add(new Cathedral());
    }

    public List<CastleBuildings> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<CastleBuildings> buildings) {
        this.buildings = buildings;
    }

    public String getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(String loyalty) {
        this.loyalty = loyalty;
    }
}
