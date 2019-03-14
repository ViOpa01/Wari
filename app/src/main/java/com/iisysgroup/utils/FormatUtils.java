package com.iisysgroup.utils;

import android.content.Context;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 商业化计算、日期时间、卡号处理类
 * 
 * @author tao
 * 
 */
public class FormatUtils {

	/**
	 * 将long型转换成标准的金额，小数点后保留两位. xxxx.xx
	 * 
	 * @param amount
	 * @return
	 */
	public static String formatAmountToYuan(long amount) {
		try {
			BigDecimal amount2 = new BigDecimal(amount);
			return amount2.divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "0.00";
		}
	}

	/**
	 * 将标准金额，元单位换成分单位
	 * 
	 * @param amount
	 * @return
	 */
	public static long formatAmountToCents(String amount) {
		BigDecimal amount2 = new BigDecimal(amount);
		return amount2.multiply(new BigDecimal(100)).setScale(0).longValue();
	}

	/**
	 * 去除字符中间的 "空格/-/," 等间隔符
	 * 
	 * @param string
	 *            要格式化的字符
	 * @return 格式化后的字符
	 */
	public static String formatString(String string) {
		if (string == null)
			return "";
		String newString = string.replaceAll(" ", "").replaceAll("-", "").replaceAll(",", "");
		return newString;
	}

	/**
	 * 金额格式化
	 * 
	 * @param s
	 *            金额1111.11(double string)
	 * @param isInitNull
	 *            是否初始化为空字符
	 * @return 格式后的金额(###,###.##)
	 */
	public static String formatAmount(String s, boolean isInitNull) {
		String result = "";
		if (StringUtils.isEmpty(s))
			return "";
		try {
			String temp = s;
			s = formatString(s);// 去除string可能的分隔符
			double num = 0.0;
			try {
				num = Double.parseDouble(s);
			} catch (NumberFormatException e) {
			}
			if (num == 0) {
				if (isInitNull) {
					return "";
				} else {
					return "0.00";
				}
			}
			if (num < 1) {// 小于1情况特殊处理
				if (s.length() == 4) {// 0.05
					return temp;
				} else if (s.length() == 3) {// 0.5
					return temp + "0";
				}
			}
			NumberFormat formater = new DecimalFormat("#,###.00");
			result = formater.format(num);
		} catch (Exception e) {
		}
		if (result.startsWith(".")) {
			result = "0" + result;
		}
		return result;
	}

	/**
	 * 金额格式化
	 * 
	 * @param value
	 *            金额########(long string)
	 * @return 格式后的金额(###,###.##)
	 */
	public static String formatAmount(String value) {
		return formatAmount(value, 2);
	}

	/**
	 * 金额格式化
	 * 
	 * @param value
	 *            金额########(long string)
	 * @param format
	 *            小数点位数
	 * @return 格式后的金额(###,###.##)
	 */
	public static String formatAmount(String value, int format) {
		String strInteger = "0";
		String strDecimal = "0";
		StringBuilder tmp = new StringBuilder();

		if (value == null || value.length() == 0 || (!StringUtils.isNumeric(value))) {
			value = "0";
		}

		if (value.length() > format) {
			strInteger = Long.valueOf(value.substring(0, value.length() - format)).toString();
			if (strInteger.length() > 3) {
				int end = strInteger.length() % 3;
				int len = strInteger.length();
				for (int start = 0; end <= len; start = end, end += 3) {
					tmp.append(strInteger.subSequence(start, end));
					if (end < len && start != end) {
						tmp.append(",");
					}
				}
				strInteger = tmp.toString();
			}
			strDecimal = value.substring(value.length() - format);
		} else {
			strDecimal = String.format("%0" + format + "d", Integer.parseInt(value));
		}
		return strInteger + "." + strDecimal;
	}

	public static boolean checkAmount(String amount) {
		if (amount.contains(".")) {
			int index = amount.indexOf(".");
			int s = amount.length() - 1 - index;
			if (s > 2)
				return false;

		} else {
			if (amount.length() > 12)
				return false;
		}
		return true;
	}

	public static String formatCardNoWithSpace(String data) {
		String sBuffer = "";
		if (null == data || "".equals(data))
			return "";
		data = data.replaceAll(" ", "");
		int l = data.length();
		int b = l / 4;
		int c = l % 4;
		int index = 0;
		if (b == 0) {
			return data;
		}
		for (int i = 0; i < b; i++) {
			String d = data.substring(index, index + 4);
			sBuffer += d;
			if (i < b - 1 || (i == b - 1 && c != 0)) {
				sBuffer += " ";
			}

			index += 4;
		}

		if (c > 0) {
			String d = data.substring(index, index + c);
			sBuffer += d;

		}
		return sBuffer;

	}

	/**
	 * 卡号格式化 前6后4
	 * 
	 * @param cardNo
	 * @return
	 */
	public static String formatCardNoWithStar(String cardNo) {
		if (cardNo == null || "".equals(cardNo))
			return "";
		int len = cardNo.length();
		if (len <= 10) {
			return cardNo;
		} else {
			String c1 = cardNo.substring(0, 6);
			String c3 = cardNo.substring(len - 4, len);
			String c2 = "";
			for (int i = 0; i < len - 10; i++) {
				c2 += "*";
			}
			return c1 + c2 + c3;
		}
	}



	/*
	 * 0000000001250转化成12.50
	 */
	public static String unformatMount(String mount) {
		if ("".equals(mount) || mount == null) {
			return "0.00";
		}
		double money = (double) (Long.parseLong(mount) * 0.01);
		if (money > 0) {
			DecimalFormat df = new DecimalFormat("##0.00");
			return df.format(money);

		} else {
			return "0.00";
		}
	}

	/**
	 * 格式化手机号码
	 * 
	 * @param data
	 * @return
	 */
	public static String formatPhoneNo(String data) {
		String sBuffer = "";
		if (null == data || "".equals(data))
			return "";
		data = data.replaceAll(" ", "");
		int l = data.length();
		int a = l - 3;
		int b = a / 4;
		int c = a % 4;
		int index = 0;
		if (a <= 0) {
			return data;
		}
		for (int i = 0; i < b + 1; i++) {
			String d = "";
			if (i > 0) {
				d = data.substring(index, index + 4);
				index += 4;
			} else {
				d = data.substring(index, index + 3);
				index += 3;
			}
			sBuffer += d;
			if (i < b || (i == b && c != 0)) {
				sBuffer += " ";
			}
		}

		if (c > 0) {
			String d = data.substring(index, index + c);
			sBuffer += d;

		}
		return sBuffer;

	}

	/**
	 * 获取资源文本
	 * 
	 * @param context
	 * @param resId
	 * @return
	 */
	public static String getText(Context context, int resId) {
		return context.getResources().getString(resId);
	}

}
