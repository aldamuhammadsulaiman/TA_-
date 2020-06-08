package taberth;

import java.util.ArrayList;
public class Ship implements Cloneable{    
    private int shipId;   
    private double arrival;
    private double desDepart;
    private int manouvTime;
    private int costWait;
    private int costDelay;    
    private int [] processTimes = new int[11];    
    private int ti;
    private int ri;
    private int hi;    
    private int berth;        

    public Ship(int shipId) {
        this.shipId = shipId;
    }
    
    public Ship(Ship ship){
        this.shipId=ship.shipId;       
        this.arrival=ship.arrival;
        this.desDepart=ship.desDepart;
        this.manouvTime=ship.manouvTime;
        this.costWait=ship.costWait;
        this.costDelay=ship.costDelay;
        this.processTimes = ship.processTimes;
        this.ti=ship.ti;
        this.ri=ship.ri;
        this.hi=ship.hi;
        this.berth=ship.berth;        
    }
    
    public Object clone()throws CloneNotSupportedException{  
        return super.clone();  
    }  
    public int getShipId() {
        return shipId;
    }
    public void setShipId(int shipId) {
        this.shipId = shipId;
    }  
    public double getArrival() {
        return arrival;
    }
    public void setArrival(double Arrival) {
        this.arrival = Arrival;
    }
    public double getDesDepart() {
        return desDepart;
    }
    public void setDesDepart(double desDepart) {
        this.desDepart = desDepart;
    }
    public int getManouvTime() {
        return manouvTime;
    }
    public void setManouvTime(int manouvTime) {
        this.manouvTime = manouvTime;
    }
    public int getCostWait() {
        return costWait;
    }
    public void setCostWait(int costWait) {
        this.costWait = costWait;
    }

    public int getCostDelay() {
        return costDelay;
    }

    public void setCostDelay(int costDelay) {
        this.costDelay = costDelay;
    }
    
    public int[] getProcessTimes() {
        return processTimes;
    }

    public void setProcessTimes(int[] processTimes) {
        this.processTimes = processTimes;
    }

    public int getTi() {
        return ti;
    }

    public void setTi(int ti) {
        this.ti = ti;
    }

    public int getRi() {
        return ri;
    }

    public void setRi(int ri) {
        this.ri = ri;
    }

    public int getHi() {
        return hi;
    }

    public void setHi(int hi) {
        this.hi = hi;
    }

    public int getBerth() {
        return berth;
    }

    public void setBerth(int berth) {
        this.berth = berth;
    }

            
    
    
    
}