package warriors;

public class Pikeman extends Warrior {
    public Pikeman() {
        title = "Копейщик";
        cost = 60;
        speed = 3;
        attack = 4;
        defence = 5;
        hp = 10;
        damage = new int[]{1, 3};
    }
}
