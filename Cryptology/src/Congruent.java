import java.util.ArrayList;

public class Congruent {
	public static void exponent(long a, long e, long n){
		// a^e mod n
		long temp = a % n;
		for (int i = 0; i <= e; i++){
			temp = (temp * temp) % n;
		}
		System.out.println(temp);
	}
	
	public static void main(String[] args){
		long n = 211463707796206571l;
		exponent(30120, 9007, 211463707796206571l);
		ArrayList<Long> r = new ArrayList<Long>();
		r.add((long)Math.pow(30120, 4) % n);
		//r.add(r.get(0) 
	}
}
