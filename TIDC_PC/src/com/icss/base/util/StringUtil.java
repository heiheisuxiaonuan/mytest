package com.icss.base.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import com.google.gson.Gson;

/**
 * 字符串常用工具类
 *
 * @author wangyu
 * @version 1.0
 * @since Jun 4, 2009
 * @see
 * @history Jun 4, 2009 wangyu 创建StringUtil类
 */
public class StringUtil {

	/**
	 * 8859 -> UTF-8
	 * @param String oldValue
	 * @return String
	 */
	public static String encode(String oldValue) {
		String newValue = new String();
		try {
			newValue = new String(oldValue.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Catch unsupported encoding exception.");
			e.printStackTrace();
		}
		return newValue;
	}

	/**
	 * UTF-8 -> 8859
	 * @param String oldValue
	 * @return String
	 */
	public static String decode(String oldValue) {
		String newValue = new String();
		try {
			newValue = new String(oldValue.getBytes("UTF-8"), "8859_1");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Catch unsupported encoding exception.");
			e.printStackTrace();
		}
		return newValue;
	}

	/**
	 * 把String转换成Array，以传入参数token分隔
	 */
	public static String[] convertToArray(String str, String token) {
		if (str == null || str.equals(""))
			return new String[0];
		if (str.indexOf(token) < 0)
			return new String[] { str };
		if (token.equals(""))
			return new String[0];
		int index = -1;
		List arr = new ArrayList();
		while ((index = str.indexOf(token)) != -1) {
			arr.add(str.substring(0, index));
			str = str.substring(index + token.length(), str.length());
		}
		arr.add(str);
		String[] result = new String[arr.size()];
		for (int i = 0; i < arr.size(); i++) {
			result[i] = (String) arr.get(i);
		}
		return result;
	}

	/**
	 * 过滤字符串中的非法字符
	 * @param String in
	 * @return String
	 */
	public static String escapeHTMLTags(String in) {
		if (in == null || in.length() <= 0) {
			return "";
		}
		// 注意，可以向_temp1末尾添加转义字符，同时也要向_temp2末尾添加替换字符串，并且要以"|"分隔
		// 对于ASCII码大于127的字符，在添加到_temp中时需要以escape方式表示，如：\u00FF表示ASCII码为255的字符
		String _temp1 = "\u0020\u00A9\u00AE\u2122\"\u0026\u003C\u003E\u00C1\u00E1\u00C0\u00E0\u00C2\u00E2\u00C4\u00E4\u00C3\u00E3\u00C5\u00E5\u00C6\u00E6\u00C7\u00E7\u00D0\u00F0\u00C9\u00E9\u00C8\u00E8\u00CA\u00EA\u00CB\u00EB\u00CD\u00ED\u00CC\u00EC\u00CE\u00EE\u00CF\u00EF\u00D1\u00F1\u00D3\u00F3\u00D2\u00F2\u00D4\u00F4\u00D6\u00F6\u00D5\u00F5\u00D8\u00F8\u00DF\u00DE\u00FE\u00DA\u00FA\u00D9\u00F9\u00DB\u00FB\u00DC\u00FC\u00DD\u00FD\u00FF";
		String _temp2 = "&nbsp;|&copy;|&reg;|&trade;|&quot;|&amp;|&lt;|&gt;|&Aacute;|&aacute;|&Agrave;|&agrave;|&Acirc;|&acirc;|&Auml;|&auml;|&Atilde;|&atilde;|&Aring;|&aring;|&AElig;|&aelig;|&Ccedil;|&ccedil;|&ETH;|&eth;|&Eacute;|&eacute;|&Egrave;|&egrave;|&Ecirc;|&ecirc;|&Euml;|&euml;|&Iacute;|&iacute;|&Igrave;|&igrave;|&Icirc;|&icirc;|&Iuml;|&iuml;|&Ntilde;|&ntilde;|&Oacute;|&oacute;|&Ograve;|&ograve;|&Ocirc;|&ocirc;|&Ouml;|&ouml;|&Otilde;|&otilde;|&Oslash;|&oslash;|&szlig;|&THORN;|&thorn;|&Uacute;|&uacute;|&Ugrave;|&ugrave;|&Ucirc;|&ucirc;|&Uuml;|&uuml;|&Yacute;|&yacute;|&yuml;";
		StringBuffer ret = new StringBuffer();
		String _new = "";
		// 先将in中含有的转义后的字符串转换成一般的转义前字符，这样就会正确转换一般字符，
		// 但是会将用户输入的包含在_temp2中的转义后的字符理解成相应的原字符，
		// 比如，用户输入" "，会转换成"&nbsp;"输出，但是用户输入"&nbsp;"就会被认为是" ".
		int i;
		StringTokenizer st1 = new StringTokenizer(_temp2, "|");
		for (i = 0; st1.hasMoreTokens(); i++) {
			in = replace(in, st1.nextToken(), _temp1.substring(i, i + 1));
		}
		// 进行字符转义
		int _length = in.length();
		for (i = 0; i < _length; i++) {
			char t_char = in.charAt(i);
			int _index = _temp1.indexOf(t_char);
			if (_index < 0) {
				// 不存在待转义字符
				ret.append(t_char);
			} else {
				// 存在待转义字符，到_temp2中去查找相应的替换字符串
				StringTokenizer st = new StringTokenizer(_temp2, "|");
				for (int j = 0; j <= _index && st.hasMoreTokens(); j++) {
					_new = st.nextToken();
				}
				ret.append(_new);
			}
		}
		return ret.toString();
	}

	/**
	 * 处理字符串中的单引号 一个单引号变为两个单引号 Liang Yong, Feb 28, 2003
	 * @param String oldValue
	 * @return String
	 */
	public static String processSingleQuotes(String oldValue) {
		// Process single quotes
		String newValue = new String();
		if (oldValue != null) {
			char c;
			for (int i = 0; i < oldValue.length(); i++) {
				c = oldValue.charAt(i);
				if (c == '\'') {
					newValue += c;
				}
				newValue += c;
			}
		}
		return newValue;
	}

	/**
	 * 替换字符
	 * @param strSource
	 * @param strFrom 要替换的内容
	 * @param strTo 替换的内容
	 * @return String
	 */
	public static String replace(String strSource, String strFrom, String strTo) {
		StringBuffer strDest = new StringBuffer();
		int intFromLen = strFrom.length();
		int intPos;
		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			strDest.append(strSource.substring(0, intPos));
			strDest.append(strTo);
			strSource = strSource.substring(intPos + intFromLen);
		}
		strDest.append(strSource);
		return strDest.toString();
	}

