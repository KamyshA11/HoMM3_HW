package warriors;

public class Archer extends Warrior {
    public Archer() {
        title = "Арбалетчик";
        cost = 100;
        speed = 3;
        attack = 6;
        defence = 3;
        hp = 10;
        damage = new int[]{2, 3};
    }
}
