package net.kingchev.serafim.quartz

import com.github.twitch4j.ITwitchClient
import net.kingchev.serafim.config.SerafimProperties
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
public class SendMessageJob(private val twitchClient: ITwitchClient, private val props: SerafimProperties) {
    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.MINUTES)
    private fun sendMessage() {
        props.twitchChannel.forEach {
            twitchClient.chat.sendMessage(
                it,
                "Не забывайте подписываться на наш телеграм канал! https://t.me/k1ngchev"
            )
        }
    }
}