	/**
	 * 产生指定长度的随机字符串
	 * @param i
	 * @return String
	 */
	public static String randomString(int i) {
		Random randGen = new Random();
		char numbersAndLetters[] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
				.toCharArray();
		if (i < 1)
			return null;
		char ac[] = new char[i];
		for (int j = 0; j < ac.length; j++)
			ac[j] = numbersAndLetters[randGen.nextInt(61)];

		return new String(ac);
	}

	// 产生唯一键
	static long currentMill = 0;

	static List randlist = new ArrayList();

	public static String getUniqueID() {
		long tempMill = System.currentTimeMillis();
		String randstr = String.valueOf((int) (10000 * Math.random()));
		synchronized (randlist) {
			if (tempMill > currentMill) {
				currentMill = tempMill;
				randlist.clear();
			} else {
				while (randlist.contains(randstr)) {
					randstr = String.valueOf((int) (10000 * Math.random()));
				}
				randlist.add(randstr);
			}
		}
		String id = tempMill + "_" + randstr;
		return id;
	}

	/**
	 * 获得截取到指定字节长度后的字符串，多出部分用指定字符串代替。
	 * @param str 需要限定长度的字符串
	 * @param maxByteLength 最大字节数
	 * @param more 超长情况的替换字符串
	 * @return 结果字符串
	 */
	public static String getMoreString(final String str,
			final int maxByteLength, final String more) {
		if (str == null) return "";
		int len = 0;
		StringBuffer buf = new StringBuffer();
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			byte[] bytes = charToByte(c);
			if (bytes[0] == 0) {
				len++;
			} else {
				len += 2;
			}
			if (len > maxByteLength) {
				buf.append(more == null ? "..." : more);
				break;
			} else {
				buf.append(c);
			}
		}
		return buf.toString();
	}
    //字符到字节转换
    public static byte[] charToByte(char ch) {
        int temp = (int)ch;
        byte[] b = new byte[2];
        for (int i = b.length - 1; i > -1; i--) {
            b[i] = new Integer(temp & 0xff).byteValue(); //将最高位保存在最低位
            temp = temp >> 8; //向右移8位
        }
        return b;
    }
    
    /**
	 * 转换成json格式
	 * @param obj 需要转换json的对象
	 * @return 结果字符串
	 */
    public static String getJson(Object obj){
    	Gson gson = new Gson();
    	String str = gson.toJson(obj);
    	return str;
    }
    
    
    public static void main(String[] args) {
		
	}

}
