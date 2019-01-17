package de.unidue.langtech.web1.run;

import java.io.IOException;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.provider.FrequencyCountProvider;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class ResourceUser extends JCasAnnotator_ImplBase
{
    final static String MODEL_KEY = "FrequencyProvider";
    @ExternalResource(key = MODEL_KEY)
    private FrequencyCountProvider model;

    @Override
    public void process(JCas aJCas) throws AnalysisEngineProcessException
    {
        for(Token t : JCasUtil.select(aJCas, Token.class)) {
            String text = t.getCoveredText();
            try {
                long frequency = model.getFrequency(text);
                System.err.println("Frequency of ["+text+"] is ["+frequency+"]");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
  

}
