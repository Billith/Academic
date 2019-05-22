package multi_inheritance;

public class Scanner extends Device implements IScanner {

    private String maxScanResolution;
    private boolean scanningDuplex;

    public Scanner(String deviceName, String deviceSerialNumber, String deviceModel, String deviceProducer, String maxScanResolution, boolean scanningDuplex) {
        super(deviceName, deviceSerialNumber, deviceModel, deviceProducer);
        this.maxScanResolution = maxScanResolution;
        this.scanningDuplex = scanningDuplex;
    }


    @Override
    public String getMaxScanResolution() {
        return this.maxScanResolution;
    }

    @Override
    public boolean isScanningDuplex() {
        return this.scanningDuplex;
    }
}
