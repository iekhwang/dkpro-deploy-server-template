package de.unidue.langtech.web1;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import com.googlecode.jweb1t.JWeb1TIndexer;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.io.web1t.Web1TWriter;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import org.apache.uima.jcas.JCas;

import java.util.ArrayList;
import java.util.List;

public class CreateIndexNews
{
    public static void main(String[] args) throws Exception
    {
         CreateIndexNews cin = new CreateIndexNews();
         cin.run("jasbdjah ajsd bajhsd a jhasdbasjd ajs jashdab sd ad jasdh ajd", "de");
    }

    public JCas run(String text, String language) throws Exception
    {

        //CollectionReader reader = createReader(OneLinePerCasReader.class,
                //OneLinePerCasReader.PARAM_SOURCE_LOCATION, corpusPath);

        AnalysisEngine segmenter = createEngine(BreakIteratorSegmenter.class);

        AnalysisEngine ngramWriter = createEngine(Web1TWriter.class,
                Web1TWriter.PARAM_TARGET_LOCATION, "target/", Web1TWriter.PARAM_INPUT_TYPES,
                new String[] { Token.class.getName() }, Web1TWriter.PARAM_MIN_NGRAM_LENGTH, 1,
                Web1TWriter.PARAM_MAX_NGRAM_LENGTH, 3, Web1TWriter.PARAM_MIN_FREQUENCY, 1);

        List<AnalysisEngine> engines = new ArrayList<>();
        engines.add(segmenter);
        engines.add(ngramWriter);

        // JWeb1TIndexer indexCreator = new JWeb1TIndexer(outpcd .. utLocation, 3);
        JCas jcas = process(text, language, engines);

        return jcas;
    }

    private JCas process(String aText, String aLanguage, List<AnalysisEngine> engines) throws UIMAException {
        JCas jcas = JCasFactory.createText(aText, aLanguage);
        for (AnalysisEngine engine : engines)
            engine.process(jcas);
        return jcas;
    }

}
