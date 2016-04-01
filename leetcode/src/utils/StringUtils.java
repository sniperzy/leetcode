package utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 字符串处理工具类，提供了一些对字符串进行处理的静态方法
 */
public final class StringUtils {

	/* 私有的构造方法，保证此类不能外部被实例化 */
	private StringUtils() {
	}

	/**
	 * 如果字符串等于null、空白字符(“”)、空格(“ ”)则返回true,否则返回false
	 * 
	 * @param str
	 *            String 要比较的字符串
	 * @return boolean
	 */
	public static boolean isBlank(String str) {
		boolean b = true;
		if (null == str) {
			return b;
		}

		str = str.trim(); // 去掉空格
		if (!str.equals("")) { // 如果不等于“”空字符则返回值为false
			b = false;
		}
		return b;
	}

	/**
	 * 如果字符串不等于null、空白字符(“”)、空格(“ ”)则返回true,否则返回false
	 * 
	 * @param str
	 *            String 要比较的字符串
	 * @return boolean
	 */
	public static boolean isNotBlank(String str) {
		return (!isBlank(str));
	}

	/**
	 * 如果字符串等于null、空白字符("")、空格(" ")则返回空白字符(""), 否则返回一个将字符串的前后空格去掉的字符串
	 * 
	 * @param str
	 *            String 要处理的字符串
	 * @return String
	 */
	public static String trimToBlank(String str) {
		String s = "";
		if (isBlank(str)) {
			return s;
		}
		s = str.trim();
		return s;
	}

	/**
	 * 如果字符串等于null、空白字符("")、空格(" ")则返回null, 否则返回一个将字符串的前后空格去掉的字符串
	 * 
	 * @param str
	 *            String 要处理的字符串
	 * @return String
	 */
	public static String trimToNull(String str) {
		String s = null;
		if (isBlank(str)) {
			return s;
		}
		s = str.trim();
		return s;
	}

	/**
	 * 将对象使用指定的分隔符转换成一个字符串，
	 * 
	 * @param delimiter
	 *            分隔符
	 * @param ignore
	 *            为true忽略null值
	 * @param objs
	 *            分隔对象
	 * @return
	 */
	public static String join(String delimiter, boolean ignore, Object... objs) {
		if (objs == null || 0 == objs.length) {
			return "";
		}
		StringBuffer bf = new StringBuffer();
		Object obj = null;
		int ind = 0;
		for (int i = 0; i < objs.length; i++) {
			obj = objs[i];
			if (null == obj && true == ignore) {
				continue;
			} else {
				if (0 == ind) {
					bf.append(obj);
				} else {
					bf.append(delimiter).append(obj);
				}
				ind++;
			}
		}
		return bf.toString();
	}

	/**
	 * 使用指定的分隔符将字符串分割成一个字符串数组
	 * 
	 * @param input
	 *            字符串
	 * @param delimiter
	 *            分隔符
	 * @return
	 */
	public static String[] split(String input, String delimiter) {
		String[] values = new String[] { input };
		if (null != input && null != delimiter
				&& -1 != input.indexOf(delimiter)) {
			values = input.split(delimiter);
		}
		return values;
	}

	/**
	 * 将特殊字符(<、>、"、'等)转换成对应的实体
	 * 
	 * @param s
	 *            需要转换的字符串
	 * @return
	 */
	public static final String htmlEncode(String s) {
		return htmlEncode(s, true);
	}

	/**
	 * 将特殊字符(<、>、"、'等)转换成对应的实体
	 * 
	 * @param s
	 *            需要转换的字符串
	 * @param encodeSpecialChars
	 *            对特殊字符进行编码
	 * @return
	 */
	public static final String htmlEncode(String s, boolean encodeSpecialChars) {
		s = trimToBlank(s);
		StringBuffer str = new StringBuffer();
		for (int j = 0; j < s.length(); j++) {
			char c = s.charAt(j);
			if (c < '\200') {
				switch (c) {
				case 34: // '"'
					str.append("&quot;");
					break;

				case 38: // '&'
					str.append("&amp;");
					break;

				case 60: // '<'
					str.append("&lt;");
					break;

				case 62: // '>'
					str.append("&gt;");
					break;

				default:
					str.append(c);
					break;
				}
				continue;
			}
			if (encodeSpecialChars && c < '\377') {
				String hexChars = "0123456789ABCDEF";
				int a = c % 16;
				int b = (c - a) / 16;
				String hex = (new StringBuilder()).append("")
						.append(hexChars.charAt(b)).append(hexChars.charAt(a))
						.toString();
				str.append((new StringBuilder()).append("&#x").append(hex)
						.append(";").toString());
			} else {
				str.append(c);
			}
		}

		return str.toString();
	}

	private static final String char0 = "0";// 填充的字符串

	public static String nullToEmpty(Object o) {
		if (o == null) {
			return "";
		} else {
		}
		return o.toString();
	}

