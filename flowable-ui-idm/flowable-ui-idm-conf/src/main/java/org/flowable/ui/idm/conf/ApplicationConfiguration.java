/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.ui.idm.conf;

import org.flowable.ui.common.service.idm.RemoteIdmServiceImpl;
import org.flowable.ui.idm.properties.FlowableIdmAppProperties;
import org.flowable.ui.idm.security.CustomDaoAuthenticationProvider;
import org.flowable.ui.idm.security.CustomLdapAuthenticationProvider;
import org.flowable.ui.idm.security.CustomPersistentRememberMeServices;
import org.flowable.ui.idm.security.UserDetailsService;
import org.flowable.ui.idm.servlet.ApiDispatcherServletConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Application configuration for Flowable IDM with Keycloak SSO integration.
 *
 * In Keycloak mode, we exclude the local authentication components because
 * all authentication is handled by Keycloak via KeycloakCookieFilter.
 * The excluded classes are:
 * - RemoteIdmServiceImpl - replaced by KeycloakServiceImpl
 * - CustomPersistentRememberMeServices - not needed, Keycloak handles sessions
 * - CustomDaoAuthenticationProvider - not needed, Keycloak handles auth
 * - CustomLdapAuthenticationProvider - not needed, Keycloak handles auth
 * - UserDetailsService - not needed, Keycloak provides user details
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(FlowableIdmAppProperties.class)
@ComponentScan(basePackages = {
    "org.flowable.ui.common.conf",
    "org.flowable.ui.idm.conf",
    "org.flowable.ui.idm.security",
    "org.flowable.ui.idm.idm",
    "org.flowable.ui.idm.service"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = RemoteIdmServiceImpl.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CustomPersistentRememberMeServices.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CustomDaoAuthenticationProvider.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CustomLdapAuthenticationProvider.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = UserDetailsService.class)
    })
public class ApplicationConfiguration {

    @Bean
    public ServletRegistrationBean apiServlet(ApplicationContext applicationContext) {
        AnnotationConfigWebApplicationContext dispatcherServletConfiguration = new AnnotationConfigWebApplicationContext();
        dispatcherServletConfiguration.setParent(applicationContext);
        dispatcherServletConfiguration.register(ApiDispatcherServletConfiguration.class);
        DispatcherServlet servlet = new DispatcherServlet(dispatcherServletConfiguration);
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(servlet, "/api/*");
        registrationBean.setName("Flowable IDM App API Servlet");
        registrationBean.setLoadOnStartup(1);
        registrationBean.setAsyncSupported(true);
        return registrationBean;
    }

}
