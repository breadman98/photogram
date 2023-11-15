package com.cos.photogramstart.web.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CMResponseDto<T> {
    private int errCode; // 1:성공 -1:실패
    private String message;
    private T data;
}
