package yte.pbs2024.user.repository;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomSecurityContextRepository implements SecurityContextRepository {


    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        return SecurityContextHolder.getContext();
    }
    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        if (request != null) {
            request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", context);
        }
    }
    @Override
    public boolean containsContext(HttpServletRequest request) {
        return false;
    }
}