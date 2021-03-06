package net.virtualviking.dunebuggy.vro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VROHelper {
	public static Map<String, Object> simplifyAttributes(Map<String, Object> response) {
		return simplifyAttributes(response, "attributes");
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> simplifyAttributes(Map<String, Object> response, String attributeSection) {
		for(Map.Entry<String, Object> node : response.entrySet()) {
			String tag = node.getKey();
			Object value = node.getValue();
			if(tag.equals(attributeSection)) {
				Map<String, Object> attrMap = new HashMap<>();
				for(Map<String, Object> attr : (List<Map<String, Object>>) value) {
					attrMap.put((String) attr.get("name"), attr.get("value"));
				}
				node.setValue(attrMap);
			}
			else
			{
				if(value instanceof List)
					simplifyAttributes((List<Map<String, Object>>) value);
				else if(value instanceof Map)
					simplifyAttributes((Map<String, Object>) value);
			}
		}
		return response;
	}
	
	public static List<Map<String, Object>> simplifyAttributes(List<Map<String, Object>> response) {
		for(Map<String, Object> node : response) {
			simplifyAttributes(node);
		}
		return response;
	}
}
