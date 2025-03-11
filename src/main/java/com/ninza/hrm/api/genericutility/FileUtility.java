package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility
{
	public String getDataFromPropertiesFile(String key) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./EnvironmentData/configData.properties");
		Properties p=new Properties();
		p.load(fis);
		String data=p.getProperty(key);
		return data;
	}
}
