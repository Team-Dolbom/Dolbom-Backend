package com.example.dolbom.domain.auth.present.dto.request;

import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MessageRequest {
    private String to;
    private String content;
}