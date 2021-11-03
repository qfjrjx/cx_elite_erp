package com.erp;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.erp.common.annotation.FebsShiro;
import org.springframework.boot.WebApplicationType;
import org.springframework.http.MediaType;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiufeng
 */
@FebsShiro
public class CxEliteErpShiroApplication extends WebMvcConfigurerAdapter {


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        /*
         * 1. 需要先定义一个convert转换器对象
         * 2. 配置添加fastjson的配置信息, 比如: 是否要格式化返回的json数据;
         * 3. 把配置信息添加到convert转换器对象中;
         * 4. 解决中文乱码
         * 5. 将convert添加到转换器对象当中;
         */
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //4. 解决中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        converters.add(fastJsonHttpMessageConverter);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(CxEliteErpShiroApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
