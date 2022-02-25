package com.example.consumer_rabbitmqapp.controller;

import com.example.consumer_rabbitmqapp.config.RabbitMQConfiguration;
import com.example.consumer_rabbitmqapp.entity.StudentAction;
import com.example.consumer_rabbitmqapp.service.StudentActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/action")
@RequiredArgsConstructor
public class StudentActionController {

    private final StudentActionService studentActionService;

    @PostMapping("/save")
    /*
    RABBIT MQ DAGI "action_queue_all" DEGAN QUEUE GA KELGAN HABARLARNI OLAYAPTI VA DB GA SAQLASH UCHUN SERVICE GA BERIB YUBORAYAPTI
     */
    @RabbitListener(queues = RabbitMQConfiguration.ACTION_QUEUE_ALL)
    public void saveActionFromQueue(@RequestBody StudentAction studentAction) {
        studentActionService.saveNewAction(studentAction);

        /*
        AGAR PRODUCER API CONVERTSENDANDRECEIVE METODI ORQALI HABAR JO`NATSA UNGA STRINGDA JAVOB QAYTARISH UCHUN
         */
//        return "RAHMAT! STUDENT ACTIONNI O`ZIMNING DB GA SAQLADIM!";
    }
}
