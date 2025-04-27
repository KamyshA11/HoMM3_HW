package warriors;

public class Champion extends Warrior {
    public Champion() {
        title = "Паладин";
        cost = 1200;
        speed = 6;
        attack = 16;
        defence = 16;
        hp = 100;
        damage = new int[]{20, 25};
    }
}
