package com.example.producerrabbitmqapp.payload;

import com.example.producerrabbitmqapp.enums.ActionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentActionDTO {
    private boolean action;
    private Integer studentId;

}
