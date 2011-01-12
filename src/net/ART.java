package net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import presentation.*;

public class ART {

	private int max;
	private double[][] F2;
	private int F2n;
	private boolean[] F2active;
	private double[] D;
	private final static int B = 1;
	private final static double P = 0.5;
	private final static double Lambda = 0.5;

	private void getDump() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"/home/strike/eclipse/thrigram/war/dump"));
			String str;
			while ((str = in.readLine()) != null) {
				String[] c = new String[this.max];
				c = str.split(" ");
				for (int i = 0; i < c.length; i++) {
					this.F2[this.F2n][i] = Integer.getInteger(c[i]);
					this.F2n++;
				}
			}
			in.close();
		} catch (IOException e) {
		}
	}

	public void run() {
		// побольше
		// this.F2 = new int [1000];
		this.F2n = 0;
		getDump();
		if (this.F2n == 0) {
			// 19
			this.F2n++;
			this.F2[this.F2n] = this.D;
		} else {
			// 5 ????
			this.F2active = new boolean[1000]; 
			activeF2();
			while (function1()) {}

		}
	}

	private void activeF2() {
		for (int i = 0; i < this.F2.length; i++){
			this.F2active[i] = true;
		}
		
	}

	private boolean function1() {
		double Smax = -1;
		int Vnear = -1;
		for (int i = 1; i < this.F2n; i++) {
			if (isActive(i)) {
				double Sij = multiplyVector(this.D, this.F2[i]);
				if (Sij > Smax) {
					Smax = Sij;
					// Vnear = this.F2[i];
					Vnear = i;
				}
			}
		}

		if (mod((multiplyVector(this.F2[Vnear], this.D)))
				/ (ART.B + mod(sumVector(this.D))) < mod(sumVector(this.D))
				/ (ART.B + this.D.length)) {
			this.F2n++;
			this.F2[this.F2n] = this.D;
		} else {
			double Sij = mod(multiplyVector(this.D, this.F2[Vnear]));
			// 14
			if (Sij < ART.P) {
				unactiveF2(Vnear);
				if (isF2haveActiveVectors()){
					return true;
				} else {
					this.F2n++;
					this.F2[this.F2n] = this.D;
				}
			} else {
				// TODO;
			}
		}
	}

	private boolean isF2haveActiveVectors() {		
		for (int i = 0; i < this.F2.length; i++){
			if(this.F2active[i]){
				return true;
			}
		}
		return false;
	}

	private void unactiveF2(int vnear) {
		this.F2active[vnear] = false;		
	}

	private int mod(double e) {
		// TODO Auto-generated method stub
		return (int) (e > 0 ? e : -e);
	}

	private double sumVector(int[] d2) {
		// TODO Auto-generated method stub
		return 0;
	}

	private double multiplyVector(double[] d2, double[] is) {
		double sum = 0;
		for (int i = 0; i < d2.length; i++) {
			sum += d2[i] * is[i];
		}
		return 0;
	}

	private boolean isActive(int i) {
		if
		return false;
	}

	public ART(final String text, final String lang) {
		presentation.Thrigram term = new presentation.Thrigram();
		term.setLang(lang);
		term.setText(text);
		term.generate();
		this.D = term.getResultS();
		this.max = term.getMax();
	}
}
