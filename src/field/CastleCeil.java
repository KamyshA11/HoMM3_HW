package field;

import objects.Castle;

public class CastleCeil extends BaseCeil {
    Castle castle;

    public CastleCeil(String loyalty) {
        super();
        consoleSymbol = "\u001B[34;107m" + " C ";
        castle = new Castle(loyalty);
    }

    public Castle getCastle() {
        return castle;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }
}