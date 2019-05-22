package multi_inheritance;

public class Printer extends Device {

    private int speedOfPrinting;
    private boolean printingColor;

    public Printer(String deviceName, String deviceSerialNumber, String deviceModel, String deviceProducer, int speedOfPrinting, boolean printingColor) {
        super(deviceName, deviceSerialNumber, deviceModel, deviceProducer);
        this.speedOfPrinting = speedOfPrinting;
        this.printingColor = printingColor;
    }

    public int getSpeedOfPrinting() {
        return speedOfPrinting;
    }

    public boolean isPrintingColor() {
        return printingColor;
    }
}
