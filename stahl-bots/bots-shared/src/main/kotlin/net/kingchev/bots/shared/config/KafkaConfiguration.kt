/*
 *  Stahl geist
 *  Copyright (C) 2025 kiNgchev
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.kingchev.bots.shared.config

import net.kingchev.bots.shared.*
import net.kingchev.model.bots.CrosspostingMessage
import net.kingchev.model.bots.NotificationMessage
import net.kingchev.shared.utils.buildMap
import net.kingchev.shared.utils.delegate.env
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.config.TopicConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.*
import org.springframework.kafka.listener.ContainerProperties.AckMode
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

@EnableKafka
@Configuration
@Import(
    KafkaConfiguration.Topics::class,
    KafkaConfiguration.Crossposting::class,
    KafkaConfiguration.TwitchNotification::class
)
public class KafkaConfiguration {
    public object Topics {
        @Bean
        public fun topicCrossposting(): NewTopic = TopicBuilder
            .name(CROSSPOSTING)
            .configs(topicProps())
            .build()

        @Bean
        public fun topicTwitchNotification(): NewTopic = TopicBuilder
            .name(TWITCH_NOTIFICATION)
            .configs(topicProps())
            .build()

        @Bean
        public fun topicYoutubeNotification(): NewTopic = TopicBuilder
            .name(YOUTUBE_NOTIFICATION)
            .configs(topicProps())
            .build()

    }
    public object Crossposting {
        @Bean
        public fun kafkaTemplate(): KafkaTemplate<String, CrosspostingMessage> = KafkaTemplate(producerFactory())

        @Bean
        public fun consumerFactory(): ConsumerFactory<String, CrosspostingMessage> =
            DefaultKafkaConsumerFactory(
                consumerProps(),
                ErrorHandlingDeserializer(StringDeserializer()),
                ErrorHandlingDeserializer(JsonDeserializer())
            )

        @Bean
        public fun producerFactory(): ProducerFactory<String, CrosspostingMessage> {
            return DefaultKafkaProducerFactory(producerProps())
        }

        @Bean
        public fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<String, CrosspostingMessage> =
            KafkaListenerContainerFactory<String, CrosspostingMessage>()
                .apply(kafkaListenerContainerFactory(consumerFactory()))
    }

    public object TwitchNotification {
        @Bean
        public fun kafkaTemplateTwitch(): KafkaTemplate<String, NotificationMessage> =
            KafkaTemplate(producerFactoryTwitch())

        @Bean
        public fun consumerFactoryTwitch(): ConsumerFactory<String, NotificationMessage> =
            DefaultKafkaConsumerFactory(
                consumerProps(),
                ErrorHandlingDeserializer(StringDeserializer()),
                ErrorHandlingDeserializer(JsonDeserializer())
            )

        @Bean
        public fun producerFactoryTwitch(): ProducerFactory<String, NotificationMessage> =
            DefaultKafkaProducerFactory(producerProps())

        @Bean
        public fun kafkaListenerContainerFactoryTwitch(): KafkaListenerContainerFactory<String, NotificationMessage> =
            KafkaListenerContainerFactory<String, NotificationMessage>()
                .apply(kafkaListenerContainerFactory(consumerFactoryTwitch()))
    }
}

private val bootstrapServers: List<String> by env("KAFKA_BOOTSTRAP_SERVERS")
{ servers -> servers.split(";") }

private fun topicProps(): Map<String, String> = buildMap {
    TopicConfig.MAX_MESSAGE_BYTES_CONFIG to "104857600"
}

private fun consumerProps(): MutableMap<String, Any> = buildMap {
    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers
    ConsumerConfig.GROUP_ID_CONFIG to "stahl-group"
    ConsumerConfig.CLIENT_ID_CONFIG to "stahl"
    ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest"
    ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to false
    ConsumerConfig.MAX_POLL_RECORDS_CONFIG to 1
    ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG to 36000
    ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG to "104857600"
    ConsumerConfig.FETCH_MAX_BYTES_CONFIG to "104857600"
    JsonSerializer.ADD_TYPE_INFO_HEADERS to false
    JsonDeserializer.TRUSTED_PACKAGES to "net.kingchev.model"
}

private fun producerProps(): MutableMap<String, Any> = buildMap {
    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers
    ProducerConfig.RETRIES_CONFIG to 2
    ProducerConfig.CLIENT_ID_CONFIG to "stahl"
    ProducerConfig.MAX_REQUEST_SIZE_CONFIG to "104857600"
    ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG to 60000
    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java
    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
}

private fun <E> kafkaListenerContainerFactory(consumer: ConsumerFactory<String, E>): KafkaListenerContainerFactoryBuilder<String, E> = {
    consumerFactory = consumer
    containerProperties.ackMode = AckMode.MANUAL
    containerProperties.pollTimeout = 1800000
    containerProperties.clientId = "stahl"
    setConcurrency(2)
}