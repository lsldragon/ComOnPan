package ell.ComOnPan.slimego;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ell.ComOnPan.Utils.GetSource;
import ell.ComOnPan.Utils.WebUtils;

public class SlimegoParse {

	public ArrayList<Slimego> getSlimegoSource(String keyWord, String page) throws Exception {

		ArrayList<Slimego> slimegos = new ArrayList<Slimego>();

		StringBuilder sb = new StringBuilder();
		sb.append("http://www.slimego.cn/search.html?q=");
		sb.append(WebUtils.encode(keyWord));
		sb.append("&page=");
		sb.append(page);
		sb.append("&rows=20");

		String url = sb.toString();
		String sourceCode = GetSource.getCode(url, "");
		Document document = Jsoup.parse(sourceCode);

		Elements all = document.getElementsByClass("total-results");
		String searchResult = all.text();
		System.out.println(searchResult);

		Elements nameAndUrlElements = document.select(".container div .searchRow .searchCell .link a");
		for (Element e : nameAndUrlElements) {
			Slimego slimego = new Slimego();
			slimego.setName(e.text());
			slimego.setUrl(e.attr("href"));
			slimegos.add(slimego);
		}

		Elements fileTypeElements = document.select(".container div .searchRow .searchCell .info .ftype");
		int length1 = fileTypeElements.size();
		for (int i = 0; i < length1; i++) {
			String fileType = fileTypeElements.get(i).text();
			slimegos.get(i).setFtype(fileType);
		}

		Elements fileSizeElements = document.select(".container div .searchRow .searchCell .info .size");
		int length2 = fileSizeElements.size();
		for (int i = 0; i < length2; i++) {
			String fileSize = fileSizeElements.get(i).text();
			slimegos.get(i).setSize(fileSize);
		}

		Elements uploadElements = document.select(".container div .searchRow .searchCell .info .upload");
		int length3 = uploadElements.size();
		for (int i = 0; i < length3; i++) {
			String uploadTime = uploadElements.get(i).text();
			slimegos.get(i).setUploadTime(uploadTime);
		}

		return slimegos;
	}
}
