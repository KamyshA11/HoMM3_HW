package field;

public class ObeliscCeil extends BaseCeil {
    public ObeliscCeil() {
        super();
        consoleSymbol = "\u001B[100;97m" + " O ";
        canThrough = false;
    }
}