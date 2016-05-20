package com.example.aademo.util;
import java.util.regex.Pattern;

public class StringUtil {
	// 验证是否是数字的校验
	private static String numberPatternStr = "\\d+(.\\d+)?|-\\d+(.\\d+)?$";

	/**
	 * 查看str是否为空
	 * 
	 * @param str
	 *            待判断的str
	 * @return true:传入值为空,false:不为空
	 */
	public static boolean isNull(String str) {
		if (str == null)
			return true;
		else if (str.length() == 0)
			return true;
		else if (str.toLowerCase().equals("null"))
			return true;
		return false;
	}

	/**
	 * 查看obj是否为空
	 *
	 * @param object
	 *            待判断的str
	 * @return true:传入值为空,false:不为空
	 */
	public static boolean isNull(Object object) {
		if (object == null)
			return true;
		else if (object.toString().length() == 0)
			return true;
		else if (object.toString().toLowerCase().equals("null"))
			return true;
		return false;
	}

	/**
	 * 查看一个字符串是否包含在一个字符串数组中
	 * 
	 * @param res
	 *            待查看的字符串
	 * @param tar
	 *            是否包含这个字符串的数组
	 * @return true:包含,false:不包含
	 */
	public static boolean isContains(String res, String... tar) {
		if (tar == null || res == null)
			return false;
		for (String str : tar) {
			if (res.equals(str))
				return true;
		}
		return false;
	}

	/**
	 * 查看一个数字是否包含在一个数字数组中
	 *
	 * @param res
	 *            待查看的字符串
	 * @param tar
	 *            是否包含这个字符串的数组
	 * @return true:包含,false:不包含
	 */
	public static boolean isContains(int res, int... tar) {
		if (tar == null)
			return false;
		for (int str : tar) {
			if (res==str)
				return true;
		}
		return false;
	}

	/**
	 * 验证是否是纯数字: 以下都是纯数字 0.0 0 0.00 100000 12.54
	 * 
	 * @param validateNum
	 *            待验证的文本
	 * @return false不是纯数字,true:是纯数字
	 */
	public static boolean verifyTrueNumber(String validateNum) {
		if (isNull(validateNum))
			return false;
		return Pattern.compile(numberPatternStr).matcher(validateNum).matches();
	}

	/**
	 * 查看一个值类型的参数是否包含在一个值类型参数的数组中
	 * =======两个参数的类型必须是一模一样的,如如果传int型,则tar也必须是int型,否则会判断错误======
	 * 
	 * @param res
	 *            待查看的数字
	 * @param tar
	 *            是否包含这个数字的数组
	 * @return true:包含,false:不包含
	 */
	public static boolean isObjContains(Object res, Object... tar) {
		if (tar == null || res == null)
			return false;
		for (Object item : tar) {
			if (res == item)
				return true;
		}
		return false;
	}

	/**
	 * 对buffer增加新的字符串和分隔符(如果这个串不为空的话)
	 * 
	 * @param buffer
	 *            待添加的buffer
	 * @param addStr
	 *            添加入的str
	 * @param splitSign
	 *            添加完成后结尾的分隔符
	 */
	public static void AppendNotEmptyStr(StringBuffer buffer, String addStr, String splitSign) {
		if (addStr != null && addStr.length() != 0 && !addStr.equals("")) {
			buffer.append(addStr + splitSign);
		}
	}

	/**
	 * 去除结尾的某个字符串
	 * 
	 * @param buffer
	 * @param splitSign
	 */
	public static void removeLastSplitSign(StringBuffer buffer, String splitSign) {
		if (buffer.length() != 0 && buffer.lastIndexOf(splitSign) == buffer.length() - splitSign.length()) {
			buffer = buffer.replace(buffer.length() - splitSign.length(), buffer.length(), "");
		}
	}

	/**
	 * 去除开头的某个字符串
	 * 
	 * @param buffer
	 * @param splitSign
	 */
	public static void removeFristSplitSign(StringBuffer buffer, String splitSign) {
		if (buffer.length() != 0 && buffer.indexOf(splitSign) == 0) {
			buffer = buffer.replace(0, splitSign.length(), "");
		}
	}


	/**
	 * 隐藏一个字符串的中间几位
	 * @param str  需要处理的字符串
	 * @param visiablestartcount  这个字符串,开头几个字符是显示明文的
	 * @param visiablendcount  这个字符串,末尾几个字符是显示明文的.
	 * @param hiddenSign     用什么符号来代替隐藏部分
	 * @return
	 */
	public static String hideMidleOfStr(String str,int visiablestartcount,int visiablendcount,String hiddenSign) {
		StringBuilder sb = new StringBuilder();

		//字符串长度不为空,且长度大于visiablestart+visiablestart(起始位置不隐藏的字符个数+末尾位置不隐藏的字符个数)
		if (!StringUtil.isNull(str) && str.length() > (visiablestartcount+visiablendcount)) {
			char[] arrC = str.toCharArray();
			int length = arrC.length;
			for (int i = 0; i < length; i++) {
				if (i < visiablestartcount)
					sb.append(arrC[i]);
				else if (i >= length - visiablendcount)
					sb.append(arrC[i]);
				else {
					sb.append(hiddenSign);
				}
			}
			return sb.toString();
		} else {
			return str;
		}
	}

	/**
	 * 给金额字符串的小数点后面补齐数字0
	 */
	public static String endWithZero(String money){
		String result = "";
		if (money == null)
			return "";
		else if (money.length() == 0)
			return "";
		else if (money.toLowerCase().equals("null"))
			return "";
		result = money;
		if(money.contains(".")){
			String[] res = money.split("\\.");
			int leng = res[1].length();
			if(leng == 1){
				result = money+"0";
			}
		}
		else{
			result = money+".00";
		}
		return result;
	}
}
