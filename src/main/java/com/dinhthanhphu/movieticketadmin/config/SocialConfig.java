package com.dinhthanhphu.movieticketadmin.config;

import com.dinhthanhphu.movieticketadmin.dao.AppUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.sql.DataSource;

@Configuration
@EnableSocial
// Load to Environment.
@PropertySource("classpath:social-cfg.properties")
public class SocialConfig  implements SocialConfigurer {

    private boolean autoSignUp = false;

    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private DataSource dataSource;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {

        try {
            this.autoSignUp = Boolean.parseBoolean(environment.getProperty("social.auto-signup"));
        } catch (Exception e) {
            this.autoSignUp = false;
        }

        FacebookConnectionFactory ffactory = new FacebookConnectionFactory(//
                environment.getProperty("facebook.app.id"), //
                environment.getProperty("facebook.app.secret"));

        ffactory.setScope(environment.getProperty("facebook.scope"));
        connectionFactoryConfigurer.addConnectionFactory(ffactory);
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator,

                Encryptors.noOpText());
        if (autoSignUp) {
            // After logging in to social networking.
            // Automatically creates corresponding APP_USER if it does not exist.
            ConnectionSignUp connectionSignUp = new ConnectionSignUpImpl(appUserDAO);
            usersConnectionRepository.setConnectionSignUp(connectionSignUp);
        } else {
            // After logging in to social networking.
            // If the corresponding APP_USER record is not found.
            // Navigate to registration page.
            usersConnectionRepository.setConnectionSignUp(null);
        }
        return usersConnectionRepository;
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, //
                                               ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
