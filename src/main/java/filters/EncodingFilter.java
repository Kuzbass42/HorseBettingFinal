package filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter extends HttpFilter{
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        encoding = filterConfig.getInitParameter("characterencoding");
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
    }
}
