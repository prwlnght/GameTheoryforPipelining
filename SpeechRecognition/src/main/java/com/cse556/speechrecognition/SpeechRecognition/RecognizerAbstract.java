/**
 * Define Methods for Demo Class 
 */

package com.cse556.speechrecognition.SpeechRecognition;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * @author garre_000
 * 
 */
public abstract class RecognizerAbstract {

	protected String testFile;
	protected Configuration config;
	protected StreamSpeechRecognizer recognizer;

	/**
	 * Default Constructor
	 */
	public RecognizerAbstract(String testFile) {
		this.testFile = testFile;
		this.config = new Configuration();
		this.establishConfiguration();
		this.establishStreamSpeechRecognizer();
	}

	/**
	 * Set configuration of speech recognizer model
	 */
	private void establishConfiguration() {
		this.config = new Configuration();

		this.config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		this.config.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		this.config.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
	};

	/**
	 * Set speech recognizer of speech recognizer model
	 */
	private void establishStreamSpeechRecognizer() {
		 File file;
	     InputStream stream;
	     
	     try {
	    	 this.recognizer = new StreamSpeechRecognizer(this.config);
			 file = new File(this.testFile);
		     stream = new FileInputStream(file);
		     this.recognizer.startRecognition(stream);
	     } catch(FileNotFoundException e) {
	    	System.out.println("File " + this.testFile + " does not exist."); 
	    	System.exit(-1);
	     } catch (Exception e) {
	    	e.getStackTrace();
	    	System.exit(-1);
	     }		
	};
}
