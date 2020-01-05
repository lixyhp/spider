package com.lixyhp.spider;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
	static Logger logger = LogManager.getLogger(App.class.getName());
	
	public static void main(String[] args) {
		recursion("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html");
		
	}
	 
	public static void recursion(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			Elements links = doc.select("a");
			if (links.size() <= 1)
				return;
			for (Element a : links) {
				if(!StringUtils.isNumeric(a.text()) &&
						!a.attr("href").equals("http://www.miibeian.gov.cn/")) {
					logger.debug(a.text() + " : " + a.attr("href"));
					
					//recursion(a.absUrl("href"));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