	/**
	 * 以规定长度填充字符
	 * 
	 * @param code
	 * @param leng
	 * @param limit
	 *            :true限制长度必须小于leng规定的值,false则可以大于这个长度，但小于这个长度则补起
	 * @return
	 */
	public static String formatCodeStr(String code, int leng, boolean limit) {
		return formatCodeStr(code, char0, leng, limit);
	}
	
	/**
	 * 以规定长度填充字符
	 * 
	 * @param code
	 * @param fillStr
	 * @param leng
	 * @param limit
	 *            :true限制长度必须小于leng规定的值,false则可以大于这个长度，但小于这个长度则补起
	 * @return
	 */
	public static String formatCodeStr(String code, String fillStr, int leng, boolean limit) {
		if (code.length() < leng) {
			return formatCodeStr(fillStr + code, fillStr, leng, limit);
		} else {
			if (limit) {
				return code.substring(code.length() - leng);
			}
			return code;
		}
		
	}

	/**
	 * 在每一个类里面写一个main方法,可以很方便的 对这个类进行测试
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		// String s = null;
		// System.out.println(isBlank(s));
		// System.out.println(isNotBlank(s));
		//
		// s = "";
		// System.out.println(isBlank(s));
		// System.out.println(isNotBlank(s));
		//
		// s = " ";
		// System.out.println(isBlank(s));
		// System.out.println(isNotBlank(s));
		//
		// s = " ";
		// System.out.println("[" + trimToNull(s) + "]");
		// System.out.println("[" + trimToBlank(s) + "]");

		// System.out.println(StringUtils.join(",", "ddd", null, "fff"));

		// String input = "aa , bb, ccc";
		// String[] arr = StringUtils.split(null, "z");
		// System.out.println(StringUtils.join("-", arr));
		// System.out.println(arr.length);
		// for (int i = 0; i < arr.length; i++) {
		// System.out.println(arr[i]);
		// }
		//
		// System.out.println(StringUtils.join("-", (Object[]) new Integer[] {
		// 11,
		// 22 }));

		// System.out.println(StringUtils.join(",", false, new Object[] { null,
		// "a", 2, 3, null }));

		// String str = "<br>？";
		// System.out.println(htmlEncode(str, true));
		// System.out.println('\377');
		//
		// String str = "你好啊，哈哈!,数据项:(呵呵)";
		// System.out.println(escape(str));
		// System.out.println(unescape(escape(str)));
	}

	/**
	 * 将字符串编码格式转成GB2312指定encode
	 * 
	 * @param str
	 *            encode
	 * @return
	 */
	public static String TranEncode(String str, String encode) {
		try {
			String strEncode = getEncoding(str);
			String temp = new String(str.getBytes(strEncode), encode);
			return temp;
		} catch (java.io.IOException ex) {

			return null;
		}
	}

