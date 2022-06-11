package com.geek;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitHubOAuth2User implements OAuth2User {
    private List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("RoLE_USER");
    private Map<String, Object> attributes;
    private String id;
    private String name;
    private String login;
    private String email;
    @Override
    public Map<String, Object> getAttributes() {
        if(this.authorities == null){
            this.attributes = new HashMap<>();
            this.attributes.put("id",this.getId());
            this.attributes.put("name",this.getName());
            this.attributes.put("login",this.getLogin());
            this.attributes.put("email",this.getEmail());
        }
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }




    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
