package com.adii.matrix.multiplication.commons;

public class CommonUtil {
	
	public String getRealPath(String fileName) {
		String fileFullPath = getClass().getClassLoader().getResource(fileName).toString();
		String realPath = fileFullPath.substring("file:/".length(), fileFullPath.length());
		return realPath;
	}

}