	/**
	 * 判断字符串的编码
	 * 
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}

	/**
	 * 把 ~!@#$%^&*()_+|\\=-,./?><;'][{}\" 转化为ASCII码
	 * 
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j) || isGb(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * 把 ASCII码转化为 ~!@#$%^&*()_+|\\=-,./?><;'][{}\"
	 * 
	 * @author zhoubinshan
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(
							src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(
							src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	// private static Map<String, String> getEscapeMap(){
	// if(escapeMap == null){
	// initEscapeMap();
	// }
	// return escapeMap;
	// }
	//
	// private static void initEscapeMap() {
	// escapeMap = new HashMap<String, String>();
	// escapeMap.put(":", "%@01@%");
	// escapeMap.put(",", "%@02@%");
	// escapeMap.put(";", "%@03@%");
	// escapeMap.put("<", "%@04@%");
	// escapeMap.put(">", "%@05@%");
	// escapeMap.put("{", "%@06@%");
	// escapeMap.put("}", "%@07@%");
	// escapeMap.put(" ", "%@08@%");
	// escapeMap.put("'", "%@09@%");
	// escapeMap.put("(", "%@10@%");
	// escapeMap.put(")", "%@11@%");
	// escapeMap.put("@", "%@12@%");
	// escapeMap.put("[", "%@13@%");
	// escapeMap.put("]", "%@14@%");
	// escapeMap.put("/", "%@15@%");
	// escapeMap.put("\\", "%@16@%");
	// escapeMap.put(".", "%@17@%");
	// escapeMap.put("?", "%@18@%");
	// escapeMap.put("=", "%@19@%");
	// escapeMap.put("%", "%@20@%");
	// escapeMap.put("-", "%@21@%");
	// escapeMap.put("+", "%@22@%");
	// escapeMap.put("_", "%@23@%");
	// escapeMap.put("|", "%@24@%");
	// escapeMap.put("*", "%@25@%");
	// escapeMap.put("!", "%@26@%");
	// escapeMap.put("~", "%@27@%");
	// escapeMap.put("#", "%@28@%");
	// escapeMap.put("$", "%@29@%");
	// escapeMap.put("^", "%@30@%");
	// escapeMap.put("&", "%@31@%");
	// escapeMap.put("`", "%@32@%");
	// }

	/**
	 * 判断字符串是否为中文
	 * 
	 * @param ch
	 * @return
	 * @author zhaoye 2015-04-20
	 */
	public static boolean isGb(char ch) {
		boolean flag = false;

		byte[] bytes;
		try {
			bytes = ("" + ch).getBytes("GB2312");
			if (bytes.length == 2) {
				int[] ints = new int[2];
				ints[0] = bytes[0] & 0xff;
				ints[1] = bytes[1] & 0xff;
				if (ints[0] >= 0x81 && ints[0] <= 0xfe && ints[1] >= 0x40
						&& ints[1] <= 0xfe) {
					flag = true;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 获取对象的字符表示
	 * 
	 * @param obj
	 * @return
	 */
	public static String getObjectOfString(Object obj) {
		String str = "";

		if (obj != null) {
			try {
				str = String.valueOf(obj);
			} catch (Exception e) {

			}
		}

		return str;
	}
	
	/**
	 * 获得对象的Integer表示
	 * 
	 * @param obj
	 * @return
	 */
	public static int getObjectInt(Object obj)
	{
		if (obj == null)
		{
			return 0;
		}
		else if (obj.toString().length() == 0)
		{
			return 0;
		}
		else
		{
			try
			{
				int intTemp = Integer.parseInt(obj.toString().trim());
				return intTemp;
			}
			catch (Exception e)
			{
				return 0;
			}
		}
	}
	
	/**
	 * 获得对象的Double表示
	 * 
	 * @param obj
	 * @return
	 */
	public static double getObjectDouble(Object obj)
	{
		if (obj == null)
		{
			return 0.0;
		}
		else
		{
			return Double.parseDouble(obj.toString());
		}
	}

	/**
	 * 获得对象的Double表示
	 * 
	 * @param obj
	 * @return
	 */
	public static BigDecimal getObjectBigDecimal(Object obj)
	{
		if (obj == null || "".equals(obj.toString()))
		{
			return new BigDecimal(0.0);
		}
		else
		{
			return new BigDecimal(obj.toString());
		}
	}
	
	/**
	 * 获得对象的长整数表示
	 * 
	 * @param obj
	 * @return
	 */
	public static long getObjectLong(Object obj)
	{
		if (obj == null)
		{
			return 0;
		}
		else
		{
			if (obj.getClass() == Timestamp.class)
			{
				return ((Timestamp) obj).getTime();
			}
			else
			{
				return Long.parseLong(obj.toString().trim());
			}
		}
	}

	/**
	 * 重新整理字符串并进行去重
	 * 
	 * @param stringList
	 *            字符串列表
	 * @param splitStr
	 *            分隔符
	 * @return
	 */
	public static String arrangeStr(List<String> stringList, String splitStr) {
		String str = "";

		if (stringList != null) {
			Set<String> stringSet = new LinkedHashSet<String>();
			for (String curString : stringList) {
				if (curString != null) {
					String[] splitArray = curString.split(splitStr);
					for (String curStr : splitArray) {
						stringSet.add(curStr);
					}
				}
			}
			int index = 0;
			StringBuffer sb = new StringBuffer();
			for (String cur : stringSet) {
				if (index != 0) {
					sb.append(",");
				}
				sb.append(cur);
				index++;
			}
			str = sb.toString();
		}

		return str;
	}

	/**
	 * copy字符串时,新字符串前缀生成
	 * “copy0_XXXXXXXX”类型字符串生成
	 * 
	 * @author li_zhen
	 * @param name 
	 * @param namePrefix 字符串前缀
	 * @param prefixCount 第多少个前缀
	 * @param nameSeparator 前缀与字符串的分隔符
	 * @return
	 */
	public static String copyNameWithPrefixHandle(String name , String namePrefix , String prefixCount , String nameSeparator){
		StringBuffer resultName = new StringBuffer();
        String[] nameArray = name.split(nameSeparator);
        if(nameArray.length > 1 && nameArray[0].startsWith(namePrefix)){
        	resultName.append(namePrefix).append(prefixCount).append(nameSeparator).append(nameArray[1]);
        }else{
        	resultName.append(namePrefix).append(prefixCount).append(nameSeparator).append(name);
        }
        return resultName.toString();
	}
	
	public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
	
	// Stripping
    //-----------------------------------------------------------------------
   
    public static String strip(String str) {
        return strip(str, null);
    }
   
    public static String stripToNull(String str) {
        if (str == null) {
            return null;
        }
        str = strip(str, null);
        return str.length() == 0 ? null : str;
    }
   
    public static String stripToEmpty(String str) {
        return str == null ? "" : strip(str, null);
    }
   
    public static String strip(String str, String stripChars) {
        if (isEmpty(str)) {
            return str;
        }
        str = stripStart(str, stripChars);
        return stripEnd(str, stripChars);
    }
   
    public static String stripStart(String str, String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                start++;
            }
        }
        return str.substring(start);
    }
   
    public static String stripEnd(String str, String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }
        if (stripChars == null) {
            while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                end--;
            }
        }
        return str.substring(0, end);
    }
}
