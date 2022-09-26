package com.ll.exam.spring_batch_exam.app.member;

import com.ll.exam.spring_batch_exam.app.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;


@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@ToString(callSuper = true)
public class Member extends BaseEntity {
    private String username;
    private String password;
    private String email;
}
