package fr.isep.userservice.application.security;

import org.keycloak.adapters.AdapterTokenStore;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.OAuthRequestAuthenticator;
import org.keycloak.adapters.RequestAuthenticator;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.authentication.SpringSecurityRequestAuthenticator;
import org.keycloak.adapters.springsecurity.authentication.SpringSecurityRequestAuthenticatorFactory;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class KeycloackAuthenticationProcessingFilterPostProcessor implements BeanPostProcessor {

    private void process(KeycloakAuthenticationProcessingFilter filter) {
        filter.setRequestAuthenticatorFactory(new SpringSecurityRequestAuthenticatorFactory() {
            @Override
            public RequestAuthenticator createRequestAuthenticator(HttpFacade facade, HttpServletRequest request,
                                                                   KeycloakDeployment deployment, AdapterTokenStore tokenStore, int sslRedirectPort) {
                return new SpringSecurityRequestAuthenticator(facade, request, deployment, tokenStore, sslRedirectPort) {

                    @Override
                    protected OAuthRequestAuthenticator createOAuthAuthenticator() {
                        return new OAuthRequestAuthenticator(this, facade, deployment, sslRedirectPort, tokenStore) {

                            @Override
                            protected String getRequestUrl() {
                                System.out.println("Test bonjour");
                                return "https://user-service.cubetech-app.fr";
                            }
                        };
                    }

                };
            }
        });
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof KeycloakAuthenticationProcessingFilter) {
            process(((KeycloakAuthenticationProcessingFilter) bean));
        }
        return bean;
    }
}