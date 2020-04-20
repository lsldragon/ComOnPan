package ell.ComOnPan;

import java.util.ArrayList;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;

import ell.ComOnPan.panDuoDuo.PanDuoDuoParse;
import ell.ComOnPan.slimego.Slimego;
import ell.ComOnPan.slimego.SlimegoParse;

public class App {

	public static void main(String[] args) throws Exception {

		while (true) {
			System.out.println();
			Scanner scanner = new Scanner(System.in);
			System.out.print("请输入搜索引擎: \n 1. 史莱姆\n 2.盘多多\n -> ");
			String options = scanner.nextLine();

			if (options.equals("1")) {
				slm();
			} else if (options.equals("2")) {
				pdd();
			} else if (options.equals("exit")) {
				System.exit(0);
			} else {
				System.out.println("参数错误！！！");
			}
		}

	}

	private static void slm() throws Exception {

		while (true) {

			Scanner scanner = new Scanner(System.in);
			System.out.print("输入搜索关键字(史莱姆):\n ->");
			String keyWords = scanner.nextLine();
			System.out.print("输入页码数(史莱姆，从1开始): \n ->");
			String pageString = scanner.nextLine();
			if (keyWords.equals("exit") || pageString.equals("exit")) {
				break;
			}

			SlimegoParse slimego = new SlimegoParse();
			ArrayList<Slimego> slimegoSource = slimego.getSlimegoSource(keyWords, pageString);

			String jsonString = JSON.toJSONString(slimegoSource, true);
			System.out.println("--------------------------------------");
			System.out.println(jsonString);

		}

	}

	private static void pdd() throws Exception {

		while (true) {

			Scanner scanner = new Scanner(System.in);
			System.out.print("输入搜索关键字(盘多多):\n ->");
			String keyWords = scanner.nextLine();
			System.out.print("输入页码数(盘多多，从1开始): \n ->");
			String pageString = scanner.nextLine();

			if (keyWords.equals("exit") || pageString.equals("exit")) {
				break;
			}

			PanDuoDuoParse pdd = new PanDuoDuoParse();
			ArrayList<ArrayList<String>> pddSource = pdd.getPDDSource(keyWords, pageString);

			ArrayList<String> name = pddSource.get(0);
			ArrayList<String> url = pddSource.get(1);
			ArrayList<String> date = pddSource.get(2);

			for (int i = 0; i < name.size(); i++) {
				System.out.println("-----------------------");
				System.out.println("搜索结果: ");
				System.out.println(name.get(i));
				System.out.println(url.get(i));
				System.out.println(date.get(i));
				System.out.println("----------------------");
			}
		}
	}

}
