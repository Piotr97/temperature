package pl.com.wpi.task1.config;

import com.antkorwin.xsync.XSync;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public XSync<Integer> xSync(){
        return new XSync<>();
    }
}
