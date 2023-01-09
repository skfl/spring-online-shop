package ru.skfl.skflshop.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.skfl.skflshop.entities.User;
import ru.skfl.skflshop.repositories.UserRepository;
import ru.skfl.skflshop.security.configurations.SecurityConfig;
import ru.skfl.skflshop.security.details.ShopUserPrincipal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
public class TokenAuthorizationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals(SecurityConfig.LOGIN_FILTER_PROCESSES_URL) || request.getRequestURI().equals("/signIn")) {
            filterChain.doFilter(request, response);
        } else {
            Cookie tokenCookie = null;
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("Token")) {
                    tokenCookie = cookie;
                }
            }
            if (tokenCookie != null) {
                String token = tokenCookie.getValue();
                Optional<User> user = userRepository.findByToken(token);

                if (user.isPresent()) {
                    ShopUserPrincipal userPrincipal = new ShopUserPrincipal(user.get());
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(token, null, userPrincipal.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } else {
                    System.err.println("WRONG TOKEN");
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    objectMapper.writeValue(response.getWriter(), Collections.singletonMap("error", "there is no user with such token"));
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
