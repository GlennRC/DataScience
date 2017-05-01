package com.mycompany.app;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.sentiment.*;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.*;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.*;

public class App {
    public static void main(String[] args) {
        Annotation document = new Annotation("I liked the first movie.  I hated the second movie.");
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,sentiment");
        props.setProperty("parse.binaryTrees","true");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        /**
        pipeline.annotate(document);
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            System.out.println("---");
            System.out.println(sentence.get(CoreAnnotations.TextAnnotation.class));
            System.out.println(sentence.get(SentimentCoreAnnotations.SentimentClass.class));
        }
        **/
        JSONParser parser = new JSONParser();
        String filePath = "/Users/glenncontreras/Desktop/DS_TERM/702_webhose-2017-03_20170405063221/";

        try {
            for(int i=0; i<100; i++) {
                String index = Integer.toString(i);
                String fileName = "blogs_"+ ("00000000" + index).substring(index.length()) + ".json";
                System.out.println(fileName);

            }
//            JSONObject obj = (JSONObject) parser.parse(new FileReader(fileName));
//            String str = (String) obj.get("text");
//            System.out.println(str);
//            System.out.println("---");
//            System.out.println("sentiment: " + calculateSentiment(str, pipeline));
        }
        catch(Exception e){
            System.out.println(e.toString());
        }

    }



    public static float calculateSentiment(String text, StanfordCoreNLP pipeline) {

        float mainSentiment = 0;

        int longest = 0;
        Annotation annotation = pipeline.process(text);
        for (CoreMap sentence : annotation
                .get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
            String partText = sentence.toString();
            if (partText.length() > longest) {
                mainSentiment = sentiment;
                longest = partText.length();
            }

        }

        return mainSentiment;

    }

}


