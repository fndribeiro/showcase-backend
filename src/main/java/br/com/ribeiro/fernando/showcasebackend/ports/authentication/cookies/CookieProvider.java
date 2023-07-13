package br.com.ribeiro.fernando.showcasebackend.ports.authentication.cookies;

import java.util.Base64;

import jakarta.servlet.http.Cookie;

public class CookieProvider {
	
    public Cookie create(String name, String value) {
    	
    	String encodedValue = encode(value);
    	
        var cookie = new Cookie(name, encodedValue);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-1);
        
        return cookie;
    }
    
    public Cookie delete(Cookie cookie) {
    	
    	 cookie.setValue("");
         cookie.setPath("/");
         cookie.setMaxAge(0);
         
         return cookie;
    }

    private String encode(String value) {
        return Base64
        		.getUrlEncoder()
                .encodeToString(value.getBytes());
    }

    public byte[] decode(Cookie cookie) {
        return Base64
        		.getUrlDecoder()
        		.decode(cookie.getValue());
    }
	
}
