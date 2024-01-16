package com.nowon.bul.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class MybatisConfig {
	
	private final ApplicationContext applicationContext;
	private final DataSource dataSource; // jpa 같이쓰면 별도로 bean 생성 필요없음
	
	 @Bean
	 SqlSessionTemplate sqlSession() throws Exception {
	    return new SqlSessionTemplate(sqlSessionFactory());
	 }
	 @Bean
	 SqlSessionFactory sqlSessionFactory() throws Exception {
		 SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		 factoryBean.setDataSource(dataSource);
		 //mapper-DAO
		 String locationPattern = "classpath:/static/sql/**/*-mapper.xml";
		 //new PathMatchingResourcePatternResolver().getResources(null);
		 Resource[] mapperLocations=applicationContext.getResources(locationPattern);
		 factoryBean.addMapperLocations(mapperLocations);//xml파일 위치
		 
		 factoryBean.setConfiguration(myBatisConfig());
		 return factoryBean.getObject();
	 }
	 
	 @ConfigurationProperties(prefix = "mybatis.configuration")
	 @Bean
	 org.apache.ibatis.session.Configuration myBatisConfig() {
		return new org.apache.ibatis.session.Configuration();
	}
	 
}