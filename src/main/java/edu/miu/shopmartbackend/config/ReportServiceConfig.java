package edu.miu.shopmartbackend.config;

import edu.miu.shopmartbackend.util.ReportMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportServiceConfig {

//    @Bean
//    public ReportRepository reportRepository() {
//        return new ReportRepositoryImpl();
//    }

    @Bean
    public ReportMapper reportMapper() {
        return Mappers.getMapper(ReportMapper.class);
    }
}

