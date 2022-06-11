package com.geek;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .customUserType(GitHubOAuth2User.class,"github")
                .and()
                .loginProcessingUrl("/authorization_code");
    }

    /**java配置，能够更细粒度的控制权限
     *
     * @return
     */
    @Bean
    public ClientRegistrationRepository registrationRepository(){
        return new InMemoryClientRegistrationRepository(githubClientRegistration());
    }
    private ClientRegistration githubClientRegistration(){
        return ClientRegistration.withRegistrationId("github").clientId("86b046b4aabf519c7e0d")
                .clientSecret("5feaba2acdd49752032f2e1daf73f63db91a56d9")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .userNameAttributeName("id")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate("http://localhost:8080/authorization_code")
                .scope("read:user")
                .authorizationUri("https://github/com/login/oauth/authorize")
                .tokenUri("https://github.com/login/oauth.access_token")
                .userInfoUri("https://api.github.com/user")
                .clientName("GitHub")
                .build();
    }
}
