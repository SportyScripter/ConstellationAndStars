package SpaceObjects;
import java.io.Serializable;

public class RightAscension implements Serializable {
    private static final long serialVersionUID = 5162710183389028792L;
    private int xx;
    private int yy;
    private int zz;

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

    public int getZz() {
        return zz;
    }

    public void setZz(int zz) {
        this.zz = zz;
    }

    public RightAscension(int xx,int yy,int zz)
    {
        this.xx = xx;
        this.yy = yy;
        this.zz = zz;
    }

}
