package ell.ComOnPan.panDuoDuo;

import java.util.ArrayList;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ell.ComOnPan.Utils.GetSource;
import ell.ComOnPan.Utils.WebUtils;

public class PanDuoDuoParse {

	public ArrayList<ArrayList<String>> getPDDSource(String keyWords, String page) throws Exception {

		ArrayList<String> urlList = new ArrayList<String>();
		ArrayList<String> nameList = new ArrayList<String>();
		ArrayList<String> dateList = new ArrayList<String>();
		ArrayList<String> realUrlList = new ArrayList<String>();

		ArrayList<ArrayList<String>> contents = new ArrayList<ArrayList<String>>();

		String mainStation = "http://m.rufengso.net";
		String reUrlStation = "http://pdd.19mi.net/go/";

		StringBuilder sb = new StringBuilder();
		sb.append("http://www.rufengso.net/s/comb/n-");
		String word = WebUtils.encode(keyWords);
		sb.append(word);
		sb.append("&s-feedtime1");
		sb.append("/");
		sb.append(page);

		String urlString = sb.toString();
		String codeString = GetSource.getCode(urlString, "");

		Document document = Jsoup.parse(codeString);
		Elements elements = document.select("#content .list li .content a");

		Elements countElements = document.select("#content .title span b");

		String numString = countElements.get(0).text();
		System.out.println("共有资源: " + numString + " 个");

		Elements dateElements = document.select("#content .list li .content .list-content");
		for (Element e : dateElements) {
			dateList.add(e.text());
		}

		int max = elements.size();
		for (int i = 0; i < max; i += 3) {

			String names = elements.get(i).text();
			String urls = mainStation + elements.get(i).attr("href");

			String temp = urls.substring(urls.length() - 8);
			String reurl = reUrlStation + temp;
			System.out.println(reurl);

			nameList.add(names);
			urlList.add(reurl);
		}

		int length = urlList.size();
		int i = 0;
		for (i = 0; i < length; i++) {

			String code = GetSource.getCode(urlList.get(i), "");
			System.out.println(code);
			System.out.println("----------------------------");
			Random random = new Random();
			int value = random.nextInt(8);
			if (value <= 3) {
				value = 4;
			}
			System.out.println("系统休眠: " + value + "s");
			Thread.sleep(value * 1000);

			Document parse = Jsoup.parse(code);
			Elements elements2 = parse.select(".main a");
			for (Element e : elements2) {
				String bdyUrl = e.attr("href");
				realUrlList.add(bdyUrl);
			}

		}
		contents.add(nameList);
		contents.add(realUrlList);
		contents.add(dateList);

		return contents;
	}
}
