public class Hanoi {

	public static void main(String[] args) {
		hanoi1HardCoded("A", "C");
	}
	
	public static void hanoi1HardCoded(String start, String end) {
		StdOut.println(start + " -> " + end);		
	}
	
	public static void hanoi2HardCoded(String start, String spare, String end) {
		StdOut.println(start + " -> " + spare);		
		StdOut.println(start + " -> " + end);		
		StdOut.println(spare + " -> " + end);		
	}
	
	public static void hanoi3HardCoded(String start, String spare, String end) {
		StdOut.println(start + " -> " + end);		
		StdOut.println(start + " -> " + spare);		
		StdOut.println(end + " -> " + spare);		
		StdOut.println(start + " -> " + end);		
		StdOut.println(spare + " -> " + start);		
		StdOut.println(spare + " -> " + end);		
		StdOut.println(start + " -> " + end);		
	}
	
	public static void hanoi1CallingSimplerMethods(String start, String end) {
		StdOut.println(start + " -> " + end);		
	}
	
	public static void hanoi2CallingSimplerMethods(String start, String spare, String end) {
		hanoi1CallingSimplerMethods(start, spare);		
		StdOut.println(start + " -> " + end);		
		hanoi1CallingSimplerMethods(spare, end);		
	}
	
	public static void hanoi3CallingSimplerMethods(String start, String spare, String end) {
		hanoi2CallingSimplerMethods(start, end, spare);		
		StdOut.println(start + " -> " + end);		
		hanoi2CallingSimplerMethods(spare, start, end);		
	}

	public static void hanoi(String start, String spare, String end, int n) {
		if (n == 1) {
			StdOut.println(start + " -> " + end);
		} else {
			hanoi(start, end, spare, n - 1);
			StdOut.println(start + " -> " + end);
			hanoi(spare, start, end, n - 1);			
		}
	}

}
