package com;
// @DKPRO CLI import code generation is starting this line

import de.unidue.langtech.web1.CreateIndexNews;


import org.apache.uima.cas.CAS;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.jcas.JCas;

import org.apache.uima.util.XMLSerializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
@RestController
public class DKProEndpoint {

    @RequestMapping(
            value = "/process", method = RequestMethod.POST, consumes = "text/plain")
    public static String analyzeText(@RequestBody String jsonString) throws Exception {

        try {
            // @DKPRO CLI analysis code generation is starting this line

			CreateIndexNews analysis = new CreateIndexNews();
			JCas result = analysis.run(jsonString);


            return JCasToXMIString(result);


        } catch (Exception e) {

            e.printStackTrace();
            return "Hat nicht funktionukkelt";
        }


    }

    private static String JCasToXMIString(JCas result) throws Exception {

        ByteArrayOutputStream outStream = null;

        try {
            // create out stream
            outStream = new ByteArrayOutputStream();
            XMLSerializer xmlSer = new XMLSerializer(outStream, false);
            // create cas from jcas (result)
            CAS resultCas = result.getCas();

            // get current cas type system
            TypeSystem resultType = resultCas.getTypeSystem();

            // set up CAS serializer with type system
            XmiCasSerializer xmi_cas = new XmiCasSerializer(resultType);


            xmi_cas.serialize(resultCas, xmlSer.getContentHandler());

            String resultXMLString = outStream.toString();

            return resultXMLString;


        } catch (Exception e) {

            e.printStackTrace();
            return "JCas to String hat nicht funktioniert";
        } finally {

            if (outStream != null) {
                outStream.close();
            }
        }

    }

}

