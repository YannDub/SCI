package fr.sma.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	private Properties prop = new Properties();
	
	
	public PropertiesReader(){
		try {
			InputStream input = new FileInputStream("../../../../Utils/param.properties");
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(String key) {
		return this.prop.getProperty(key);
	}

}
