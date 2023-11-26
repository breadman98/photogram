package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.web.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
    private boolean PageOwnerState;
    private int imageCount;
    private User user;
}