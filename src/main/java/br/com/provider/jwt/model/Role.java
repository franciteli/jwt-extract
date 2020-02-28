package br.com.provider.jwt.model;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class Role {
    
    private String alias;

    public Role() {}
    
    public Role(String alias) {
        this();
        this.alias = alias;
    }
    
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o) {
        
        if(o == null) {
            return false;
        }
        
        if(this == o) {
            return true;
        }
        
        return (o instanceof Role && this.alias.equals(((Role) o).getAlias()));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.alias);
        return hash;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
}
