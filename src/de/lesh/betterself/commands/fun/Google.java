/**
 * 		This work is licensed under a Creative Commons 
 *			 Attribution-NonCommercial-ShareAlike 
 *				4.0 International License.
 */
package de.lesh.betterself.commands.fun;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Lukas
 */
public class Google {

	
	
    public ArrayList<String> getDataFromGoogle(String query) {
        String request;
        try {
            request = "https://www.google.com/search?q=" + URLEncoder.encode(query, "UTF-8") + "&num=20";
        } catch (UnsupportedEncodingException ex) {
            System.err.println(ex);
            return null;
        }
        //System.out.println("Sending request..." + request);
        ArrayList<String> result;
        try {

            // need http protocol
            org.jsoup.nodes.Document doc = Jsoup
                    .connect(request)
                    .userAgent(
                      "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
                    .timeout(5000).get();

            // get all links
            org.jsoup.select.Elements links = doc.select("a[href]");
            result = new ArrayList<>();
            links.stream().map((link) -> link.attr("href")).filter((temp) -> (temp.startsWith("/url?q="))).forEach((temp) -> {
                try {
                    //result.add(temp.substring(7,temp.indexOf("&sa="))+"\n");
                    String rslt = URLDecoder.decode(temp.substring(7,temp.indexOf("&sa=")),"UTF-8");
                    if(!rslt.contains("/settings/ads/preferences") && !rslt.startsWith("http://webcache.googleusercontent.com"))
                        result.add(rslt);
                } catch (UnsupportedEncodingException ex) {
                }
            });
        } catch (IOException e) {
            LoggerFactory.getLogger("Google").error("Search failure: "+e.toString());
            return null;
        }
        return result;
    }
}
