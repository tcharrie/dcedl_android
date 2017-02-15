package tests;
import java.util.*;
import solveurs.*;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();
	//	ArrayList<Integer> x = new ArrayList<>(Arrays.asList(100,75,50,25,10,10));
		//ArrayList<String> y = new ArrayList<>(Arrays.asList("100","75","50","25","10","10"));
		ArrayList<Integer> x = new ArrayList<>(Arrays.asList(6,5,3,2,1,1));
		ArrayList<String> y = new ArrayList<>(Arrays.asList("6","5","3","2","1","1"));
		///ArrayList<Integer> x = new ArrayList<>(Arrays.asList(75,9,4,3,3,1));
		//ArrayList<String> y = new ArrayList<>(Arrays.asList("75","9","4","3","3","1"));
		Solveur_chiffres s = new Solveur_chiffres();
		System.out.println(s.solver_avec_meilleure_approche(x,y,6,999,1000));
		//System.out.println(s.solver_that_stops_when_resolved(x,y,6,480));
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(totalTime/1000000);
		}

}
