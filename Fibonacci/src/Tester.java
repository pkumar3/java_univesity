
public class Tester {

	public static void main(String[] args) {

		//System.out.println(fib(50));
		//System.out.println(fib2(1426));
		
		//System.out.println(countDigits("12abc45"));
		
		//System.out.println(changeCase("HeLLo"));
		
	}
	
	public static long  fib(int n) {
		if(n < 3) return 1;
		
		return fib(n-2) + fib(n-1);
	}

	public static long fib2(int n) {
		return fib2(n, 3, 1, 1);
	}
	
	private static long fib2(int n, int p, long nextHighest, long highest) {
		if(n < 3) return 1;
		if(p > n) return highest;
		return fib2(n, p+1, highest, nextHighest+highest);
	}
	
	public static int countDigits(String s) {
		if(s.length() == 0) return 0;
		if(s.charAt(0) > '0' && s.charAt(0) < '9')
			return 1 + countDigits(s.substring(1));
		return countDigits(s.substring(1));
	}
	
	public static String changeCase(String s) {
		if(s.length() == 0) return "";
		
		if(s.charAt(0) >= 'A' && s.charAt(0) <= 'Z') 
			return (char)(s.charAt(0)+32) + "" + changeCase(s.substring(1));
		else if(s.charAt(0) >= 'a' && s.charAt(0) <= 'z')
			return (char)(s.charAt(0)-32) + "" + changeCase(s.substring(1));
		return s.charAt(0) + changeCase(s.substring(1));
	}
	
}
