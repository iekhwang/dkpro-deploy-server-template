package de.unidue.langtech.web1.run;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;
import static org.apache.uima.fit.factory.ExternalResourceFactory.createExternalResourceDescription;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.frequency.resources.Web1TFrequencyCountResource;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unidue.langtech.web1.CreateIndexNews;
import de.unidue.langtech.web1.OneLinePerCasReader;

public class Run
{
    public static void main(String [] args) throws Exception{
        
        String corpusPath = "src/main/resources/data.txt";
        String outputLocation = "target/";
        
        CreateIndexNews cin = new CreateIndexNews();
        cin.run("jasbdjah ajsd bajhsd a jhasdbasjd ajs jashdab sd ad jasdh ajd", "de");
        
        CollectionReader reader = createReader(
                OneLinePerCasReader.class,
                OneLinePerCasReader.PARAM_SOURCE_LOCATION, corpusPath
        );

        AnalysisEngineDescription segmenter = createEngineDescription(
                BreakIteratorSegmenter.class
        );

        AnalysisEngineDescription useExternalResourceForSomething = createEngineDescription(
                ResourceUser.class,
                ResourceUser.MODEL_KEY, createExternalResourceDescription(
                        Web1TFrequencyCountResource.class,
                        Web1TFrequencyCountResource.PARAM_INDEX_PATH, outputLocation,
                        Web1TFrequencyCountResource.PARAM_LANGUAGE, "en",
                        Web1TFrequencyCountResource.PARAM_MIN_NGRAM_LEVEL, "1",
                        Web1TFrequencyCountResource.PARAM_MAX_NGRAM_LEVEL, "2"));
        
        SimplePipeline.runPipeline(
                reader,
                segmenter,
                useExternalResourceForSomething
        );
    }

}
