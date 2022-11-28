package ru.sobinda.conditional.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sobinda.conditional.profile.DevProfile;
import ru.sobinda.conditional.profile.ProductionProfile;
import ru.sobinda.conditional.profile.SystemProfile;


@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(
            name = "netology.profile.dev",
            havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(
            name = "netology.profile.dev", //поиск в файле application.properties
            havingValue = "false", //ищем значения поля на совпадение
            matchIfMissing = true) // если такого поля нет, то принимаем данное значение
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
