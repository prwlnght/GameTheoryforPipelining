/**
 *  Driver of Application
 */

package com.cse556.speechrecognition.SpeechRecognition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author garre_000
 */
public class App {
	
	/**
	 * @param args Array of command line arguments
	 */
	public static void main(String[] args) {
		ArrayList<String> audioSentences = null;
		Recognizer demo = null;
		HashMap<String, Double> sentences = null;
		Map.Entry<String, Double> choice = null;
		Parser parser = null;
		String fileName = null;
		
		if (args.length != 1) {
			System.out.println("Argument Error: Enter only one filename as command line argument.");
			System.exit(-1);
		}
		parser = new Parser();
		fileName = args[0];
		demo = new Recognizer(fileName);
		System.out.println("Executing Demo of Sphinx");
		audioSentences = demo.recognizeVoice();
		if (audioSentences != null ) {
			sentences = parser.getSemanticSentences(audioSentences);
		} else {
			System.out.println("The audio parser has no sentences.");
			System.exit(-1);
		}
		
		if (sentences != null) {
			choice = (Map.Entry<String, Double>)parser.hashMapMax(sentences);
		} else {
			System.out.println("The semantic parser has no sentences.");
			System.exit(-1);
		}
		
        if (choice == null) {
        	System.out.println("\nNo max Found");
        } else {
        	System.out.println("\nMax Score: " + Double.toString((Double)choice.getValue()));
        	System.out.println("Best Sentence: " + (String)choice.getKey());
        }
		
	}
}
