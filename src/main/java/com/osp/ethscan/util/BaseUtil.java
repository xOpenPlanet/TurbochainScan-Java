package com.osp.ethscan.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * osp_ethscan工具类
 * 
 * @author zhangmingcheng
 */
public class BaseUtil {

	/**
	 * 获取当前时间:格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		return sdf.format(date);
	}

	/*
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(Long lt) {
		String res;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(lt * 1000L);
		res = sdf.format(date);
		return res;
	}

	/*
	 * 将时间转换为时间戳
	 */
	public static long dateToStamp(String s) {
		long ts = 0;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = simpleDateFormat.parse(s);
			ts = date.getTime() / 1000L;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	/**
	 * 获取过去任意天内的日期数组
	 * 
	 * @param intervals
	 *            intervals天内
	 * @return 日期数组
	 */
	public static List<String> getPastDaysList(int intervals, String formats) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = intervals; i > 0; i--) {
			pastDaysList.add(getPastDate(i, formats));
		}
		return pastDaysList;
	}

	/**
	 * 获取过去第几天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past, String formats) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat(formats);
		String result = format.format(today);
		return result;
	}

	/**
	 * 计算两个时间的间隔
	 * 
	 * @param hdtime
	 *            原时间戳
	 * @return
	 */
	public static String timeInterval(Long hdtime) {
		long day = 1000 * 24 * 60 * 60;
		long hour = 1000 * 60 * 60;
		long minute = 1000 * 60;
		long second = 1000;
		String result = "";
		Long ndtime = System.currentTimeMillis();
		Long interval = ndtime - hdtime * 1000L;
		if (interval / day > 0) {
			result += interval / day + " day";
			if (interval / day != 1) {
				result += "s";
			}
			if ((interval / hour) % 24 != 0) {
				result += " " + (interval / hour) % 24 + " hr";
				if ((interval / hour) % 24 != 1) {
					result += "s";
				}
			}
			return result += " ago";
		}
		if (interval / hour > 0) {
			result += interval / hour + " hr";
			if (interval / hour != 1) {
				result += "s";
			}
			if ((interval / minute) % 60 != 0) {
				result += " " + (interval / minute) % 60 + " min";
				if ((interval / minute) % 60 != 1) {
					result += "s";
				}
			}
			return result += " ago";
		}
		if (interval / minute > 0) {
			result += interval / minute + " min";
			if (interval / minute != 1) {
				result += "s";
			}
			return result + " ago";
		}
		if ((interval / second) != 1) {
			return interval / second + " secs ago";
		}
		return interval / second + " sec ago";
	}

	/**
	 * 大整数16进制转10进制
	 * 
	 * @param hex
	 * @return
	 */
	public static String hexTransformToDec(String hex) {
		BigInteger base = new BigInteger("16");
		BigDecimal result = new BigDecimal("0.00000000");
		try {
			hex = hex.substring(2);
			for (int i = 0; i < hex.length(); i++) {
				char ch = hex.charAt(hex.length() - 1 - i);
				if (ch >= 'a' && ch <= 'f') {
					BigInteger tmp = base.pow(i).multiply(new BigInteger(Integer.toString((ch - 'a' + 10))));
					result = result.add(new BigDecimal(tmp));
				} else if (ch >= 'A' && ch <= 'F') {
					BigInteger tmp = base.pow(i).multiply(new BigInteger(Integer.toString((ch - 'A' + 10))));
					result = result.add(new BigDecimal(tmp));
				} else {
					BigInteger tmp = base.pow(i).multiply(new BigInteger(Character.toString(ch)));
					result = result.add(new BigDecimal(tmp));
				}
			}
			result = result.divide(new BigDecimal("10").pow(18));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 判断字符串是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
