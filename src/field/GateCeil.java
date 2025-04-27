package field;

public class GateCeil extends BaseCeil {
    public GateCeil() {
        super();
        consoleSymbol = "\u001B[40;93m" + " G ";
        canThrough = false;
    }
}