import java.util.Random;

/**
 * Recursive FFT vs. Iterative FFT
 * Final Paper
 * @author wooseok kim
 *
 */
public class FFTRecursive {
	
	/**
	 * @param Coefficients
	 */
	public void run(int Coefficients) {

		double randNum;
		int sizeOfcomplex = 2 * Coefficients;
		Complex[] a0 = new Complex[sizeOfcomplex];
		Complex[] a1 = new Complex[sizeOfcomplex];
		Complex[] omega = new Complex[sizeOfcomplex];
		Complex[] omegaInverse = new Complex[sizeOfcomplex];

		Random randGen = new Random();

		// Generate the random numbers between -1 and 1
		// Store the random numbers to arrays
		for (int i = 0; i < Coefficients; i++) {
			randNum = randGen.nextDouble() * 2 - 1;
			a0[i] = new Complex(Double.valueOf(randNum), 0);

			randNum = randGen.nextDouble() * 2 - 1;
			a1[i] = new Complex(Double.valueOf(randNum), 0);
		}
		// padding with zeros
		for (int t = Coefficients; t < sizeOfcomplex; t++) {
			a0[t] = new Complex(0, 0);
			a1[t] = new Complex(0, 0);
		}
		
		// complex nth root of unity
		for (int a = 0; a < omega.length; a++) {
			double alpha = 2 * Math.PI * a;
			omega[a] = new Complex(Math.cos(alpha / Coefficients), Math.sin(alpha / Coefficients));
		}

		// Inverse of the complex nth root of unity
		for (int a = 0; a < omega.length; a++) {
			omegaInverse[a] = new Complex(omega[a].realNumber, -omega[a].imaginaryNumber);
		}

		// Calculate FFT
		FFTRecCal fftCal = new FFTRecCal();
		Complex[] a0FFT = fftCal.FFTmultiplication(a0, a0.length, 1, omega);
		Complex[] a1FFT = fftCal.FFTmultiplication(a1, a1.length, 1, omega);
		
		// multiplication values the point by point
		Complex[] afterMultiplication = new Complex[sizeOfcomplex];

		// multiplication point by point 
		for (int t = 0; t < sizeOfcomplex; t++)
			afterMultiplication[t] = a0FFT[t].multiplication(a1FFT[t]);

		// inverse values
		for (int a = 0; a < omega.length; a++) {
			double alpha = 2 * Math.PI * a;
			omega[a] = new Complex(Math.cos(alpha / Coefficients), -Math.sin(alpha / Coefficients));
		}
		Complex[] inverseFFT = fftCal.FFTmultiplication(afterMultiplication, afterMultiplication.length, 1, omega);
		Complex[] answer = new Complex[inverseFFT.length];
		// output
		/*
		System.out.println("actual output");
		for (int t = 0; t < answer.length; t++) {
			System.out.println((inverseFFT[t].realNumber / p.length) + " X^" + t);
		}
		*/
	}
}
