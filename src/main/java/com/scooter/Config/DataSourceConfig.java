package com.scooter.Config;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder= DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        String root=getParent(System.getProperty("user.dir"));
        String dir_name="backend";
        dataSourceBuilder.url("jdbc:sqlite:"+root+dir_name+"/DB/scooter.DB");
        dataSourceBuilder.username("");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

    private static String getParent(String url){
        if(url==null) return "";
        url=url.replace('\\','/');

        int endIndex=url.lastIndexOf('/')+1;
        url=url.substring(0,endIndex);


        return url;
    }
}
