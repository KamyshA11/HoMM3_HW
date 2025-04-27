package field;

public class BoxCeil extends BaseCeil {
    public BoxCeil() {
        super();
        consoleSymbol = "\u001B[45;95m" + " B ";
        canThrough = false;
    }
}