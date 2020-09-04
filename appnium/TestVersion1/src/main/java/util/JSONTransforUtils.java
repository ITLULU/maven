package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import pojo.User;


/**
 * json转换工具
 * 
 * @author Administrator
 *
 */
public class JSONTransforUtils {
	public static String jsonmap = "{\"001\":{\"name\":\"xiaohong\",\"password\":\"654321\"},\"002\":[{\"$ref\":\"$.001\"},{\"name\":\"xixi\",\"password\":\"789\"}]}";
    public static String jsonuser = "{\"name\":\"xiaohong\",\"password\":\"654321\"}";
    public static String jsonlist = "[{\"name\":\"xiaohong\",\"\":\"654321\"},{\"name\":\"xixi\",\"password\":\"789\"}]";

	public static Logger log = Logger.getLogger(JSONTransforUtils.class.getName());

	/**
	 * string转json
	 * 
	 * @param jsonString
	 * @return
	 */
	public static JSONObject StringTransForjSON(String jsonString) {
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		return jsonObject;
	}

	/**
	 * list转字符串
	 * 
	 * @param lists
	 * @return
	 */
	public static String listTransforTOJSON(List<String> lists) {

		String str = JSON.toJSON(lists).toString();
		return str;
	}

	/**
	 * map转json
	 * 
	 * @param map
	 * @return
	 */
	public static JSONObject mapTransTOJSON(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json;
	}
    
   
	public static User JAVAObjectTransToJSON(String str) {
		User user=JSONObject.parseObject(str,User.class);
		return user;
	}
	
	public static List<Object> getListFromJSON(String str){
		List<Object> list1 = JSONObject.parseObject(str, List.class);
		return list1;
	}
	
	public static Map<String,Object> getMapFromJSON(String str){
		Map<String,Object>  map1 = JSONObject.parseObject(jsonmap, Map.class);
		 Iterator iterator = map1.entrySet().iterator();
	        while (iterator.hasNext()) {
	            Map.Entry entry= (Map.Entry) iterator.next();
	            System.out.println("key :"+entry.getKey()+"   value: " + entry.getValue());
	        }

		return map1;
	}
	
	public static void main(String[] args) {
		
		 String jsonString= "{\"abc\":\"1\",\"hahah\":\"2\"}";
		 log.info("string转jsonObject:"+StringTransForjSON(jsonmap));
		 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "小组");
		map.put("age", 12);
		log.info("map转jsonString:" + mapTransTOJSON(map));
		
		
		log.info(JAVAObjectTransToJSON(jsonuser));
		log.info("list json转换"+getListFromJSON(jsonlist));
		log.info(getMapFromJSON(jsonmap));
	}

}
