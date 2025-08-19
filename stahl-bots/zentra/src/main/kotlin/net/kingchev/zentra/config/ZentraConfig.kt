package net.kingchev.zentra.config

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.kingchev.zentra.ZentraClient
import net.kingchev.zentra.telegram.ZentraClassManager
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["net.kingchev.zentra"])
@EnableConfigurationProperties(ZentraProperties::class)
public class ZentraConfig(private val props: ZentraProperties) {
    @Bean
    public fun zentraClient(classManager: ZentraClassManager): ZentraClient {
        val bot = ZentraClient(props.token)
        {
            identifier = "zentra-bot"
            this.classManager = classManager
        }

        GlobalScope.launch { bot.handleUpdates() }
        return bot
    }
}
