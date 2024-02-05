package com.nowon.bul.chatbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.nowon.bul.chatbot.DeptEntityRepository;
import com.nowon.bul.chatbot.EmpEntityRepository;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class KomoramConfig {
	
	private final DeptEntityRepository deptEntityRepository;
	private final EmpEntityRepository empEntityRepository;
	
	private String USER_DIC="user.dic";
	
	@Bean
	Komoran komoran() throws IOException {
		//파일에 DB의 정보 셋
		createDIC();
		Komoran komoran=new Komoran(DEFAULT_MODEL.FULL);
		komoran.setUserDic(USER_DIC);
		return komoran;
	}

	private void createDIC() throws IOException {
		//ClassPathResource cpr=new ClassPathResource("static/files/");
		//File file=new File(cpr.getFile(),USER_DIC);
		File file=new File(USER_DIC);
		if(!file.exists()) file.createNewFile();
		
		Set<String> nnpset=new HashSet<>();
		////////////////////////////////////////
		BufferedReader br=new BufferedReader( new FileReader(file) );
		String data=null;
		while((data=br.readLine()) !=null) {
			if(data.startsWith("#")) continue;
			// 매니져\tNNP
			nnpset.add( data.split("\\t")[0] );
		}
		br.close();
		
		//부서명 등록
		deptEntityRepository.findAll().forEach(e->{
			nnpset.add(e.getName());
		});
		//사원명 등록
		empEntityRepository.findAll().forEach(e->{
			nnpset.add(e.getName());
		});
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(file));
		
		nnpset.forEach(name->{
			try {
				bw.write(name+"\tNNP\n");
			} catch (IOException e1) {}
		});
		bw.close();
		
	}

}
