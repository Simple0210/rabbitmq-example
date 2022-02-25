package com.example.producerrabbitmqapp.service;

import com.example.producerrabbitmqapp.config.RabbitMQConfiguration;
import com.example.producerrabbitmqapp.entity.Student;
import com.example.producerrabbitmqapp.entity.StudentAction;
import com.example.producerrabbitmqapp.enums.ActionEnum;
import com.example.producerrabbitmqapp.payload.StudentActionDTO;
import com.example.producerrabbitmqapp.repository.StudentActionRepository;
import com.example.producerrabbitmqapp.repository.StudentRepository;
import com.example.producerrabbitmqapp.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentActionServiceImpl implements StudentActionService {

    private final StudentRepository studentRepository;
    private final StudentActionRepository studentActionRepository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public ApiResponse saveNewStudentAction(StudentActionDTO studentActionDTO) {

        Optional<Student> optionalStudent = studentRepository.findById(studentActionDTO.getStudentId());
        if (optionalStudent.isPresent()) {
            StudentAction studentAction = new StudentAction(
                    /*
                    STUDENTDTO DA KELGAN BOOLEAN TRUE BO`LSA INCOME YA`NI KIRGAN, FALSE BO`LSA OUTCOME YA`NI CHIQQAN HISOBLANADI
                     */
                    (studentActionDTO.isAction()) ? ActionEnum.INCOME : ActionEnum.OUTCOME,
                    optionalStudent.get()
            );

            /*
            SAQLANGAN ACTION CONSUMER API GA JO`NATISH UCHUN OLINYAPTI
             */
            StudentAction savedAction = studentActionRepository.save(studentAction);
            /*
            SAQLANGAN ACTIONNI ID ISI BILAN BERIB YUBORMASLIK UCHUN IDISIZ ACTION YASALAYAPTI
             */
            StudentAction studentActionForSend = new StudentAction(savedAction.getAction(), savedAction.getCreatedAt(), savedAction.getStudent());

            /*
            RABBITGA JO`NATILYAPTI BU YERDA TOPIC EXCHANGE BO`LGANI UCHUN "routing.green" va "routing.*"   KEYLARI BILAN BOG`LANGAN IKKIDA QUEUEGA YUBORILADI
             */
            rabbitTemplate.convertAndSend(RabbitMQConfiguration.ACTION_EXCHANGE, "routing.green", studentActionForSend);

            /*
            RABBITTEMPLATE NING USHBU METODI RABBITGA JO`NATILGAN HABARNI ESHITGAN CONSUMERNING JAVOBINI QAYTARIB BERADI
             */
//            Object responseObject = rabbitTemplate.convertSendAndReceive(directExchange.getName(),RabbitMQConfiguration.ACTION_ROUTING_KEY, studentActionForSend);

            return new ApiResponse("Student Action muvaffaqiyatli saqlandi va RABBIT MQ ga jo`natildi!", true);
        } else {
            return new ApiResponse("Action saqlash uchun Student topilmadi. Student ID xato!", false);
        }
    }
}
