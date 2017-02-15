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
	
	private String compare(int a, int b, String as, String bs, int result) {
		if ((a+b) == result) {
			return as + "+" + bs;
		}
		else {
			if ((a-b) == result) {
				return as + "-" + bs;
			}
			else {
				if (b > 1) {
					if ((a*b) == result) {
						return as + "*" + bs;
					}
					else {
						if ((a%b == 0) && (a/b == result)) {
							return as + "/" + bs;
						}
						else {
							return "";
						}
					}
				}
				else {
					return "";
				}
			}
		}
	}
    public class Pair<ArrayList, Integer> {
        public final ArrayList str;
        public final Integer it;

        public Pair(ArrayList a, Integer b) {
            this.str = a;
            this.it = b;
        }
    }
//	private Pair compare_avec_borne(int a, int b, String as, String bs, int result, int borne) {
//		ArrayList<String> sol = new ArrayList<String>();
//		int bornenew = borne;
//		if (borne == 0) {
//			sol.add(compare(a,b,as,bs,result));
//		}
//		else {
//			int iplus = Math.abs((a+b)-result); 
//		if (iplus <= bornenew) {
//			if (iplus < bornenew) {
//				bornenew = iplus;
//				sol.add(as+"+"+bs);
//			}
//		}
//		int imoins = Math.abs((a-b)-result); 
//		if (imoins <= bornenew) {
//			if (imoins < bornenew) {
//				bornenew = imoins;
//				sol = new ArrayList<String>();
//				sol.add(as+"-"+bs);
//			}
//		}
//		if (b > 1) {
//			int ifois = Math.abs((a*b)-result); 
//			if (ifois <= bornenew) {
//				if (ifois < bornenew) {
//					bornenew = ifois;
//					sol = new ArrayList<String>();
//					sol.add(as+"*"+bs);
//				}
//			}
//		if ((a%b) == 0)
//			else {
//				if ((a-b) == result) {
//					return as + "-" + bs;
//				}
//				else {
//					if ((a*b) == result) {
//						return as + "*" + bs;
//					}
//					else {
//						if ((a%b == 0) && (a/b == result)) {
//							return as + "/" + bs;
//						}
//						else {
//							return "";
//						}
//					}
//				}
//			}
//		}
//		return (sol,bornenew);
//	}
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
				return compare(l1.get(0),l1.get(1),l2.get(0),l2.get(1),result);
			}
			else {
				String sol = "";
				for (int i = 0; i < l1.size()-1; i++) {
					for (int j = i+1; j < l1.size(); j++) {
						sol = compare(l1.get(0),l1.get(1),l2.get(0),l2.get(1),result);
						if (sol != "") {
							return sol;
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
						sol = solver_that_stops_when_resolved(l1b,l2b,(nb_steps-1),result);
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
	
//	public ArrayList<String> solver_avec_meilleure_approche(ArrayList<Integer> l1, ArrayList<String> l2, int nb_steps, int result,int meilleureapproche) {
//		if (nb_steps == 1) {
//			if (l1.get(0) == result) {
//				return l2;
//			}
//			else {
//				return new ArrayList();
//			}
//		}
//		else {
//			if (nb_steps == 2) {
//				String compare(l1.get(0),l1.get(1),l2.get(0),l2.get(1),result);
//			}
//			else {
//				String sol = "";
//				for (int i = 0; i < l1.size()-1; i++) {
//					for (int j = i+1; j < l1.size(); j++) {
//						sol = compare(l1.get(0),l1.get(1),l2.get(0),l2.get(1),result);
//						if (sol != "") {
//							return sol;
//						}
//					}
//				}
//				for (int i = 0; i < l1.size()-1; i++) {
//					for (int j = i+1; j < l1.size(); j++) {
//						int a = l1.get(i);
//						int b = l1.get(j);
//						ArrayList<Integer> l1a = new ArrayList(l1);
//						ArrayList<String> l2a =return new ArrayList(l2);
//						l1a.remove(j);
//						l1a.remove(i);
//						l2a.remove(j);
//						l2a.remove(i);
//
//						ArrayList<Integer> l1b = new ArrayList(l1a);
//						ArrayList<String> l2b = new ArrayList(l2a);
//						insert_in_both_lists(l1b,l2b,(a+b),"("+l2.get(i)+"+"+l2.get(j)+")");
//						sol = solver_that_stops_when_resolved(l1b,l2b,(nb_steps-1),result);
//						if (sol != "") {
//							return sol;
//						}
//						if (b < a) {
//							l1b = new ArrayList(l1a);
//							l2b = new ArrayList(l2a);
//							insert_in_both_lists(l1b,l2b,(a-b),"("+l2.get(i)+"-"+l2.get(j)+")");
//							sol = solver_that_stops_when_resolved(l1b,l2b,(nb_steps-1),result);
//							if (sol != "") {
//								return sol;
//							}
//						}
//						if (b > 1) {
//							l1b = new ArrayList(l1a);
//							l2b = new ArrayList(l2a);
//							insert_in_both_lists(l1b,l2b,(a*b),"("+l2.get(i)+"*"+l2.get(j)+")");
//							sol = solver_that_stops_when_resolved(l1b,l2b,(nb_steps-1),result);
//							if (sol != "") {
//								return sol;
//							}
//						}
//						if ((b > 1) && (a % b == 0)) {
//							l1b = new ArrayList(l1a);
//							l2b = new ArrayList(l2a);
//							insert_in_both_lists(l1b,l2b,(a/b),"("+l2.get(i)+"/"+l2.get(j)+")");
//							sol = solver_that_stops_when_resolved(l1b,l2b,(nb_steps-1),result);
//							if (sol != "") {
//								return sol;
//							}
//						}
//					}
//				}
//			}
//			return "";
//		}
//		
//		return new ArrayList();
//	}
}
