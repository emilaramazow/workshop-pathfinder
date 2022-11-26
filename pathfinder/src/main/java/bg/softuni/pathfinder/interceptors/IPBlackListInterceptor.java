package bg.softuni.pathfinder.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class IPBlackListInterceptor implements HandlerInterceptor {

    private List<String> blackListedIPAddresses = new ArrayList<>();

    public IPBlackListInterceptor () {
        blackListedIPAddresses.add("0.0.0.0.0.0.0.1");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String IPAddress = request.getRemoteAddr();

        System.out.println(IPAddress);

        if (blackListedIPAddresses.contains(IPAddress)) {
            response.sendRedirect("/error");
        }

        return true;
    }
}
