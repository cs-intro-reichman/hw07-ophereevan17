

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		hashTag = hashTag.toLowerCase();
		String []dictionary = readDictionary("dictionary.txt");
		System.out.println (existInDictionary(hashTag, dictionary));
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for (int i = 0 ; i < 3000 ; i++){
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		for ( int i = 0; i < dictionary.length; i++){
			if (word.equals(dictionary[i])){
				return true;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }

        int N = hashtag.length();
        for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 3000; j++){

				if(existInDictionary(hashtag.substring(0, i), dictionary)) {
					System.out.println(hashtag.substring(0, i));

					hashtag = hashtag.replace(hashtag.substring(0, i), "");
					// Recursive call
					breakHashTag(hashtag, dictionary);
                
					// Exits the inner loop after finding a match
					return;
				}
			}
		}
	}
}
