package de.unidue.langtech.web1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;

import de.tudarmstadt.ukp.dkpro.core.api.parameter.ComponentParameters;

public class OneLinePerCasReader
    extends JCasCollectionReader_ImplBase
{
    public static final String PARAM_SOURCE_LOCATION = ComponentParameters.PARAM_SOURCE_LOCATION;
    @ConfigurationParameter(name = PARAM_SOURCE_LOCATION, mandatory = false)
    private String sourceLocation;

    BufferedReader bufferedReader;
    String next = null;

    public void initialize(final UimaContext context)
        throws ResourceInitializationException
    {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(
                    sourceLocation))));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext()
        throws IOException, CollectionException
    {

        next = bufferedReader.readLine();

        return next != null ? true : false;
    }

    @Override
    public Progress[] getProgress()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void getNext(JCas aJCas)
        throws IOException, CollectionException
    {
        aJCas.setDocumentText(next);
        aJCas.setDocumentLanguage("x-unspecified");
    }

}
