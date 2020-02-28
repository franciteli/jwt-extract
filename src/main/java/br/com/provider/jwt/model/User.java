package br.com.provider.jwt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.provider.jwt.service.UserDeserializer;

@JsonDeserialize(using = UserDeserializer.class)
public class User implements Serializable {
    
    public static class Context implements Serializable {
        
        private static final long serialVersionUID = 10L;
        
        private final String operator;
        private final String channel;
        private final String session;
        private final String businessDate;
        
        public Context(String operator, String channel, String session, String businessDate) {
            this.operator = operator;
            this.channel = channel;
            this.session = session;
            this.businessDate = businessDate;
        }
        
        public String getOperator() {
            return operator;
        }

        public String getChannel() {
            return channel;
        }

        public String getSession() {
            return session;
        }

        public String getBusinessDate() {
            return businessDate;
        }
        
    }

    private static final long serialVersionUID = 1L;
    
    private Boolean canLogin;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String document;
    private String documentType;
    private Date lastAccessDate;
    private Context context;
    
    private List<Role> roles;

    public User() {
        roles = new ArrayList<>();
    }
    
    public Boolean getCanLogin() {
        return canLogin;
    }

    public void setCanLogin(Boolean canLogin) {
        this.canLogin = canLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Date getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(Date lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = new ArrayList<>(roles);
    }
    
    public void addRole(Role role) {
        this.roles.add(role);
    }
    
    public boolean hasRole(Role role) {
        return this.roles.contains(role);
    }
    
    public boolean hasRole(String alias) {
        return hasRole(new Role(alias));
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
