package com.erp.common.shutdown;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author qiufeng
 */
@Slf4j
@Component
public class FebsShutDownHook {

    @EventListener(classes = {ContextClosedEvent.class})
    public void onFebsApplicationClosed(@NonNull ApplicationEvent event) {
        log.info("ERP系统已关闭，Bye");
    }
}
