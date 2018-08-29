package adk.lims.core.interseptor;

import adk.lims.core.entity.LoggerModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
    private final JmsTemplate jmsTemplate;
    private final String loggerDestination;
    private final ObjectMapper objectMapper;

    public LoggerInterceptor(JmsTemplate jmsTemplate,
                             @Value("${activemq.pool.queue.name.logger}") String loggerDestination,
                             ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.loggerDestination = loggerDestination;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI());
        this.jmsTemplate.convertAndSend(this.loggerDestination, this.objectMapper.writeValueAsString(new LoggerModel(request.getMethod(), request.getRequestURI())));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
        logger.info("[postHandle][" + request + "]");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null){
            ex.printStackTrace();
        }
        logger.info("[afterCompletion][" + request + "][exception: " + ex + "]");
    }

}
