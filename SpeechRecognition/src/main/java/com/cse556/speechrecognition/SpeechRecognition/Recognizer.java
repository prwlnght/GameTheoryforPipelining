/**
 * Class that demonstrates usage of Sphinx4
 */

package com.cse556.speechrecognition.SpeechRecognition;

import edu.cmu.sphinx.api.SpeechResult;
import java.util.ArrayList;

/**
 * @author garre_000
 */
public class Recognizer extends RecognizerAbstract {
	

	/**
	 * Default Constructor
	 */
	public Recognizer(String testFile) {
		super(testFile);
	}
	private ArrayList<String> deleteEmptyEntries(ArrayList<String> sentences) {
		ArrayList<String> nonEmptySentences = new ArrayList<String>();
		for (String sentence : sentences) {
			if (sentence == null  || sentence.equals("")) {
				// pass
			} else {
				nonEmptySentences.add(sentence);
			}
		}
		return nonEmptySentences;
	}
	
	public ArrayList<String> recognizeVoice() {
		SpeechResult result;
		ArrayList<String> possibleSentences;
		
		possibleSentences = new ArrayList<String>();
		while ((result = this.recognizer.getResult()) != null) {
			possibleSentences.add(result.getHypothesis());
		}
		this.recognizer.stopRecognition();
		possibleSentences = deleteEmptyEntries(possibleSentences);
		for (String sentence: possibleSentences) {
			System.out.format("Hypothesis: %s\n", sentence);
		}
		return possibleSentences;
	};
}
