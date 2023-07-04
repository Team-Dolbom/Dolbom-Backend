package com.example.dolbom.domain.auth.present.dto.request;

import com.example.dolbom.domain.auth.present.dto.request.MessageRequest;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SmsRequest {
    private String type;
    private String contentType;
    private String countryCode;
    private String from;
    private String content;
    private List<MessageRequest> messages;
}