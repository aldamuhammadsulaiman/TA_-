package taberth;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Heuristic {
    ArrayList<Ship> initsol;
    ArrayList<Ship> ga_sol; 
    long besttimer =0;
    long startimer=0;
    long endtimer=0;
    Random rn = new Random();
    long starttimer = System.nanoTime(); 
    
    public Heuristic(ArrayList<Ship> initsol){
        this.initsol=initsol;
    }                
   
    public void GA(int popSize,double crossOveRate, double mutationRate, int maxiteration ){                                    
        double penaltyLocal=0;
        double penaltyHC=0;
        ArrayList<Ship> sbest = Util.cloneList(initsol);        
        ArrayList<Ship> [] populasi= new ArrayList[popSize];
        for (int i = 0; i < popSize; i++) {
            populasi[i]=new ArrayList<>(initsol);
        }                          
                
        double penalty1 = Util.cost(sbest);
        double[] penalty2 = new double[popSize];
       
           for (int i = 0; i < maxiteration; i++) {
            int numb=rn.nextInt(9); 
                if (mutationRate > Math.random()){
                    switch(numb){
                        case(0):
                            do {
                                shift(populasi[0]);
                            } while (!Util.cekhc(populasi[0]));
                            break;

                        case(1):
                            do {
                                shift(populasi[1]);
                            } while (!Util.cekhc(populasi[1]));
                            break;

                        case(2):
                            do {
                                shift(populasi[2]);
                            } while (!Util.cekhc(populasi[2]));
                            break;

                        case(3):
                            do {
                                shift(populasi[3]);
                            } while (!Util.cekhc(populasi[3]));
                            break;

                        case(4):
                            do {
                                shift(populasi[4]);
                            } while (!Util.cekhc(populasi[4]));
                            break;

                        case(5):
                            do {
                                shift(populasi[5]);
                            } while (!Util.cekhc(populasi[5]));
                            break;

                        case(6):
                            do {
                                shift(populasi[6]);
                            } while (!Util.cekhc(populasi[6]));
                            break;

                        case(7):
                            do {
                                shift(populasi[7]);
                            } while (!Util.cekhc(populasi[7]));
                            break;	

                        case(8):
                            do {
                                shift(populasi[8]);
                            } while (!Util.cekhc(populasi[8]));
                            break;                           

                        case(9):
                            do {
                                shift(populasi[9]);
                            } while (!Util.cekhc(populasi[9]));
                            break;                     

                    }
                }
                if (crossOveRate > Math.random()){
                    switch(numb){                                                                  
                        case(0):
                        do {
                          swap(populasi[0]);
                        } while (!Util.cekhc(populasi[0]));
                        break;
                        case(1):
                        do {
                          swap(populasi[1]);
                        } while (!Util.cekhc(populasi[1]));
                        break;
                        case(2):
                        do {
                          swap(populasi[2]);
                        } while (!Util.cekhc(populasi[2]));
                        break;
                        case(3):
                        do {
                          swap(populasi[3]);
                        } while (!Util.cekhc(populasi[3]));
                        break;
                        case(4):
                        do {
                          swap(populasi[4]);
                        } while (!Util.cekhc(populasi[4]));
                        break;
                        case(5):
                        do {
                          swap(populasi[5]);
                        } while (!Util.cekhc(populasi[5]));
                        break;
                        case(6):
                        do {
                          swap(populasi[6]);
                        } while (!Util.cekhc(populasi[6]));
                        break;
                        case(7):
                        do {
                          swap(populasi[7]);
                        } while (!Util.cekhc(populasi[7]));
                        break;
                        case(8):
                        do {
                          swap(populasi[8]);
                        } while (!Util.cekhc(populasi[8]));
                        break;
                        case(9):
                        do {
                          swap(populasi[9]);
                        } while (!Util.cekhc(populasi[9]));
                        break;
                    }
                }
                for (int j = 0; j < popSize; j++) {
                    penalty2[j]=Util.cost(populasi[j]);
                    if(penalty2[j] < penalty1){
                    penalty1 = penalty2[j];
                    sbest = Util.cloneList(populasi[j]);
                    }else{
                        populasi[j] = Util.cloneList(sbest);
                    }
                }
            } 
        
        
        //LOCAL SEARCH HILL CLIMBING
        ArrayList<Ship> locS = Util.cloneList(sbest);        
        for (int i = 0; i < maxiteration; i++) {
            localSearch(locS);
            penaltyLocal=Util.cost(locS);
            if (penaltyLocal < penalty1) {
                penalty1=penaltyLocal;
                sbest= Util.cloneList(locS);
            }else{
                locS=Util.cloneList(sbest);
            }
        }
        
        
        ArrayList<Ship> hc = Util.cloneList(sbest);
        for (int i = 0; i < maxiteration; i++) {
            localSearch(hc);
        }
        penaltyHC=Util.cost(hc);
        if (penaltyHC < penalty1) {
            penalty1=penaltyHC;
            sbest= Util.cloneList(hc);
        }else{
            hc=Util.cloneList(sbest);
        }
        
        this.ga_sol=Util.cloneList(sbest);
        System.out.println(penalty1);
        endtimer   = System.nanoTime();
	long totaltimer = endtimer - starttimer;
	this.startimer=starttimer;
	this.endtimer = endtimer;
	this.besttimer=besttimer;
    }
     
    public void shift(ArrayList<Ship> ls){
        Random rn = new Random();
        int hi = 0;
        int angka = 0;
        int totalavailable=0;
        
        Ship pick1 = ls.get(rn.nextInt(ls.size()));
        do {
            for (int i = 0; i < pick1.getProcessTimes().length; i++) {
                if(pick1.getProcessTimes()[i]>0){
                    totalavailable++;
                }
            }
            if (totalavailable>1) {
                do {
                    angka = rn.nextInt(11);
                } while (angka==pick1.getBerth());
            }
            if (totalavailable==1) {
                angka = pick1.getBerth();
            }
            hi = pick1.getProcessTimes()[angka];
        } while (hi==0);
        pick1.setBerth(angka);
        countagain(ls);

    }
    
    public void countagain(ArrayList<Ship> listship){
        ArrayList<BerthTrans> listberth2 = new ArrayList<BerthTrans>();
        
        
        for (int i = 0; i < 11; i++) {
            BerthTrans berth2 = new BerthTrans(i);
            berth2.setReleasetime(0);
            berth2.setHandlingtime(0);
            listberth2.add(berth2);
        }       
        listship.sort(Comparator.comparing(Ship::getArrival));
        for (int i = 0; i < listship.size(); i++) {
            Ship thisship = listship.get(i); 
            for (int j = 0; j < listberth2.size(); j++) {
                listberth2.get(j).setHandlingtime(thisship.getProcessTimes()[j]);
            }
            for (int j = 0; j < listberth2.size(); j++) {
                if(thisship.getArrival()>=listberth2.get(j).getReleasetime())     
                    listberth2.get(j).setReleasetime(0);
            }
            BerthTrans pilih = listberth2.get(thisship.getBerth());
            
            int ti = 0;
            int ri = 0;
            
            if(pilih.getReleasetime()>0){
                thisship.setCostWait(pilih.getReleasetime()-(int)thisship.getArrival());
                ti=pilih.getReleasetime();
            }
            if(pilih.getReleasetime()==0){
                ti=(int)thisship.getArrival();
            }  

            thisship.setTi(ti);

            ri = ti + pilih.getHandlingtime();
            
            thisship.setHi(pilih.getHandlingtime());
            thisship.setRi(ri);
            //UPDATE RELEASE TIME DI AKIR
            listberth2.get(pilih.getId()).setReleasetime(ri);
            
    }
    }
    
    public void swap(ArrayList<Ship> listship){
        Random rn = new Random();
        int hi = 0;
        int angka1 = 0;
        int angka2 = 0;
        int berth1=0;
        int berth2=0;
        do {
            angka1 = rn.nextInt(listship.size()); //ngambil ship 1
            angka2 = rn.nextInt(listship.size()); //ngambil ship 2
            berth1 = listship.get(angka1).getProcessTimes()[listship.get(angka2).getBerth()];//hi ship 1 kalo ditaruh di tempatnya ship 2
            berth2 = listship.get(angka2).getProcessTimes()[listship.get(angka1).getBerth()];//hi ship 2 kalo ditaruh di tempatnya ship 1
        } 
        while (berth1==0||berth2==0);
        Ship pick1 = listship.get(angka2); //pick shipp yang akan diswap
        Ship pick2 = listship.get(angka1); //pick shipp yang akan diswap
        int berthtemp = 0;
        berthtemp = pick1.getBerth();
        pick1.setBerth(pick2.getBerth());
        pick2.setBerth(berthtemp);
        countagain(listship);
    }
    
    public void localSearch (ArrayList<Ship> daftarkapal){
        Random rn = new Random();
        int hi = 0;
        int angka = 0;
        int totalavailable=0;
        double rate = 0.001;
       
        Ship pick1 = daftarkapal.get(rn.nextInt(daftarkapal.size())); 
        do {
            for (int i = 0; i < pick1.getProcessTimes().length; i++) {
                if(pick1.getProcessTimes()[i]>0){
                    totalavailable++;
                }
            }
            if (totalavailable>1) {
                do {
                    angka = rn.nextInt(11);
                } while (angka==pick1.getBerth());
            }
            if (totalavailable==1) {
                angka = pick1.getBerth();
            }
            hi = pick1.getProcessTimes()[angka];
        } while (hi==0);
        pick1.setBerth(angka);
        countagain(daftarkapal);
        
        
        int angka1 = 0;
        int angka2 = 0;
        int berth1=0;
        int berth2=0;
        do {
            angka1 = rn.nextInt(daftarkapal.size()); //ngambil ship 1
            angka2 = rn.nextInt(daftarkapal.size()); //ngambil ship 2
            berth1 = daftarkapal.get(angka1).getProcessTimes()[daftarkapal.get(angka2).getBerth()];//hi ship 1 kalo ditaruh di tempatnya ship 2
            berth2 = daftarkapal.get(angka2).getProcessTimes()[daftarkapal.get(angka1).getBerth()];//hi ship 2 kalo ditaruh di tempatnya ship 1
        } while (berth1==0||berth2==0);
        Ship pick3 = daftarkapal.get(angka2); //pick shipp yang akan diswap
        Ship pick4 = daftarkapal.get(angka1); //pick shipp yang akan diswap
        int berthtemp = 0;
        berthtemp = pick1.getBerth();
        pick3.setBerth(pick4.getBerth());
        pick4.setBerth(berthtemp);
        //compute ulang ti ri hi
        countagain(daftarkapal);
    }   
}

    
    

