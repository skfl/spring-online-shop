package ru.skfl.skflshop.services.implementations;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class TokenService {

    public String getTokenFromCookies(Cookie[] cookies) {
        Cookie token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Token")) {
                token = cookie;
                break;
            }
        }
        if (token != null) {
            return token.getValue();
        } else {
            return "";
        }
    }
}
