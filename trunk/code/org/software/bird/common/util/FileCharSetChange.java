package org.software.bird.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

/**
 * 转码
 * @author ISSuser
 *
 */
public class FileCharSetChange {

	public static void main(String[] args) throws IOException, Exception {
		
		String path = "C:\\Documents and Settings\\cyyan\\桌面\\bird-x\\bird-x";
		String oldCharSet = "gbk";
		String newCharSet = "utf-8";
		
		System.err.println("begin");
		changeChangeCode(new File(path), oldCharSet, newCharSet);
		System.out.println("over");
	}
	
	static void changeChangeCode(File oldFile, String oldCharSet, String newCharSet) throws Exception, IOException {
		if (oldFile.isDirectory()) {
			File[] subFile = oldFile.listFiles();
			for (int i = 0; i < subFile.length; i++) {
				changeChangeCode(subFile[i], oldCharSet, newCharSet);
			}
		} else {
			InputStream in = new FileInputStream(oldFile);
			OutputStream out = new FileOutputStream(oldFile);
			
			String data = IOUtils.toString(in, oldCharSet);
			IOUtils.closeQuietly(in);
			
			IOUtils.write(data, out, newCharSet);
			IOUtils.closeQuietly(out);
		}
	}
}
