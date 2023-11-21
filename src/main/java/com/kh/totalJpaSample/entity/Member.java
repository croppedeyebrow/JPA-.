package com.kh.totalJpaSample.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


//Entity란, 데이터베이스의 테이블과 매칭되는 자바 객체!
// 데이터베이스의 구조와 필드들을 표현하고, JPA를 사용하여 데이터베이스와 상호작용하는데 사용.



@Entity
//해당 클래스가 JPA 엔티티 클래스라고 정의.


@Table(name = "member")   //이름 붙히는게 관례.
//해당 클래스가 데이터베이스의 어느 테이블에 매필되는지 정의.
//클래스 이름은, 테이블 이름
//지정해주는 이름은 카멜표기법을 따르지 않음, db와 다르기 때문
//재시작을 하여도 영구히 보존되는 특성
@Getter
@Setter
@ToString



public class Member {
    @Id   //이 테이블의 pk역할, DB테이블의 Primary Key 칼럼과 매핑.
    @GeneratedValue(strategy = GenerationType.AUTO)  //스프링부트가 알고 있는 생성전략에 따라 생성
    private Long id;   //고유값으로 Long타입을 하나 만들고 들어가기.
    @Column(nullable = false) //NULL을 허용하지 않음.
    // @Column: 매핑할 데이터베이스의 칼럼 이름과 필드 변수의 이름이 다를 경우 매핑하기 위해 사용.
    private String name;
    private String password;
    @Column(unique = true) // 유일한 값이어야 함.
    private String email;
    private LocalDateTime regDate;  // 가입일
    @PrePersist
    public void prePersist() {
        regDate = LocalDateTime.now();
    }
}
