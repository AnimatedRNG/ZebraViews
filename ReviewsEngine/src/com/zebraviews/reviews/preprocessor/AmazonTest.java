package com.zebraviews.reviews.preprocessor;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.zebraviews.reviews.SignedRequestsHelper;

public class AmazonTest {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws IllegalArgumentException 
	 * @throws InvalidKeyException 
	 */
	public static void main(String[] args) throws InvalidKeyException, IllegalArgumentException, UnsupportedEncodingException, NoSuchAlgorithmException {
		SignedRequestsHelper helper = SignedRequestsHelper.getInstance("ecs.amazonaws.com", "AKIAJYAME7RKTOR2CI5Q", "YoTVtzH1OSV2/V+sEXrX6FJQ7Isl7npmhCgFHUG9");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("Service", "AWSECommerceService");
        params.put("Version", "2013-8-2");
        params.put("Operation", "ItemLookup");
        params.put("IdType", "UPC");
        params.put("SearchIndex", "All");
        params.put("ItemId", "635753490879");
        params.put("ResponseGroup", "Large");
        
        String url = helper.sign(params);
        
        try {
            Document response = getResponse(url);
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            Properties props = new Properties();
            props.put(OutputKeys.INDENT, "yes");
            trans.setOutputProperties(props);
            StreamResult res = new StreamResult(new StringWriter());
            DOMSource src = new DOMSource(response);
            trans.transform(src, res);
            String toString = res.getWriter().toString();
            System.out.println(toString);
        } catch (Exception ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	private static Document getResponse(String url) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(url);
        return doc;
    }

}
