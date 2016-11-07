# Purpose
This repo is designed for the Arizona State University CSE 556: Game Theory with Applications to Networks class project by Garrett Gutierrez, Prajwal Paudal, and Ismael Mercado.
The purpose of the project is to help resolve the pipelining problem in Natural Language Processing using Game Theory Concepts. We intend to make the different steps of Natural Language Parsing into separate models called players: acoustic parsing, semantic parsing, and pragmatics.
We hope to show that Nash Equilibrium between these different models results with a reasonably accurate, semantically correct sentence from a piece of audio.

# Acoustic Parser
The acoustic parser for this project is Sphinx4.

* Documentation: http://cmusphinx.sourceforge.net/
* Source Code: http://cmusphinx.sourceforge.net/wiki/download

# Syntactic and Semantic Parser
This project uses a statistical parser rather than a grammatical parsers so that there is more flexibility in the audio input. If the audio results in unfamiliar words, a statistical parser will still give a result while other parsers may not since it is not a recognized word. The statistical parser used in this project is the Stanford lexical parser.

* Documentation: http://nlp.stanford.edu/software/lex-parser.shtml
* Download: http://nlp.stanford.edu/software/lex-parser.shtml#Download
* Online Demo: http://nlp.stanford.edu:8080/parser/

# How to run
1. Import as a maven project
    1.1.  Open eclipse.
    1.2.  Click File > Import.
    1.3.  Type Maven in the search box under Select an import source:
    1.4.  Select Existing Maven Projects.
    1.5.  Click Next.
    1.6.  Click Browse and select the folder that is the root of the Maven project (probably contains the pom.xml file)
    1.7.  Click OK.
2. Right click SpeechRecognition/src/main/java/com.cse556.speechrecognition.SpeechRecognition/App.java
3. Click run as configurations
4. Click Arguments tab
5. Type path of the wav file such as C:\Users\garre_000\Documents\Eclipse\SpeechRecognition\test2.wav
6. Click Run
