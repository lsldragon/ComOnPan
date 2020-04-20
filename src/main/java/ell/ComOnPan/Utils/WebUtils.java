package ell.ComOnPan.Utils;

import java.net.URLEncoder;

public class WebUtils {

	public static String encode(String valueString) throws Exception {

		String result = URLEncoder.encode(valueString, "UTF-8");
		return result;

	}

	public static int String2Int(String value) {
		return Integer.parseInt(value);
	}
}
