
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);

	}

	public static String tail(String str) {
		int len = str.length();
		if (len <= 1) {
			return ""; 
		}
		String tail_str = "";
		for (int i = 1; i < len; i++) {
			tail_str = tail_str + str.charAt(i);
		}
		return tail_str;
	}
	public static int levenshtein(String word1, String word2) {

		if (word1.length() == 0) {
			return word2.length();
		} else if (word2.length() == 0) {
			return word1.length();
		} else if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		} else {
			int lev_1 = levenshtein(tail(word1), word2);
			int lev_2 = levenshtein(word1, tail(word2));
			int lev_3 = levenshtein(tail(word1), tail(word2));
			return 1 + Math.min(Math.min(lev_1, lev_2), lev_3);
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for (int i = 0 ; i < 3000 ; i++){
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int lev = 0;
		int min_lev = threshold;
		boolean flag = false;
		String word_lev = "";

		for (int i = 0; i < dictionary.length; i++){
			lev = levenshtein(word, dictionary[i]);
			if (lev <= threshold){
				flag = true;

				if (min_lev >= lev){
					word_lev = dictionary[i];
				}
				min_lev = Math.min(min_lev, lev);

			}
		}
		if (flag) {
			return word_lev;
		} 
		else {
			return word;
		}
	}
}
