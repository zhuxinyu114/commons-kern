package com.snowstone.commons.kern.apiext.collection;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.alibaba.fastjson.JSON;
import com.snowstone.commons.kern.apiext.io.IOUtil;

public abstract class ByteUtils {
	/**
	 * 对象转Byte数组
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static byte[] objectToBytes(Object obj) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream sOut = new ObjectOutputStream(out);
		sOut.writeObject(obj);
		sOut.flush();
		byte[] bytes = out.toByteArray();
		return bytes;
	}

	/**
	 * 字节数组转对象
	 * 
	 * @param content
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	public static Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
		// byte转object
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ObjectInputStream sIn = new ObjectInputStream(in);
		return sIn.readObject();

	}

	/**
	 * 该流内文件可能是音频文件或图片
	 * 获得指定文件的byte数组
	 */
	public static byte[] getBytes(InputStream fis) {
		byte[] buffer = null;
		try {

			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	/**
	 * 根据byte数组，生成文件
	 * 
	 * @return
	 */
	public static void toFile(byte[] bfile,File file) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		try {

			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}


}
