
public class Tester {

	public static void main(String[] args) {
		
		//System.out.println(triangle(4));
		//System.out.println(addDigits(5634));
		System.out.println(deDup("ABCCDEE"));
		//System.out.println(extractNum("1AB2CD34"));
		//System.out.println(val("42H"));
		//System.out.println(squareRoot(25));
		//System.out.println(isSorted(new int[]{2,1,2,3,4,3}));
		//System.out.println(charCount("abccdefgccoiu;ljk;lkcc", 'c'));
	}
	
	public static int charCount(String s, char c) {
		if(s.length() == 0) return 0;
		return (s.charAt(0) == c ? 1 : 0) + charCount(s.substring(1), c);
	}
	
	public static boolean isSorted(int[] nums) {
		return isSorted(nums, 0);
	}
	
	private static boolean isSorted(int[] nums, int index) {
		if(index > nums.length-2) return true;
		if(nums[index] > nums[index+1]) return false;
		return isSorted(nums, index+1);
	}
	
	public static double squareRoot(int x) {
		return squareRoot(x, x/2);
	}
	
	private static double squareRoot(double x, double guess) {
		if(guess == (guess + (x/guess))/2) return guess;
		return squareRoot(x,(guess + (x/guess))/2);
			
		//return (guess == (guess + (x/guess))/2) ? guess : (guess+(x/guess)/2;
	}
	
	public static int val(String s) {
		return Integer.parseInt("0" + val(s, true));
	}
	
	private static String val(String s, boolean tmp) {
		if(s.length() == 0 || (s.charAt(0) < '0' || s.charAt(0) > '9'))
				return "";
		return (s.charAt(0) - 48 + val(s.substring(1), tmp));
	}
	
	public static String extractNum(String s) {
		if(s.length() == 0) return "";
		if(s.charAt(0) >= '0' && s.charAt(0) <= '9')
			return s.charAt(0) + extractNum(s.substring(1));
		return extractNum(s.substring(1));
	}
	
	public static String deDup(String s) {
		if(s.length() < 2) return s;
		if(s.charAt(0) == s.charAt(1))
			return deDup(s.substring(1));
		return s.charAt(0) + deDup(s.substring(1));
		
		//return (s.charAt(0) == s.charAt(1) ? "" : s.charAt(0)) + deDup(s.substring(1));
	}
	
	public static int addDigits(int n) {
		return n == 0 ? 0 : (n%10 + addDigits(n/10));
	}
	
	public static int triangle(int n) {
		return n < 1 ? 1 : n + triangle(n-1);
	}
}
