package SpaceObjects;
import java.io.Serializable;

public class Declination implements Serializable {
    private static final long serialVersionUID = 6433858223774886977L;
    private int xx;
    private int yy;
    private double zz;

    public int getXx() {
        return xx;
    }

    public void setXx(int xx) {
        this.xx = xx;
    }

    public int getYy() {
        return yy;
    }

    public void setYy(int yy) {
        this.yy = yy;
    }

    public double getZz() {
        return zz;
    }

    public void setZz(double zz) {
        this.zz = zz;
    }

    public Declination(int xx, int yy, double zz) {
        this.xx = xx;
        this.yy = yy;
        this.zz = zz;
    }
}
