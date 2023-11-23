package com.kh.totalJpaSample.repository;


import com.kh.totalJpaSample.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

// DB와 상호작용하기 위한 메서드를 정의하는 인터페이스!!
// 데이터베이스에 접근하고 엔티티의 영속성 관리를 담당




//네이밍 규칙에 의해서 API를 작성하면, 틀에 맞는 쿼리문을 하이버네이트(jpa의 구현체)가 구현 해줌.
//Long이 있는 자리 : pk값의 타입을..아이디 pk값
//Optional은 값이 존재할 수도 있고, 없을 수도 있는 상황에서 사용,
//코드에서 NullPointerException과 같은 예외를 방지.

public interface MemberRepository  extends JpaRepository <Member,Long>{
    Optional<Member> findByEmail(String email); //완벽한 카멜표기법으로 만들어야 함.
    Member findByPassword(String pwd);
    Member findByEmailAndPassword(String email, String Pwd);
     boolean existsByEmail(String email);
    //컬럼이름규칙을 무조건 지켜야 함.
    //상속받은 얘가 구현을 해주어야 하는데, jpa에서는 Hibernate가 담당.
    //jpa에 내부적으로 있는, Hibernate가 구현.
}
