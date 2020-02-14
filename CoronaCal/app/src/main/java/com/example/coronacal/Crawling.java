package com.example.coronacal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Crawling {

    //파싱할 홈페이지 URL("http://shecs.co.kr/"), 보여줄 TextView
    public  String  getSite(String site, String cssQuery){
         String htmlContentInStringFormat = "";

                try {
                    Document doc = Jsoup.connect(site).get();

                    //테스트1
                    Elements titles = doc.select(cssQuery);
                    System.out.println("-------------------------------------------------------------");
                    for (Element e : titles) {
                        System.out.println("title: " + e.text());
                        htmlContentInStringFormat += e.text().trim() + "\n";
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return htmlContentInStringFormat;

    }

}
