package com.nowon.bul.movie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MovieApiService {

	private final KobisOpenAPIRestService kobisOpenAPIRestService;
	private final JsoupService jsoupService;
	
	public List<MovieListDTO> getDailyBoxOffice() throws OpenAPIFault, Exception {
		
		List<MovieListDTO> list = new ArrayList<>();
		
		//일별 박스오피스 구하기
		LocalDate day = LocalDate.now().minusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String formattedDate = day.format(formatter);
		
		JSONObject jsonObject = new JSONObject(
				kobisOpenAPIRestService.getDailyBoxOffice(true, formattedDate, null, null, null, null)
				);
		
		JSONArray dailyBoxOfficeList = jsonObject.getJSONObject("boxOfficeResult").getJSONArray("dailyBoxOfficeList");
		
		IntStream.range(0, dailyBoxOfficeList.length()).boxed().forEach(i -> {
	            try {
	            	list.add(MovieListDTO.builder()
	            			.rank(dailyBoxOfficeList.getJSONObject(i).getString("rank"))
	            			.movieCd(dailyBoxOfficeList.getJSONObject(i).getString("movieCd"))
	            			.movieNm(dailyBoxOfficeList.getJSONObject(i).getString("movieNm"))
	            			.openDt(dailyBoxOfficeList.getJSONObject(i).getString("openDt"))
	            			.build());
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	        });
		for(MovieListDTO dto : list) {
			System.out.println(dto);
		}
		
		return list;
	}
	
	
	public MovieInfoDTO getMovieInfo(String movieCd) throws OpenAPIFault, Exception {
		
		JSONObject jsonObject = new JSONObject(
				kobisOpenAPIRestService.getMovieInfo(true, movieCd)
				);
		
		JSONObject movieInfo = jsonObject.getJSONObject("movieInfoResult").getJSONObject("movieInfo");
		JSONArray genres = movieInfo.getJSONArray("genres");
		
		
		StringBuilder nationesBuilder = new StringBuilder();
		IntStream.range(0, genres.length()).boxed().forEach(i -> {
            try {
            	nationesBuilder.append(genres.getJSONObject(i).getString("genreNm")+" "); // 사극 드라마
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
		return MovieInfoDTO.builder()
				.movieNm(movieInfo.getString("movieNm"))
				.showTm(movieInfo.getString("showTm")+"분")
				.openDt(movieInfo.getString("openDt"))
				.nations(nationesBuilder.toString())
				.posterUrl(getMoviePosterUrl(movieInfo.getString("movieNm")))
				.build();
		
	}
	
	public String getMoviePosterUrl(String name) {
		return jsoupService.getMoviePoster(name);
	}
}
