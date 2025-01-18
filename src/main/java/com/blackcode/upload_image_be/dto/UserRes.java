package com.blackcode.upload_image_be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRes {
    private Long userId;

    private String userName;

    private String userImage;

    private String userDesc;
}
