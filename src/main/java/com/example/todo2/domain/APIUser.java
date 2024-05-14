package com.example.todo2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

/**
 * API 사용자 도메인
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class APIUser {

    @Id
    private String mid;
    private String mpw;

    public void changePw(String mpw){
        this.mpw = mpw;
    }
}
