package fr.sma.core.utils;

public final class Properties {
	private static PropertiesReader pr = new PropertiesReader();
	
	public static String getProperty(String key) {
		return pr.getProperty(key);
	}
}
