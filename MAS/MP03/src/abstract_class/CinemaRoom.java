package abstract_class;

public class CinemaRoom extends ObjectPlus {

    private boolean supportsTwoD;
    private boolean supportsTreeD;

    public CinemaRoom(boolean supportsTwoD, boolean supportsTreeD) {
        this.supportsTwoD = supportsTwoD;
        this.supportsTreeD = supportsTreeD;
    }

    public boolean isSupportsTwoD() {

        return supportsTwoD;
    }

    public void setSupportsTwoD(boolean supportsTwoD) {
        this.supportsTwoD = supportsTwoD;
    }

    public boolean isSupportsTreeD() {
        return supportsTreeD;
    }

    public void setSupportsTreeD(boolean supportsTreeD) {
        this.supportsTreeD = supportsTreeD;
    }
}
