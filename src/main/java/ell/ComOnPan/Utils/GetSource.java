package ell.ComOnPan.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetSource {

	private static final String USER_AGENT_STRING = "User-Agent";
	private static final String USER_AGENT_VALUE_STRING = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.106 Mobile Safari/537.36";

	public static String getCode(String urlString, String charsetType) {

		if (charsetType == null || charsetType.equals("")) {
			charsetType = "utf-8";
		}

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;

		HttpGet request = new HttpGet(urlString);
		request.setHeader(USER_AGENT_STRING, USER_AGENT_VALUE_STRING);

		try {
			response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String resultString = EntityUtils.toString(entity, charsetType);
					HttpClientUtils.closeQuietly(httpClient);
					HttpClientUtils.closeQuietly(response);
					return resultString;
				}
			}

		} catch (Exception e) {
			return null;
		}

		return null;
	}
}
