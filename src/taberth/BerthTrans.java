package taberth;
public class BerthTrans {
    private int id;
    private int releasetime;
    private int handlingtime;

    public BerthTrans(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(int releasetime) {
        this.releasetime = releasetime;
    }

    public int getHandlingtime() {
        return handlingtime;
    }

    public void setHandlingtime(int handlingtime) {
        this.handlingtime = handlingtime;
    }
    
    
}
