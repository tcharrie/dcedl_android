package tests;
import java.util.*;
import static java.util.Arrays.asList;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();
		List<Integer> x = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		List<Integer> y = new ArrayList<>(x);
		x.add(4,5);
		 System.out.println(x.toString());
		 System.out.println(y.toString());
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(totalTime/1000000);
		}

}
