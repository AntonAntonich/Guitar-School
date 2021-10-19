package com.anton.gs.model.util;

/**
 * The interface Url modifier.
 */
public interface UrlModifier {
   /**
    * Modify url string.
    * cut contrxt path from request url of page where request came from
    * @param url the url
    * @return the string
    */
   String modifyUrl(String url);
}
