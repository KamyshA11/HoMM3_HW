package warriors;

public class Cavalier extends Warrior {
    public Cavalier() {
        title = "Кавалерист";
        cost = 1000;
        speed = 5;
        attack = 1;
        defence = 15;
        hp = 100;
        damage = new int[]{15, 25};
    }
}
