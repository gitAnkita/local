package com.paytm.local.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

//    @KafkaListener(topics = "test",containerFactory = "kafkaListenerContainerFactory")
//    public void listenWithHeaders(
//            ConsumerRecord<String, ?> record, Acknowledgment ack) {
//        System.out.println(
//                "Received Message: " + record.value());
//
//        ack.acknowledge();
//
//    }

}
