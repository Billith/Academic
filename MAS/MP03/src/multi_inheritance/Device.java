package multi_inheritance;

public abstract class Device {

    private String deviceName;
    private String deviceSerialNumber;
    private String deviceModel;
    private String deviceProducer;

    public Device(String deviceName, String deviceSerialNumber, String deviceModel, String deviceProducer) {
        this.deviceName = deviceName;
        this.deviceSerialNumber = deviceSerialNumber;
        this.deviceModel = deviceModel;
        this.deviceProducer = deviceProducer;
    }

}
