package utils;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;

public class CommonUtils {

    RequestSpecification reqSpec;
    HttpOperation method;
    String url;
    Response resp;

    public void init(String url, HttpOperation method) {
        this.url = url;
        this.method = method;
        reqSpec = given();
    }

    public void initBase(String baseConst) {
        try {
            RestAssured.baseURI = loadProperties(baseConst);
        } catch (Exception e) {
            e.printStackTrace();
        }
        reqSpec = RestAssured.given();
    }

    public void setHeader(String[][] head) {
        for (int row = 0; row < head.length; row++)
            for (int col = 0; col < head[row].length; col++)
                reqSpec.header(head[row][col], head[row][col + 1]);
    }

    public void setHeader(String head, String val) { reqSpec.header(head, val);}

    public void setBody(String body) { reqSpec.body(body); }

    public void setBody(Object body) { reqSpec.body(body); }
    public String callIt() {
        if (method.toString().equalsIgnoreCase("get")) {
            resp = reqSpec.get(url);
            return resp.asString();
        } else if (method.toString().equalsIgnoreCase("post")) {
            resp = reqSpec.post(url);
            return resp.asString();
        } else if (method.toString().equalsIgnoreCase("patch")) {
            resp = reqSpec.patch(url);
            return resp.asString();
        } else if (method.toString().equalsIgnoreCase("put")) {
            resp = reqSpec.put(url);
            return resp.asString();
        } else if (method.toString().equalsIgnoreCase("delete")) {
            resp = reqSpec.delete(url);
            return resp.asString();
        }
        return "invalid method set for API";
    }

    public void assertIt(String key, Object val, ValidatorOperation operation) {

        switch (operation.toString()) {
            case "EQUALS":
                resp.then().body(key, equalTo(val));
                break;

            case "KEY_PRESENTS":
                resp.then().body(key, hasKey(key));
                break;

            case "HAS_ALL":
                break;

            case "NOT_EQUALS":
                resp.then().body(key, not(equalTo(val)));
                break;

            case "EMPTY":
                resp.then().body(key, empty());
                break;

            case "NOT_EMPTY":
                resp.then().body(key, not(emptyArray()));
                break;

            case "NOT_NULL":
                resp.then().body(key, notNullValue());
                break;

            case "HAS_STRING":
                resp.then().body(key, containsString((String)val));
                break;

            case "SIZE":
                resp.then().body(key, hasSize((int)val));
                break;
        }
    }

    public void assertIt(List<List<Object>> data) {
        for (List<Object> li : data) {
            switch (((ValidatorOperation) li.get(2)).toString()) {
                case "EQUALS":
                    resp.then().body(((String) li.get(0)), equalTo((String) li.get(1)));
                    break;

                case "KEY_PRESENTS":
                    resp.then().body(((String) li.get(0)), hasKey((String) li.get(1)));
                    break;

                case "HAS_ALL":
                    break;
            }
        }
    }

    public void assertIt(int code) {
        resp.then().statusCode(code);
    }

    public String extractString(String path) { return resp.jsonPath().getString(path);}

    public int extractInt(String path) { return resp.jsonPath().getInt(path);}

    public List<?> extractList(String path) { return resp.jsonPath().getList(path);}

    public Object extractIt(String path) { return resp.jsonPath().get(path); }

    public String extractHeader(String header_name) { return resp.header(header_name);}

    public String getResponseString() { return resp.asString();}

    public void printResp() { resp.print();}

    public void assertIt(int code, int optionalCode) {
        resp.then().statusCode(anyOf(equalTo(code),equalTo(optionalCode)));
    }

    public int getStatusCode() { return resp.getStatusCode(); }

    public Headers getAllHeaders() {return resp.getHeaders();}

    public String loadProperties(String property) {

        Properties prop = new Properties();
        InputStream input;
        try {
            input = Files.newInputStream(Paths.get(System.getProperty("user.dir") + "/src/test/resources/config.properties"));
            // load a properties file
            prop.load(input);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prop.getProperty(property);
    }

    public static void assertAll(String input, String output) {
        try {
            JSONAssert.assertEquals(input, output, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void assertAll(String input, String output, String strict) {
        try {
            JSONAssert.assertEquals(input, output, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String input, String array, int pos, String key) throws JSONException {

        JSONObject inputJson = new JSONObject(input);
        JSONArray jsonArray = inputJson.getJSONArray(array);
        JSONObject job = jsonArray.getJSONObject(pos);
        String Key = job.get(key).toString();
        return Key;

    }

    public static String getValue(String input, String key) throws JSONException {

        JSONObject inputJson = new JSONObject(input);
        String value = inputJson.get(key).toString();
        return value;
    }
}
