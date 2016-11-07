package com.cse556.speechrecognition.SpeechRecognition;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.trees.Tree;

public class Parser {
	private final static String PCG_MODEL = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";

	private final TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer
			.factory(new CoreLabelTokenFactory(), "invertible=true");

	private final LexicalizedParser parser = LexicalizedParser
			.loadModel(PCG_MODEL);

	/**
	 * @param sentences
	 *            A set of key value pairs of strings and their semantic socre
	 * @return Map.Entry the maximum key value pair
	 */
	public Map.Entry<String, Double> hashMapMax(
			HashMap<String, Double> sentences) {
		Set<Entry<String, Double>> setOfSentences = sentences.entrySet();
		Iterator<Entry<String, Double>> iterator = setOfSentences.iterator();
		Double max = new Double(Double.MAX_VALUE * -1.0);
		Map.Entry<String, Double> entry = null;
		Map.Entry<String, Double> maxEntry = null;

		while (iterator.hasNext()) {
			entry = (Map.Entry<String, Double>) iterator.next();
			if (Double.compare(max, (Double) entry.getValue()) < 0) {
				maxEntry = entry;
				max = (Double) maxEntry.getValue();
			}
		}
		return maxEntry;
	}

	public Tree parse(String str) {
		List<CoreLabel> tokens = tokenize(str);
		Tree tree = parser.apply(tokens);
		return tree;
	}

	private List<CoreLabel> tokenize(String str) {
		Tokenizer<CoreLabel> tokenizer = tokenizerFactory
				.getTokenizer(new StringReader(str));
		return tokenizer.tokenize();
	}

	public HashMap<String, Double> getSemanticSentences(
		ArrayList<String> audioSentences) {
		HashMap<String, Double> sentences;
		// List<Tree> leaves = null;
		Parser parser = null;
		Tree tree = null;

		try {
			sentences = new HashMap<String, Double>();
			for (String line : audioSentences) {
				parser = new Parser();
				tree = parser.parse(line);
				// leaves = tree.getLeaves();
				// for (Tree leaf : leaves) {
				// }
				// System.out.println("\nScore " +
				// Double.toString(tree.score()));
				sentences.put(new String(line), new Double(tree.score()));
			}
			return sentences;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
