package com.erp;

import com.erp.common.annotation.FebsShiro;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author MrBird
 */
@FebsShiro
public class CxEliteErpShiroApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CxEliteErpShiroApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
