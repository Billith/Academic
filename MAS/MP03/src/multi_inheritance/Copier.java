package multi_inheritance;

public class Copier extends Printer implements IScanner {

    private Scanner scanner;

    public Copier(String deviceName, String deviceSerialNumber, String deviceModel, String deviceProducer,
                  String maxScanResolution, boolean scanningDuplex, int speedOfPrinting, boolean printingColor) {
        super(deviceName, deviceSerialNumber, deviceModel, deviceProducer, speedOfPrinting, printingColor);
        this.scanner = new Scanner(null ,null, null, null, maxScanResolution, scanningDuplex);
    }

    @Override
    public String getMaxScanResolution() {
        return scanner.getMaxScanResolution();
    }

    @Override
    public boolean isScanningDuplex() {
        return scanner.isScanningDuplex();
    }
}
