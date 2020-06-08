package taberth;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import sun.text.normalizer.UBiDiProps;

public class Mainprog {
    
    public static void main(String[] args) throws IOException, CloneNotSupportedException  {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 1; j++) {
            ArrayList<String[]> arrship = new ArrayList<>();
            ArrayList<Ship> listship = new ArrayList<>();

            File[] files = new File("src/instance/").listFiles();

            String filename = files[i].getName().substring(0, files[i].getName().length() - 4);
            String filepath = files[i].getPath();
            
            ReadFile read = new ReadFile(filepath, arrship, listship);
            InitSolution init = new InitSolution(listship);

            Heuristic heur = new Heuristic(init.initialsol);
            System.out.println("cost initial "+Util.cost(heur.initsol));
//        int popSize =10;        
//        double crossOveRate = 0.95;
//        double mutationRate = 0.95;                                          
//        double elitism = 8;	        
//        int maxiteration = 100;
            heur.GA(20, 0.95, 0.1, 2000);
            Util.export(heur.ga_sol, filename,j);
            Util.exportstat(heur.initsol, heur.ga_sol, heur.ga_sol, filename,j,heur.startimer,heur.endtimer,heur.besttimer);
            System.out.println(filename + " run ke "+j);
                System.out.println("");
            }
        }
    }
    
}
