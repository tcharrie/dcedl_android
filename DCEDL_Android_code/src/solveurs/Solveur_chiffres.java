package solveurs;
import java.util.*;
public class Solveur_chiffres {
	public void insert_in_both_lists(ArrayList<Integer> l1, ArrayList<String> l2, int a, String b) {
		int i = 0;
		boolean cond = false;
		while ((cond) && (i < l1.size()))  {
			cond = (l1.get(i) < a);
			if (!cond) {
				i += 1;
			}
		}
		l1.add(i,a);
		l2.add(i,b);
	}
}
