package br.com.provider.jwt.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.provider.jwt.model.Role;
import br.com.provider.jwt.model.User;

public class UserDeserializer extends StdDeserializer<User> {

    private static final Logger logger = LoggerFactory.getLogger(UserDeserializer.class);
    
    private final SimpleDateFormat format;
    
    public UserDeserializer() {
        super(UserDeserializer.class);
        format = new SimpleDateFormat("yyyyMMddHHmmss");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    @Override
    public User deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {

        JsonNode json = parser.getCodec().readTree(parser);
        final JsonNode claims = json.path("claims");
        
        final JsonNode userNode = claims.path("user");
        final User user = new User();
        user.setUsername(userNode.get("username").asText());
        user.setEmail(userNode.get("email").asText());
        user.setFirstName(userNode.get("firstName").asText());
        user.setLastName(userNode.get("lastName").asText());
        user.setDocument(userNode.get("document").asText());
        user.setDocumentType(userNode.get("documentType").asText());
        user.setCanLogin(userNode.get("canLogin").asBoolean());
        
        try {
            user.setLastAccessDate(format.parse(userNode.get("lastAccessDate").asText()));
        } catch (ParseException pe) {
            logger.error(pe.getMessage(), pe);
        }
        
        /*final JsonNode contextNode = claims.path("context");
        user.setContext(new User.Context(
                contextNode.get("operator").asText(),
                contextNode.get("channel").asText(),
                contextNode.get("session").asText(),
                contextNode.get("businessDate").asText())
        );*/
        
        final JsonNode roleNode = claims.path("roles");
        for(JsonNode node : roleNode) {
            
            Role role = new Role();
            role.setAlias(node.get("securityItemAlias").asText());
            
            user.addRole(role);
        }
        
        return user;
    }

}
