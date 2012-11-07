import java.util.ArrayList;

public class RSA {
	public static long encrypt(long p, long q, int e, int m){
		long n = p * q; 
		long nn = (p-1) * (q-1);
		ArrayList<Long> quotients = Euclidean.euclidean(e, nn);
		long d = Euclidean.ext_euclidean(e, nn, quotients.get(0), quotients);
		long c = (long)Math.pow(m, e) % n;
		System.out.print(c);
		return c;
	}
	
	public static void main(String[] args){
		System.out.println(Math.pow(30120, 9007));
		encrypt(885320963, 238855417, 9007, 30120);
	}
}
