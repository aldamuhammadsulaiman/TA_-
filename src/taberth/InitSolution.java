package taberth;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InitSolution {    
    ArrayList<Ship> listship;
    ArrayList<Ship> initialsol;
    public InitSolution(ArrayList<Ship> listship){
        this.listship=listship;
        
        ArrayList<BerthTrans> listbertha = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            BerthTrans bertha = new BerthTrans(i);
            bertha.setReleasetime(0);
            bertha.setHandlingtime(0);
            listbertha.add(bertha);
        }

        int[] berthi = new int[listship.size()];        
        int ti = 0;
        int ri = 0;
        int M = 0;
            
        //Sort ship by increasing arrival time
        listship.sort(Comparator.comparing(Ship::getArrival));
        
        //forloop
        for (int i = 0; i < listship.size(); i++) {
            
            //set handling time per ship
            Ship thisship = listship.get(i); //i
            berthi = thisship.getProcessTimes();
                        
            for (int j = 0; j < listbertha.size(); j++) {
                listbertha.get(j).setHandlingtime(berthi[j]);
            }
            for (int j = 0; j < listbertha.size(); j++) {
                if(thisship.getArrival()>=listbertha.get(j).getReleasetime())      //??????
                    listbertha.get(j).setReleasetime(0);
            }
            
            //sort and filter berth by release time and handling time on available berth
            List<BerthTrans> filterberth = listbertha.stream().filter(p -> p.getHandlingtime()> 0).collect(Collectors.toList());;
            filterberth.sort(Comparator.comparing(BerthTrans::getReleasetime).thenComparing(BerthTrans::getHandlingtime));
           
            //B = FIRST element in list after sort
            BerthTrans pilih = filterberth.get(0);
            
            //ti = max(relb, ai, M)
            ti = Math.max((int)thisship.getArrival(), Math.max(pilih.getReleasetime(),M));
            thisship.setTi(ti);
            //ri = ti + hi
            ri = ti + pilih.getHandlingtime();
            thisship.setHi(pilih.getHandlingtime());
            thisship.setRi(ri);
            
//            thisship.setRri(thisship.getRti()+thisship.getHi());    //wait
            thisship.setBerth(pilih.getId());
            
            M = ri;
//            System.out.println(i+". Mnow = " + M);

            //UPDATE RELEASE TIME DI AKIR
            listbertha.get(pilih.getId()).setReleasetime(ri);            
        }
        this.initialsol= new ArrayList<>(listship);
    }            
}
