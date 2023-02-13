package no.nav.familie.ef.vedtakhendelse.config

import no.nav.familie.kafka.KafkaErrorHandler
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.scheduling.annotation.EnableScheduling

@EnableKafka
@Configuration
@EnableScheduling
@Import(
    KafkaErrorHandler::class,
)
class KafkaConfig {

    @Bean
    fun kafkaAivenVedtakhendelseListenerContainerFactory(properties: KafkaProperties, kafkaErrorHandler: KafkaErrorHandler):
        ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = DefaultKafkaConsumerFactory(properties.buildConsumerProperties())
        factory.setCommonErrorHandler(kafkaErrorHandler)
        return factory
    }
}
