import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Irindul on 07/06/2017.
 */
public class CookieHandler {

    HashMap<String, String> cookies;

    public CookieHandler() {
        cookies = new HashMap<>();
    }

    protected boolean header(String s){
        return "Cookie:".equals(s);
    }

    public void store(String header){
        StringTokenizer parse = new StringTokenizer(header);
        while (parse.hasMoreTokens()) {
            String s = parse.nextToken();
            if (header(s)) {
                String cookie = parse.nextToken();
                cookies.put(cookie.split("=")[0], cookie.split("=")[1]);
            }
        }
    }

    protected String type(){
        return "Set-Cookie: ";
    }

    public String parse(){
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = cookies.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> entry = it.next();
            sb.append(type())
                    .append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());
            if(it.hasNext()){
                sb.append("\n");
            }

        }

        return sb.toString();
    }

    public HashMap<String, String> getCookies() {
        return cookies;
    }

    public void addCookie(String key, String value){
        cookies.put(key, value);
    }
}
