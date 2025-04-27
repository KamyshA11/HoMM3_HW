package warriors;

public class Swordsman extends Warrior {
    public Swordsman() {
        title = "Мечник";
        cost = 300;
        speed = 4;
        attack = 10;
        defence = 12;
        hp = 35;
        damage = new int[]{6, 9};
    }
}
