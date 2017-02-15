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
		String aas = as + "; ";
		String bbs = bs + "; ";
		if (as.equals(""+a)) {
			aas = "";
		}
		if (bs.equals(""+b)) {
			bbs = "";
		}
		if ((a+b) == result) {
			return aas +  bbs + a + " + " + b + " = " + result;
		}
		else {
			if ((a-b) == result) {
				return aas + bbs  + a + " - " + b + " = " + result;
			}
			else {
				if (b > 1) {
					if ((a*b) == result) {
						return aas  + bbs  + a + " * " + b + " = " + result;
					}
					else {
						if ((a%b == 0) && (a/b == result)) {
							return aas  + bbs  + a + " / " + b + " = " + result;
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
    public class Pair {
        public ArrayList<String> str;
        public int it;

        public Pair(ArrayList<String> a, int b) {
            this.str = a;
            this.it = b;
        }
        
        public String toString() {
        	return this.str.toString() + " - " + this.it;
        }
    }
	private Pair compare_avec_borne(int a, int b, String as, String bs, int result, int borne) {
		String aas = as + "; ";
		String bbs = bs + "; ";
		if (as.equals(""+a)) {
			aas = "";
		}
		if (bs.equals(""+b)) {
			bbs = "";
		}
		ArrayList<String> sol = new ArrayList<String>();
		int bornenew = borne;
		if (borne == 0) {
			String s = compare(a,b,as,bs,result);
			if (s != "") {
				sol.add(s);
			}
		}
		else {
			int iplus = Math.abs((a+b)-result); 
			if (iplus <= bornenew) {
				bornenew = iplus;
				sol.add(aas +  bbs + a + " + " + b + " = " + (a+b));
			}
			int imoins = Math.abs((a-b)-result); 
			if (imoins <= bornenew) {
				if (imoins < bornenew) {
					bornenew = imoins;
					sol = new ArrayList<String>();
					sol.add(aas +  bbs + a + " - " + b + " = " + (a-b));
				}
				else {
					sol.add(aas +  bbs + a + " - " + b + " = " + (a-b));
				}
			}
		if (b > 1) {
			int ifois = Math.abs((a*b)-result); 
			if (ifois <= bornenew) {
				if (ifois < bornenew) {
					bornenew = ifois;
					sol = new ArrayList<String>();
					sol.add(aas +  bbs + a + " * " + b + " = " + (a*b));
				}
				else {
					sol.add(aas +  bbs + a + " * " + b + " = " + (a*b));
				}
			}
			if ((a%b) == 0) {
				int idiv = Math.abs((a/b)-result); 
				if (idiv <= bornenew) {
					if (idiv < bornenew) {
						bornenew = idiv;
						sol = new ArrayList<String>();
						sol.add(aas +  bbs + a + " / " + b + " = " + (a/b));
					}
					else {
						sol.add(aas +  bbs + a + " / " + b + " = " + (a/b));
					}
				}
			}
		}
		
	}
		return new Pair(sol,bornenew);
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
						String as = l2.get(i);
						String bs = l2.get(j);
						ArrayList<Integer> l1a = new ArrayList(l1);
						ArrayList<String> l2a = new ArrayList(l2);
						l1a.remove(j);
						l1a.remove(i);
						l2a.remove(j);
						l2a.remove(i);

						ArrayList<Integer> l1b = new ArrayList(l1a);
						ArrayList<String> l2b = new ArrayList(l2a);
						String aas = as + "; ";
						String bbs = bs + "; ";
						if (as.equals(""+a)) {
							aas = "";
						}
						if (bs.equals(""+b)) {
							bbs = "";
						}
						insert_in_both_lists(l1b,l2b,(a+b),aas +  bbs + a + " + " + b + " = " + (a+b));
						sol = solver_that_stops_when_resolved(l1b,l2b,(nb_steps-1),result);
						if (sol != "") {
							return sol;
						}
						if (b < a) {
							l1b = new ArrayList(l1a);
							l2b = new ArrayList(l2a);
							insert_in_both_lists(l1b,l2b,(a-b),aas +  bbs + a + " - " + b + " = " + (a-b));
							sol = solver_that_stops_when_resolved(l1b,l2b,(nb_steps-1),result);
							if (sol != "") {
								return sol;
							}
						}
						if (b > 1) {
							l1b = new ArrayList(l1a);
							l2b = new ArrayList(l2a);
							insert_in_both_lists(l1b,l2b,(a*b),aas +  bbs + a + " * " + b + " = " + (a*b));
							sol = solver_that_stops_when_resolved(l1b,l2b,(nb_steps-1),result);
							if (sol != "") {
								return sol;
							}
						}
						if ((b > 1) && (a % b == 0)) {
							l1b = new ArrayList(l1a);
							l2b = new ArrayList(l2a);
							insert_in_both_lists(l1b,l2b,(a/b),aas +  bbs + a + " / " + b + " = " + (a/b));
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
	
	public Pair solver_avec_meilleure_approche(ArrayList<Integer> l1, ArrayList<String> l2, int nb_steps, int result,int meilleureapproche) {
		if (nb_steps == 1) {
			int res = Math.abs(l1.get(0)-result);
			if (res <= meilleureapproche) {
				return new Pair(l2,res);
			}
			else {
				return new Pair(new ArrayList(),meilleureapproche);
			}
		}
		else {
			if (nb_steps == 2) {
			return compare_avec_borne(l1.get(0),l1.get(1),l2.get(0),l2.get(1),result,meilleureapproche);
			}
			else {
				ArrayList<String> liste_courante = new ArrayList<String>();
				int approcheactuelle = meilleureapproche;
				int approcheactuelle2 = approcheactuelle;
				Pair p = new Pair(liste_courante,meilleureapproche);
				for (int i = 0; i < l1.size()-1; i++) {
					for (int j = i+1; j < l1.size(); j++) {
						p = compare_avec_borne(l1.get(i),l1.get(j),l2.get(i),l2.get(j),result,approcheactuelle);
						approcheactuelle2 = p.it;
						if (approcheactuelle2 < approcheactuelle) {
							approcheactuelle = approcheactuelle2;
							liste_courante = p.str;
						}
						else {
							liste_courante.addAll(p.str);
						}
					}
				}
				for (int i = 0; i < l1.size()-1; i++) {
					for (int j = i+1; j < l1.size(); j++) {
						int a = l1.get(i);
						int b = l1.get(j);
						String as = l2.get(i);
						String bs = l2.get(j);
						ArrayList<Integer> l1a = new ArrayList(l1);
						ArrayList<String> l2a = new ArrayList(l2);
						l1a.remove(j);
						l1a.remove(i);
						l2a.remove(j);
						l2a.remove(i);

						String aas = as + "; ";
						String bbs = bs + "; ";
						if (as.equals(""+a)) {
							aas = "";
						}
						if (bs.equals(""+b)) {
							bbs = "";
						}
						ArrayList<Integer> l1b = new ArrayList(l1a);
						ArrayList<String> l2b = new ArrayList(l2a);
						insert_in_both_lists(l1b,l2b,(a+b),aas +  bbs + a + " + " + b + " = " + (a+b));
						p = solver_avec_meilleure_approche(l1b,l2b,(nb_steps-1),result,approcheactuelle);
						approcheactuelle2 = p.it;
						if (approcheactuelle2 < approcheactuelle) {
							approcheactuelle = approcheactuelle2;
							liste_courante = p.str;
						}
						else {
							liste_courante.addAll(p.str);
						}
						if (a > b) {
							l1b = new ArrayList(l1a);
							l2b = new ArrayList(l2a);
							insert_in_both_lists(l1b,l2b,(a-b),aas +  bbs + a + " - " + b + " = " + (a-b));
							p = solver_avec_meilleure_approche(l1b,l2b,(nb_steps-1),result,approcheactuelle);
							approcheactuelle2 = p.it;
							if (approcheactuelle2 < approcheactuelle) {
								approcheactuelle = approcheactuelle2;
								liste_courante = p.str;
							}
							else {
								liste_courante.addAll(p.str);
							}
						}
						if (b > 1) {
							l1b = new ArrayList(l1a);
							l2b = new ArrayList(l2a);
							insert_in_both_lists(l1b,l2b,(a*b),aas +  bbs + a + " * " + b + " = " + (a*b));
							p = solver_avec_meilleure_approche(l1b,l2b,(nb_steps-1),result,approcheactuelle);
							approcheactuelle2 = p.it;
							if (approcheactuelle2 < approcheactuelle) {
								approcheactuelle = approcheactuelle2;
								liste_courante = p.str;
							}
							else {
								liste_courante.addAll(p.str);
							}
							if ((a % b) == 0) {
								l1b = new ArrayList(l1a);
								l2b = new ArrayList(l2a);
								insert_in_both_lists(l1b,l2b,(a/b),aas +  bbs + a + " / " + b + " = " + (a/b));
								p = solver_avec_meilleure_approche(l1b,l2b,(nb_steps-1),result,approcheactuelle);
								approcheactuelle2 = p.it;
								if (approcheactuelle2 < approcheactuelle) {
									approcheactuelle = approcheactuelle2;
									liste_courante = p.str;
								}
								else {
									liste_courante.addAll(p.str);
								}
							
							}
						}
					}
				}
				return new Pair(new ArrayList(new HashSet(liste_courante)),approcheactuelle);
			}
		}
	}
}
