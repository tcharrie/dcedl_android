package solveurs;
import java.util.*;
public class Solveur_chiffres {
	public void insert_in_both_lists(ArrayList<Integer> l1, ArrayList<String> l2, int a, String b) {
		int i = 0;
		boolean cond = false;
		while ((!cond) && (i < l1.size()))  {
			cond = (l1.get(i) < a);
			if (!cond) {
				i += 1;
			}
		
		}
		l1.add(i,a);
		l2.add(i,b);
	}
	
	public String solver_that_stops_when_resolved(ArrayList<Integer> l1, ArrayList<String> l2, int nb_steps, int result) {
	
		if (nb_steps == 1) {
			if (l1.get(0) == result) {
				return l2.get(0);
			}
			else {
				return "";
			}
		}
		else {
			if (nb_steps == 2) {
				int a = l1.get(0);
				int b = l1.get(1);


				if ((a+b) == result) {
					return l2.get(0) + "+" + l2.get(1);
				}
				else {
					if ((a-b) == result) {
						return l2.get(0) + "-" + l2.get(1);
					}
					else {
						if ((a*b) == result) {
							return l2.get(0) + "*" + l2.get(1);
						}
						else {
							if ((a%b == 0) && (a/b == result)) {
								return l2.get(0) + "/" + l2.get(1);
							}
							else {
								return "";
							}
						}
					}
				}
			}
			else {

				for (int i = 0; i < l1.size()-1; i++) {
					for (int j = i+1; j < l1.size(); j++) {

						int a = l1.get(i);
						int b = l1.get(j);


						if ((a+b) == result) {
							return l2.get(i) + "+" + l2.get(j);
						}
						else {
							if ((a-b) == result) {
								return l2.get(i) + "-" + l2.get(j);
							}
							else {
								if ((a*b) == result) {
									return l2.get(i) + "*" + l2.get(j);
								}
								else {
									if ((a%b == 0) && (a/b == result)) {
										return l2.get(i) + "/" + l2.get(j);
									}
								}
							}
						}
					}
				}
				for (int i = 0; i < l1.size()-1; i++) {
					for (int j = i+1; j < l1.size(); j++) {
						int a = l1.get(i);
						int b = l1.get(j);
						ArrayList<Integer> l1a = new ArrayList(l1);
						ArrayList<String> l2a = new ArrayList(l2);
						l1a.remove(j);
						l1a.remove(i);
						l2a.remove(j);
						l2a.remove(i);

						ArrayList<Integer> l1b = new ArrayList(l1a);
						ArrayList<String> l2b = new ArrayList(l2a);
						insert_in_both_lists(l1b,l2b,(a+b),"("+l2.get(i)+"+"+l2.get(j)+")");
						String sol = solver_that_stops_when_resolved(l1b,l2b,(nb_steps-1),result);
						if (sol != "") {
							return sol;
						}
						if (b < a) {
							l1b = new ArrayList(l1a);
							l2b = new ArrayList(l2a);
							insert_in_both_lists(l1b,l2b,(a-b),"("+l2.get(i)+"-"+l2.get(j)+")");
							sol = solver_that_stops_when_resolved(l1b,l2b,(nb_steps-1),result);
							if (sol != "") {
								return sol;
							}
						}
						if (b > 1) {
							l1b = new ArrayList(l1a);
							l2b = new ArrayList(l2a);
							insert_in_both_lists(l1b,l2b,(a*b),"("+l2.get(i)+"*"+l2.get(j)+")");
							sol = solver_that_stops_when_resolved(l1b,l2b,(nb_steps-1),result);
							if (sol != "") {
								return sol;
							}
						}
						if ((b > 1) && (a % b == 0)) {
							l1b = new ArrayList(l1a);
							l2b = new ArrayList(l2a);
							insert_in_both_lists(l1b,l2b,(a/b),"("+l2.get(i)+"/"+l2.get(j)+")");
							sol = solver_that_stops_when_resolved(l1b,l2b,(nb_steps-1),result);
							if (sol != "") {
								return sol;
							}
						}
					}
				}
			}
			return "";
		}
	}
}
