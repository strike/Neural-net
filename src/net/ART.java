package net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

import presentation.*;

public class ART {

	private int max;
	private double[][] F2;
	private int F2n;
	private boolean[] F2active;
	private double[] D;
	private final static int B = 100;
	private final static double P = 0.01;
	private final static double Lambda = 0.75;
	private String test;

	private void getDump() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"/home/strike/eclipse/thrigram/war/dump"+this.max + ".dump"));
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

	public String run() {
		this.test = "";
		// побольше

		getDump();
		String out = "";
		if (this.F2n == 0) {
			// 19			
			this.F2[this.F2n] = this.D;
			this.F2n++;
			this.test += "!@#$%^&*()";
		} else {
			// 5 ????
			this.F2active = new boolean[1000]; 
			activeF2();
			while (function1());

		}
		
		for (int i = 0; i < this.F2n; i++) {
			double lambda =  mod(multiplyVector(this.D, this.F2[i]));	
			out += "кластер" + lambda + "!<br />";
		}
		//out += "!!!" + this.F2n + "<br />";
		/*for (int i = 0; i < this.F2n; i++) {
			for (int j = 0; j < this.F2[i].length; j++){
				out += this.F2[i][j] + " ";
			}
			out += "<br />";
		}
		for (int i = 0; i < this.D.length; i++){
			out += this.D[i] + " ";
		}*/
		//Dump();
		return out;
	}

	private void activeF2() {
		for (int i = 0; i < this.F2.length; i++){
			this.F2active[i] = true;
		}
		
	}

	private boolean function1() {
		double Smax = -1;
		int Vnear = -1;
		for (int i = 0; i < this.F2n; i++) {
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
				/ (ART.B + mod(sumVector(this.F2[Vnear]))) 
				< mod(sumVector(this.D))
				/ (ART.B + this.D.length)) {
			this.F2[this.F2n] = this.D;
			this.F2n++;
			this.test += "!aa!";
		} else {
			double Sij = mod(multiplyVector(this.D, this.F2[Vnear]));
			// 14
			if (Sij < ART.P) {
				unactiveF2(Vnear);
				if (isF2haveActiveVectors()){
					return true;
				} else {
					this.F2[this.F2n] = this.D;
					this.F2n++;
					this.test += "!bb!";
				}
			} else {
				double sum = 0; // 16
				double skal = ART.Lambda * multiplyVector(this.D, this.F2[Vnear]);
				for (int i = 0; i < this.F2[Vnear].length; i++){
					this.F2[Vnear][i] = skal + (1-ART.Lambda)*this.F2[Vnear][i];
					// 16
					sum += this.F2[Vnear][i]*this.F2[Vnear][i];

				}
				// Normalize 16
				double modVector = Math.sqrt(sum);

				for (int i = 0; i < this.F2[Vnear].length; i++){
					this.F2[Vnear][i] = this.F2[Vnear][i] / modVector;
				}
			}
		}
		return false;
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

	private double mod(double e) {
		return e > 0 ? e : -e;
	}

	private double sumVector(double[] vnear) {
		double sum = 0;
		for (int i = 0; i < vnear.length; i++){
			sum += vnear[i];
		}
		return sum;
	}

	private double multiplyVector(double[] d2, double[] is) {
		double sum = 0;
		for (int i = 0; i < d2.length; i++) {
			sum += d2[i] * is[i];
		}
		return sum;
	}

	private boolean isActive(int i) {
		if(this.F2active[i]){
			return true;
		}
		return false;
	}
	
	public int test(){
		return this.D.length;
	}
	
	public void reSetText(String text, String lang){
		presentation.Thrigram term = new presentation.Thrigram();
		term.setLang(lang);
		term.setText(text);
		term.generate();
		this.D = term.getResultS();
		this.max = term.getMax();
	}

	public ART(final String text, final String lang) {
		presentation.Thrigram term = new presentation.Thrigram();
		term.setLang(lang);
		term.setText(text);
		term.generate();
		this.D = term.getResultS();
		this.max = term.getMax();
		this.F2 = new double[1000][this.max];
		this.F2n = 0;
	}
}
