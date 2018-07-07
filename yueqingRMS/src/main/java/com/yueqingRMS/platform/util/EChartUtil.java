//package com.yueqingRMS.util;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//public class EChartUtil {
//
//	/**
//	 * 返回ECharts的option=JSON数据（折线图，柱状图）
//	 * 
//	 * @param title
//	 *            图名称
//	 * @param type
//	 *            line折线图，bar柱状图
//	 * @param yLabel
//	 *            y轴后缀 纯数字就写""
//	 * @param yNames
//	 *            y轴名称 （最高气温，最低气温）
//	 * @param xNames
//	 *            x轴名称 （周一，周二，周三，周四，周五，周六，周日）
//	 * @param data
//	 *            key:y轴名称，value：数据（1,2,3,4）可以为Integer或者Double等
//	 * @return
//	 */
//	public static JSONObject getEChartOption(String title, String type, String yLabel, String[] yNames, String[] xNames, Map<String, List> data) {
//		JSONObject option = new JSONObject();
//
//		// title
//		JSONObject titleObject = new JSONObject();
//		titleObject.put("text", title);
//		option.put("title", titleObject);
//
//		// tooltip
//		JSONObject tooltipObject = new JSONObject();
//		tooltipObject.put("trigger", "axis");
//		option.put("tooltip", tooltipObject);
//
//		// legend
//		JSONObject legend = new JSONObject();
//		JSONArray yNamesArray = new JSONArray(yNames);
//		legend.put("data", yNamesArray);
//		option.put("legend", legend);
//
//		// toolbox
//		String toolbox = "{\"show\":true,\"feature\":{\"mark\":{\"show\":true}," + "\"dataView\":{\"show\":true,\"readOnly\":false},\"magicType\":"
//				+ "{\"show\":true,\"type\":[\"line\",\"bar\"]},\"restore\":" + "{\"show\":true},\"saveAsImage\":{\"show\":true}}}";
//		JSONObject toolboxObject = new JSONObject(toolbox);
//		option.put("toolbox", toolboxObject);
//
//		// calculable
//		option.put("calculable", true);
//
//		// xAxis
//		JSONArray xAxisObject = new JSONArray();
//		JSONObject value = new JSONObject();
//		value.put("type", "category");
//		value.put("boundaryGap", false);
//		value.put("data", new JSONArray(xNames));
//		xAxisObject.put(value);
//		option.put("xAxis", xAxisObject);
//
//		// yAxis
//		JSONArray yAxisObject = new JSONArray();
//		JSONObject y = new JSONObject();
//		y.put("type", "value");
//		JSONObject axisLabel = new JSONObject();
//		axisLabel.put("formatter", "{value} " + yLabel);
//		y.put("axisLabel", axisLabel);
//		yAxisObject.put(y);
//		option.put("yAxis", yAxisObject);
//
//		// series
//		JSONArray seriesArray = new JSONArray();
//		for (String name : data.keySet()) {
//			JSONObject object = new JSONObject();
//			object.put("name", name);
//			object.put("type", type);
//			object.put("data", new JSONArray(data.get(name)));
//			object.put("markPoint", new JSONObject("{\"data\":[{\"type\":\"max\",\"name\":\"最大值\"}," + "{\"type\":\"min\",\"name\":\"最小值\"}]}"));
//			object.put("markLine", new JSONObject("{\"data\":[{\"type\":\"average\",\"name\":\"平均值\"}]}"));
//			seriesArray.put(object);
//		}
//		option.put("series", seriesArray);
//
//		return option;
//	}
//
//	/**
//	 * 返回ECharts的option=JSON数据(饼状图)
//	 * 
//	 * @param title
//	 *            图名称
//	 * @param xNames
//	 *            x轴名称（最高温度，最低温度）
//	 * @param data
//	 *            每条（{"name":"最高气温","value":100}），总共List
//	 * @return
//	 */
//	public static JSONObject getEChartOption(String title, String[] xNames, List<Map> data) {
//		JSONObject option = new JSONObject();
//
//		// title
//		JSONObject titleObject = new JSONObject();
//		titleObject.put("text", title);
//		titleObject.put("x", "center");
//		option.put("title", titleObject);
//
//		// tooltip
//		JSONObject tooltipObject = new JSONObject();
//		tooltipObject.put("trigger", "item");
//		tooltipObject.put("formatter", "{a} <br/>{b} : {c} ({d}%)");
//		option.put("tooltip", tooltipObject);
//
//		// legend
//		JSONObject legend = new JSONObject();
//		legend.put("orient", "vertical");
//		legend.put("x", "left");
//		JSONArray xNamesArray = new JSONArray(xNames);
//		legend.put("data", xNamesArray);
//		option.put("legend", legend);
//		;
//
//		// toolbox
//		String toolbox = "{\"show\":true,\"feature\":{\"mark\":{\"show\":true}," + "\"dataView\":{\"show\":true,\"readOnly\":false},\"magicType\":{"
//				+ "\"show\":true,\"type\":[\"pie\",\"funnel\"],\"option\":{" + "\"funnel\":{\"x\":\"25%\",\"width\":\"50%\",\"funnelAlign\":\"left\",\"max\""
//				+ ":1548}}},\"restore\":{\"show\":true},\"saveAsImage\":{\"show\":true}}}";
//		JSONObject toolboxObject = new JSONObject(toolbox);
//		option.put("toolbox", toolboxObject);
//
//		// calculable
//		option.put("calculable", true);
//
//		// series
//		JSONArray seriesArray = new JSONArray();
//		JSONObject object = new JSONObject();
//		object.put("name", "访问来源");
//		object.put("type", "pie");
//		object.put("radius", "55%");
//		object.put("data", new JSONArray(data));
//		seriesArray.put(object);
//		option.put("series", seriesArray);
//
//		return option;
//	}
//
//	public static void main(String[] args) {
//		String title = "折线图名称";
//		String[] yName = new String[] { "最高气温", "最低气温" };
//		String[] xName = new String[] { "周一", "周二", "周三", "周四", "周五", "周六", "周日" };
//		List<Integer> list = new ArrayList<Integer>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		list.add(5);
//		list.add(6);
//		list.add(7);
//		Map<String, List> map = new HashMap<String, List>();
//		map.put(yName[0], list);
//
//		List<Integer> list1 = new ArrayList<Integer>();
//		list1.add(1);
//		list1.add(2);
//		list1.add(3);
//		list1.add(4);
//		list1.add(5);
//		list1.add(6);
//		list1.add(7);
//		map.put(yName[1], list1);
//
//		JSONObject o = getEChartOption(title, "line", "", yName, xName, map);
//
//		System.out.println(o.toString());
//
//		List<Map> data = new ArrayList<Map>();
//		for (int i = 0; i < 2; i++) {
//			Map m = new HashMap();
//			m.put("name", yName[i]);
//			m.put("value", 100);
//			data.add(m);
//		}
//		JSONObject oo = getEChartOption(title, yName, data);
//		System.out.println(oo.toString());
//	}
//}
