package presentation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.lucene.morphology.*;
import org.apache.lucene.morphology.russian.RussianLuceneMorphology;

public class Term {
	private String term[];
	private int len;
	private String lang;
	// if 0 then html, 1 - dat
	private int output = 0;

	private String debug = "!";

	public void setLang(String lang) {
		if (lang.equals("EN")) {
			this.lang = "EN";
		} else {
			this.lang = "RU";
		}
	}

	public String debug() {
		return this.debug;
	}

	public boolean setText(String text) {
		// RU or EN
		String regexp;
		if (this.lang == "RU") {
			regexp = "[^а-яё]+";
		} else {
			regexp = "[^a-z]+";
		}

		String cache = text.toLowerCase().replaceAll(regexp, " ").trim();
		// this.debug += "~" + cache.length() + "~\n";
		if (cache.length() == 0) {
			// this.debug += "yes\n";
			return false;
		} else {
			this.term = text.toLowerCase().replaceAll(regexp, " ").trim()
					.split(" ");
			this.len = this.term.length;

		}
		return true;
	}

	public void setOutput(int i) {
		this.output = i;
	}

	public String getResult() {
		int z = 0;
		double sum = 0;
		String r = "";
		Map<String, Integer> termarray = new HashMap<String, Integer>();
		Object cache;

		LuceneMorphology luceneMorph = null;
		try {
			if (this.lang == "RU") {
				luceneMorph = new RussianLuceneMorphology();
			} else {
				luceneMorph = new EnglishLuceneMorphology();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < this.len; i++) {
			// cache = luceneMorph.getNormalForms(this.term[i]);
			sum++;
			cache = luceneMorph.getNormalForms(
					this.term[i].replaceAll("ing$", "")).get(0).toString();
			if (termarray.containsKey(cache)) {
				termarray.put(cache.toString(), termarray.get(cache) + 1);
			} else {
				termarray.put(cache.toString(), 1);
			}

		}

		// Get Map in Set interface to get key and value
		Set<Entry<String, Integer>> s = termarray.entrySet();

		// Move next key and value of Map by iterator
		Iterator<Entry<String, Integer>> it = s.iterator();

		while (it.hasNext()) {
			// key=value separator this by Map.Entry to get key and value
			Map.Entry<String, Integer> m = it.next();

			// getKey is used to get key of Map
			String key = (String) m.getKey();

			// getValue is used to get value of key in Map
			int value = (Integer) m.getValue();
			if (this.output == 0) {
				r += "data.setCell(" + z + ",0,'" + key + "');\n";
				r += "data.setCell(" + z + ",1," + value + ");\n";
				r += "data.setCell(" + z + ",2," + value/sum + ");\n";
				z++;
			} else {
				r += "\"" + key + "\"\t" + value + "\n";
			}

		}
		if (this.output == 0) {
			r = "data.addRows(" + z + ");\n" + r;
		}
		/*
		 * r +=this.len; for (int i = 0; i< this.len; i++){ r += this.term[i] +
		 * "\n"; }
		 */
		return r;
	}

}
