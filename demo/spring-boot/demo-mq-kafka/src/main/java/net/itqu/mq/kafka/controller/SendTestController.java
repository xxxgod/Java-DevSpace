package net.itqu.mq.kafka.controller;

import net.itqu.mq.kafka.constants.KafkaConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
public class SendTestController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("/test1")
    public void test() {
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(KafkaConsts.TOPIC_TEST, "hello,kafka...");
        log.info("发送消息==========>");
    }


}
