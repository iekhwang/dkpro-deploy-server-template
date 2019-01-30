package com;
// @DKPRO CLI import code generation is starting this line


import org.apache.uima.cas.CAS;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.jcas.JCas;

import org.apache.uima.util.XMLSerializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
// TODO: Switch to post request 
@RestController
public class DKProEndpoint {

    @RequestMapping(value = "/{text}")
    public static String analyzeText(@PathVariable(value = "text") String textToAnalyze) throws Exception {

        try {
            // @DKPRO CLI analysis code generation is starting this line


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
