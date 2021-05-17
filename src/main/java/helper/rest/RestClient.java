package helper.rest;

import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public abstract class RestClient {
    /*
     * Properties
     */
    /****************** url ******************/
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /****************** responseStr ******************/
    private String responseStr;

    public String getResponseStr() {
        return responseStr;
    }

    public void setResponseStr(String response) {
        this.responseStr = response;
    }


    /*
     * Overriding
     */
//    @Override
    public String readResponseAsString() {
        return getResponseStr();
    }


    /*
     * Helper Static Methods
     */
    public static String getStrOfResponse(HttpResponse res) throws Exception {
        BufferedReader br = getBufferOfRes(res);

        StringBuffer result = new StringBuffer();

        String line = "";
        while ((line = br.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }

    public static BufferedReader getBufferOfRes(HttpResponse res) throws Exception {
        return new BufferedReader(
                new InputStreamReader(
                        res.getEntity().getContent()));
    }


    public abstract void addHeader(String key, String value);

    public abstract void send() throws Exception;

    public abstract void addParameter(String key, String value) throws Exception;

    public abstract void addBasicAuth(String username, String password) throws Exception;
}
