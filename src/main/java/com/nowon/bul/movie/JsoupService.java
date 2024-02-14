package com.nowon.bul.movie;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


@Service
public class JsoupService {
	
	private String movie_image_url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=";
	
    public String getMoviePoster(String name) {
    	movie_image_url = movie_image_url+name;
    	System.out.println("movie_image_url : "+movie_image_url);
    	
        Document document = null;
        	try {
        		// 바로 주소에 값을 가져오다보면 Read time out 오류가 나올 수 있어서 timeout(시간)을 줘서 잠깐 대기시간
				document = Jsoup.connect(movie_image_url).timeout(10000).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	System.out.println("name = "+name);
        	// 블로그에서 가져올 목록 - 링크, 제목, 내용, 등록일자
        	// id값으로 찾을 수 있음
        	Elements elements = document.select("a img[alt="+name+"]._img");
        	String url = null;
			for(Element element : elements) {
				url = element.attr("abs:src");
			}
		System.out.println(url);
        return url;
    }
}
