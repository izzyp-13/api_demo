package personal.israel.apidemo.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessLogFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger("ACCESS_LOG");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        long startTime = System.currentTimeMillis();

        chain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;
        int status = ((HttpServletResponse) response).getStatus();

        log.info("{} {} -> {} ({} ms)",
                req.getMethod(),
                req.getRequestURI(),
                status,
                duration);
    }
}
