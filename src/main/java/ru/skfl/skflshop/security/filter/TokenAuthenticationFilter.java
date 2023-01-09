package ru.skfl.skflshop.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.skfl.skflshop.dto.SignInForm;
import ru.skfl.skflshop.entities.User;
import ru.skfl.skflshop.repositories.UserRepository;
import ru.skfl.skflshop.security.details.ShopUserPrincipal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String TOKEN = "token";
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper, UserRepository userRepository) {
        super(authenticationManager);
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            BufferedReader br = request.getReader();
            String[] reqString = br.readLine().split("&");
            reqString[0] = reqString[0].replaceAll("%40", "@");
            SignInForm form = new SignInForm(reqString[0].substring("email=".length()), reqString[1].substring("password=".length()));
            System.err.println(form);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());
            return super.getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ShopUserPrincipal userPrincipal = (ShopUserPrincipal) authResult.getPrincipal();
        User user = userPrincipal.getUser();
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        userRepository.save(user);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setHeader("Set-Cookie", "Token=" + token+"; Max-Age=20000; Path=/");
        response.sendRedirect("/profile");
        objectMapper.writeValue(response.getWriter(), Collections.singletonMap(TOKEN, token));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendRedirect("/signIn?error");
    }
}
