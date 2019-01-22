package com;
// @DKPRO CLI import code generation is starting this line


import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.json.JsonCasSerializer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;
// TODO: Switch to post request 
@RestController
public class DKProEndpoint {

    @RequestMapping(value = "/{text}")
    public static String analyzeText(@PathVariable(value = "text") String textToAnalyze) throws Exception {

        try {
            // @DKPRO CLI analysis code generation is starting this line

            
            return JCasToString(result);


        } catch (Exception e) {

            e.printStackTrace();
            return "Hat nicht funktionukkelt";
        }


    }

    private static String JCasToString(JCas result) throws Exception {

        try {
            System.out.println("in Jcas to string");
            // set up CAS serializer
            JsonCasSerializer jcs = new JsonCasSerializer();
            jcs.setPrettyPrint(true); // do some configuration

            // set up string writer
            StringWriter sw = new StringWriter();

            // create cas from jcas (result)
            CAS resultCas = result.getCas();
            jcs.serialize(resultCas, sw);

            String resultJSONString = sw.toString();
            System.out.println(resultJSONString);
            return resultJSONString;


        } catch (Exception e) {

            e.printStackTrace();
            return "JCas to String hat nicht funktioniert";
        }

    }


}
