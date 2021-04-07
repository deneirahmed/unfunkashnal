package ca.sait.gifreviewr.filters;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Verifies and creates a new CSRF token before request is sent to servlet.
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 */
public class AntiCsrfFilter implements Filter {
    public AntiCsrfFilter() {
    }    

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        if (httpRequest.getMethod().equals("POST")) {
            String sessionToken = (String) httpRequest.getSession().getAttribute("token");
            String inputToken = httpRequest.getParameter("token");
            
            if (sessionToken == null || !sessionToken.equals(inputToken)) {
                httpResponse.sendError(403, "Anti-request forgery token is invalid.");
                return;
            }
        }
        
        UUID newToken = UUID.randomUUID();
        
        httpRequest.getSession().setAttribute("token", newToken.toString());
        httpRequest.setAttribute("token", newToken.toString());

        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        

    }
    
}
