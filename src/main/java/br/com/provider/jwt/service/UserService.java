package br.com.provider.jwt.service;

import static org.springframework.util.StringUtils.isEmpty;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.provider.jwt.model.User;

@Service
public class UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    public User getUser(String jwt) {
        
        if(isEmpty(jwt)) {
            return null;
        }
        jwt = jwt.replace("Bearer", "").trim();
        
        User user = null;
        try {
            String body = decode(jwt.split("\\.")[1]);
            user = new ObjectMapper().readValue(body, User.class);
        } catch (UnsupportedEncodingException uee) {
            logger.error(uee.getMessage(), uee);
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
        }
        
        return user;
    }
    
    public User getUser(HttpServletRequest servlet) {
        return getUser(servlet.getHeader("Authorization"));
    }
    
    private String decode(String encrypted) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(encrypted), "UTF-8");
    }
    
}
