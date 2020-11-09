package orbartal.spring.flume.tools.factory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	
	public static JSONArray buildJson(String data) throws JSONException {
		JSONObject headers = new JSONObject();
		JSONObject jo = new JSONObject();
		jo.put("headers", headers);
		jo.put("body", data);
		JSONArray ja = new JSONArray();
		ja.put(jo);
		return ja;
	}
	

}
