import java.util.ArrayList;

public class Euclidean {
	public static ArrayList<Long> euclidean(long a, long b){
		System.out.print("gcd(" + a + ", " + b + ") = ");
		if (a < b) {
			long temp = a;
			a = b;
			b = temp;
		}
		ArrayList<Long> q = new ArrayList<Long>();
		long remainder = a % b;
		long quotient = a / b;
		q.add((long) 0);
		q.add(quotient);
		while (remainder != 0){
			a = (a - remainder) / quotient;
			b = remainder;
			remainder = a % b;
			quotient = a / b;
			q.add(quotient);
		}
		q.set(0, b);
		System.out.println(b);
		System.out.println("quotients are " + q);
		return q;
	}	
	
	public static long ext_euclidean(long a, long b, long gcd, ArrayList<Long> q){
		ArrayList<Long> x = new ArrayList<Long>();
		ArrayList<Long> y = new ArrayList<Long>();
		x.add((long) 0); x.add((long) 1);
		y.add((long) 1); y.add((long) 0);
		
		for (int i = 2; i < q.size(); i++){
			x.add(-q.get(i-1)*x.get(i-1)+x.get(i-2));
			y.add(-q.get(i-1)*y.get(i-1)+y.get(i-2));
		}
		System.out.println("x are " + x);
		System.out.println("y are " + y);
		System.out.println(a + " * " + x.get(x.size()-1) + " + " + b + "　＊　" + y.get(y.size() - 1) + " = " + gcd);
		return x.get(x.size() - 1);
	}
	
	public static void main(String[] args){
		ArrayList<Long> q = euclidean(482, 1180);
		ext_euclidean(482, 1180, 2, q);
	}
}
