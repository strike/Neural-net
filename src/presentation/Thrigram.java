package presentation;

//import org.apache.lucene.morphology.*;
//import org.apache.lucene.morphology.russian.RussianLuceneMorphology;

public class Thrigram {
	private char[] txt, alphabet;
	// private String text;
	private int len;
	// Define how many symbol will have one thrigram
	private int N = 3;
	// Number of char
	private int M;

	// if 0 then html, 1 - dat
	private boolean output = true;
	private double sumall = 0;

	private String regexp;

	private String[] thr;
	private double[] V;
	private boolean[] isUse;
	private int max;
	
	private double[] Vnormal;
	
	public void setLang(String lang) {
		if (lang.equals("EN")) {
			lang = "EN";
		} else {
			lang = "RU";
		}
		String alphabet = "";
		if (lang == "RU") {
			alphabet = " абвгдеёжзиклмнопрстуфхцчшщъыьэюяѣi";
		} else {
			alphabet = " abcdefghijklmnopqrstuvwxyz";
		}
		this.regexp = "[^" + alphabet.trim() + "]+";
		this.alphabet = alphabet.toCharArray();
		this.M = this.alphabet.length - 1; // couse space
		this.max = this.M * this.M * this.M + this.M * this.M + this.M + 1;
	}

	public void setText(String text) {
		text = " " + text.trim().toLowerCase().replaceAll(this.regexp, " ")
				+ " ";
		this.txt = text.toCharArray();
		this.len = this.txt.length;
	}

	public void setOutput(int i) {
		this.output = false;
	}

	/*
	 * public String Test(String t){ return t; }
	 * 
	 * public String Test2(String t){ return t.toCharArray().toString() +
	 * Charset.defaultCharset().toString(); }
	 * 
	 * public int GetLen(String t){ return t.length(); }
	 * 
	 * public int GetCharLen(String t){ return t.toCharArray().length; }
	 */
	public void generate() {		
	
		this.thr = new String[this.max];		
		this.V = new double[this.max];
		this.isUse = new boolean[this.max];

		int k;
		double cache;
		
		int forcache =  this.len - this.N;
		for (int i = 0; i <= forcache; i++) {
			k = this.M * this.M * s2c(this.txt[i]) + this.M
					* s2c(this.txt[i + 1]) + s2c(this.txt[i + 2]);
			// need optimize
			this.thr[k] = String.valueOf(this.txt[i])
					+ String.valueOf(this.txt[i + 1])
					+ String.valueOf(this.txt[i + 2]);
			cache = SpaceCur(this.txt[i], this.txt[i + 1], this.txt[i + 2]);
			isUse[k] = true;
			this.V[k] += cache;
			this.sumall += cache;
			//this.debug += this.txt[i]  + "=" + s2c(this.txt[i]) + "  " +  s2c(this.txt[i+1]) + "  " + s2c(this.txt[i+2]) + "\n";
			//this.debug += k + "  " +  this.thr[k] + " " + this.V[k] + " = " + cache + "\n";
		}
		
		normalize();
	}
	
	public String getResult(){
		String r = "";
		int z = 0;
		if (this.output) {			
			for (int i = 0; i < this.max; i++) {
				if (this.isUse[i]) {				
					r += "data.setCell(" + z + ",0," + i + ");\n";
					r += "data.setCell(" + z + ",1,'" + this.thr[i] + "');\n";
					r += "data.setCell(" + z + ",2," + this.V[i] + ");\n";
					r += "data.setCell(" + z + ",3," + this.Vnormal[i] + ");\n";
					z++;
				}
			}
			r = "data.addRows(" + z + ");\n" + r;
		} else {
			for (int i = 0; i < this.max; i++) {
				if (this.isUse[i]) {	
					r += i + "\t";
					r += "\"" + this.thr[i] + "\"" + "\t";
					r += this.V[i] + "\t";
					r += this.Vnormal[i] + "\n";
				//} else {
				//	r += i + "\t" + "!!!" +"\t" + 0 + "\t" + 0 + "\n";
				}
			}
		}
		return r;		
	}
	

	
	public double[] getResultS(){				
		return this.Vnormal;
	}

	public int getMax(){
		return this.max;
	}

	private void normalize() {
		this.Vnormal = new double[this.max];		
		for (int i =0; i < this.max; i++){
			if (this.isUse[i]){
				this.Vnormal[i] = this.V[i]/this.sumall;
			}
		}
	}

	private double SpaceCur(char a, char b, char c) {
		int i = 0;
		if (a == ' ') {
			i++;
		}
		if (b == ' ') {
			i++;
		}
		if (c == ' ') {
			i++;
		}
		if (i == 0) {
			return 1;
		} else {
			if (i == 1) {
				return 0.5;
			} else {
				return 0.25;
			}
		}
	}

	private int s2c(char c) {
		for (int i = 0; i <= this.alphabet.length; i++) {
			if (this.alphabet[i] == c) {
				return i;
			}
		}
		return 0;
	}
}
