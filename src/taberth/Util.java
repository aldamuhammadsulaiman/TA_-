package taberth;


import java.io.FileWriter;
import java.util.ArrayList;
public class Util {
    public static boolean cekhc(ArrayList<Ship> listship){
        boolean cek0 = false;
        boolean cek1 = false;
        boolean cek2 = false;
        boolean cek3 = false;
        boolean cek4 = false;
        boolean valid = false;
        for (int i = 0; i < listship.size(); i++) {
            //constrain hi!=0
            if (listship.get(i).getHi()!=0) {
                cek0=true;
            }else{
                cek0=false;
                System.out.println(i+" Xconstrain 0");
                System.out.println("berth "+listship.get(i).getBerth());
                System.out.println("hi "+listship.get(i).getHi());
            }
            //constrain ti >= ai
            if(listship.get(i).getTi()>=(int)listship.get(i).getArrival()){
                cek1=true;
            }else{
                cek1=false;
                System.out.println(i+" Xconstrain 1");
            }
            //constrain ri>= ti+hi
            if(listship.get(i).getRi()>=listship.get(i).getTi()+listship.get(i).getHi()){
                cek2=true;
            }else{
                cek2=false;
                System.out.println(i+" Xconstrain 2");
            }
            //constrain kapal 2 bisa berth kalo kapal 1 udah depart (7)
//            if(conflictmatrix(listship)==true){
                cek3=true;
            //consntrain ti >= relk //?
            for(int ii = 0; ii < listship.size(); ii++) {
                for(int j = ii+1; j < listship.size(); j++) {
                    if (listship.get(ii).getBerth()==listship.get(j).getBerth()) {
    //                    System.out.println("ship "+listship.get(i).getShipId()+" di berth "+listship.get(i).getBerth()+" dan ship "+listship.get(j).getShipId()+" diberth "+listship.get(j).getBerth());
                        if(listship.get(j).getTi()>=listship.get(ii).getRi()){
                            cek4 =true;
                        }else{
                            cek4 =false;
                            System.out.println(i+" Xconstrain 4");
                        }
                    }
                }
            }
        }
        
        if(cek0&&cek1&&cek2&&cek3&&cek4){
            valid=true;
        }else
            valid=false;
        return valid;
    }    
 
    public static int cost(ArrayList<Ship> listship){
        int cost=0;
        for (int i = 0; i < listship.size(); i++) {
            cost = cost + listship.get(i).getHi();
        }
        return cost;
    }
    
    public static ArrayList<Ship> cloneList(ArrayList<Ship> sl) {
        ArrayList<Ship> clonedList = new ArrayList<Ship>(sl.size());
        for (Ship ship : sl) {
            clonedList.add(new Ship(ship));
        }
        return clonedList;
    }
    
    public static void export(ArrayList<Ship> listfinal, String filename, int run){
        try{    
            FileWriter fw=new FileWriter("D:\\hasil\\"+filename+"_solution_"+run+".txt"); 
            fw.write("Ship"+"\t"+"Berth"+"\t"+"Start"+"\t"+"End"+"\t"+"Cost"); 
            fw.write("\n"); 
            for (int i = 0; i <listfinal.size(); i++) {
                fw.write(listfinal.get(i).getShipId()+"\t\t"+listfinal.get(i).getBerth()+"\t\t"+(int)listfinal.get(i).getArrival()+"\t\t"+listfinal.get(i).getRi()+"\t\t"+listfinal.get(i).getHi());
                fw.write("\n"); 
            }
            fw.write("cost : "+Util.cost(listfinal)); 
             
            fw.close();    
        } catch(Exception e){
        	System.out.println(e);
        }    
            System.out.println("File "+filename+".txt berhasil disimpan di D");    
    }
    public static void exportstat(ArrayList<Ship> initial, ArrayList<Ship> hc, ArrayList<Ship> ils, String filename, int run, long start, long end, long best){
        try{    
            FileWriter fw=new FileWriter("D:\\hasil\\stat.txt", true); 
            
            fw.write(filename+"-"+run+";"+Util.cost(initial)+";"+Util.cost(hc)+";"+Util.cost(ils)+";"+start+";"+end+";"+best);
            fw.write("\n");
            fw.close();    
        } catch(Exception e){
        	System.out.println(e);
        }    
            System.out.println("File berhasil disimpan di D");    
    }
    
    
}
