package mybatis.mappers;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.nowon.bul.stock.service.ProductMapper")
public class MyBatisConfig {
    // MyBatis 설정 (SqlSessionFactory, SqlSessionTemplate 등)을 추가할 수 있음
}