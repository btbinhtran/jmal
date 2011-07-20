package com.jmal.util;

public class StringUtils {
	public static String unescapeXML(String xmlStr) {
		xmlStr = xmlStr.replaceAll("&lt;", "<");
		xmlStr = xmlStr.replaceAll("&gt;", ">");
		xmlStr = xmlStr.replaceAll("&quot;", "\"");
		xmlStr = xmlStr.replaceAll("&#039;", "'");
		xmlStr = xmlStr.replaceAll("&amp;", "&");

		return xmlStr;
	}

	public static String cleanWeirdMALSynopsisXML(String xmlStr) {
		xmlStr = xmlStr.replaceAll("&lt;", "<");
		xmlStr = xmlStr.replaceAll("&gt;", ">");
		xmlStr = xmlStr.replaceAll("&amp;quot;", "\"");
		xmlStr = xmlStr.replaceAll("&amp;#039;", "'");
		xmlStr = xmlStr.replaceAll("<br />", "");

		xmlStr = xmlStr.trim();
		xmlStr = xmlStr.replaceAll("\n\n", " ");
		xmlStr = xmlStr.replaceAll("\n", " ");

		return xmlStr;
	}
	
	public static String cleanWeirdMALXML(String xmlStr) {
		xmlStr = xmlStr.replaceAll("&Acirc;", "&acirc;");
		
		return xmlStr;
	}
}
