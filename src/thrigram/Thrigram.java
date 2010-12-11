package thrigram;

public class Thrigram {
	private String text;
	private char[] txt;
	private int len;
	private int N = 3;
	private int M = 26;
	private int z = 0;
	//private String lang;

	public void setText(String text) {
		this.text = text;
	}
	
/*	public void setLang(String lang) {
		this.lang = lang;
	}*/

	public String getResult() {
		String r = "";
		this.text = this.text.replaceAll("[^a-z]+", " ");
		this.txt = this.text.toLowerCase().toCharArray();
		this.len = this.txt.length;
		int max = this.M*this.M*this.M + this.M*this.M +this.M + 1;
		String[] thr;
		thr = new String[max];

		double[] V;
		V = new double[max];

		int k;
		

		for (int i = 0; i <= this.len - this.N; i++) {
			k = this.M * this.M * s2c(this.txt[i]) + this.M
					* s2c(this.txt[i + 1]) + s2c(this.txt[i + 2]);
			thr[k] = String.valueOf(this.txt[i])
					+ String.valueOf(this.txt[i + 1])
					+ String.valueOf(this.txt[i + 2]);
			V[k] = V[k]
					+ SpaceCur(this.txt[i], this.txt[i + 1], this.txt[i + 2]);
		}
		
		
		
		for (int i = 0; i < max; i++){
			if (V[i] == 0){
				continue;
			}
			r += "data.setCell(" + z + ",0," + i +");\n";
			r += "data.setCell(" + z + ",1,'" + thr[i] +"');\n";
			r += "data.setCell(" + z + ",2," + V[i] +");\n";
			this.z++;
		}
		r = "data.addRows(" + z + ");\n" + r;
	   
	     
		return r;
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
		switch (c) {
		case 'a':
			return 1;
		case 'b':
			return 2;
		case 'c':
			return 3;
		case 'd':
			return 4;
		case 'e':
			return 5;
		case 'f':
			return 6;
		case 'g':
			return 7;
		case 'h':
			return 8;
		case 'i':
			return 9;
		case 'j':
			return 10;
		case 'k':
			return 11;
		case 'l':
			return 12;
		case 'm':
			return 13;
		case 'n':
			return 14;
		case 'o':
			return 15;
		case 'p':
			return 16;
		case 'q':
			return 17;
		case 'r':
			return 18;
		case 's':
			return 19;
		case 't':
			return 20;
		case 'u':
			return 21;
		case 'v':
			return 22;
		case 'w':
			return 23;
		case 'x':
			return 24;
		case 'y':
			return 25;
		case 'z':
			return 26;
		}
		return 0;
	}
}
