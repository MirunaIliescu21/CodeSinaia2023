package JavaBasics;

public class Pi {

    // Real PI number with at least 15 digits (ref: https://en.wikipedia.org/wiki/Pi)
    private final static double _REAL_PI = 3.141_592_653_589_793;
	
	public static void main(String[] args) {
		int n = 100000000;
        double javaPi = Math.PI;
        double leibnitzPi = leibnitz(n);
        double nilakanthaPi = nilakantha(n);
        System.out.printf("Real Pi       = %.16g\n", _REAL_PI);
        System.out.printf("Java Pi       = %.16g\n", javaPi);
        System.out.printf("Iterations----: %d\n", n);
        System.out.printf("Leibnitz Pi   = %.16g\n", leibnitzPi);
        System.out.printf("Nilakantha Pi = %.16g\n", nilakanthaPi);
	}

    /**
     * Computes the PI number through n iterations in the Leibnitz seriles.
     * (ref: https://en.wikipedia.org/wiki/Leibniz_formula_for_%CF%80)
     * @param n - Number of iterations.
     * @return Calculated value of Pi.
     */
	private static double leibnitz(int n) {
		double pi = 4.0;
		int sign = -1;
		int d = 3;
		for (int i = 1; i <= n; i++) {
			pi += sign * 4.0 / d;
			sign = -sign;
			d += 2;
		}
		return pi;
	}

    /**
     * Computes the PI number through n iterations in the Nilakantha series.
     * (ref: http://maeckes.nl/Formule%20voor%20pi%20(Nilakantha)%20GB.html)
     * @param n - Number of iterations.
     * @return Calculated value of Pi.
     */
    private static double nilakantha(int n) {
		double pi = 3.0;
		int d = 2;
		int sign = 1;
		for (int i = 1; i <= n; i++) {
			pi += sign * 4.0 / d / (d+1) / (d+2);
			d+=2;
			sign = -sign;
		}
		return pi;
	}
}


