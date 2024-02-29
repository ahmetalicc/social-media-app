package com.sau.ornekwebapp.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PostUpdateRequest {
    private String text;
    private String title;

}
