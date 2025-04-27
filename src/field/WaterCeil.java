package field;

public class WaterCeil extends BaseCeil {
    public WaterCeil() {
        super();
        consoleSymbol = "\u001B[44m" + "   ";
        canThrough = false;
    }
}