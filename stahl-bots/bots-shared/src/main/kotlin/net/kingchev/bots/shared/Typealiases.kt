package net.kingchev.bots.shared

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory

public typealias KafkaListenerContainerFactory<K, V> = ConcurrentKafkaListenerContainerFactory<K, V>
public typealias KafkaListenerContainerFactoryBuilder<K, V> = KafkaListenerContainerFactory<K, V>.() -> Unit