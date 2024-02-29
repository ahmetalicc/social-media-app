package com.sau.ornekwebapp.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeCreateRequest {
    private Long id;
    private Long userId;
    private Long postId;

}
