package com.sau.ornekwebapp.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {

    private Long id;
    private String title;
    private String text;
    private Long userId;
}
