package com.kh.totalJpaSample.repository;


//import lombok.extern.slf4j.Slf4j;
import com.kh.totalJpaSample.entity.Cart;
import com.kh.totalJpaSample.entity.Member;
import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest  //  스프링 컨텍스트를 로드하여 테스트 환경 설정

@Transactional //데이터베이스의 논리적인 작업 단위, 모두 성공이 아니면 롤백, 단 테스트 쪽은 그냥 롤벡
//@Slf4j //로깅 데이터를 처리하기 위해 사용
//@RequiredArgsConstructor
@TestPropertySource(locations = "classpath:application-test.properties")

class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;
    //테스트에서는 생성자를 통한 주입이 불가하고, autoweired를 통해 필드로 의존성주입을 해주어야한다.


//    private  final CartRepository cartRepository;
//
//    private  final MemberRepository memberRepository;

    @PersistenceContext // JPA의 EntityManager를 주입 받음
    EntityManager em;

    //회원 엔티티 생성
     public Member createMemberInfo() {
         Member member = new Member();
         member.setUserId("jks2024");
         member.setPassword("sphb8250");
         member.setName("곰돌이사육사");
         member.setEmail("jks2024@gmail.com");
         member.setRegDate(LocalDateTime.now());
         return member;
     }

     @Test
     @DisplayName("장바구니 회원 매핑 테스트")
     public void findCartAndMemberTest() {
         Member member = createMemberInfo();
         memberRepository.save(member);

         Cart cart = new Cart();
         cart.setCartName("마켓컬리");
         cart.setMember(member);
         cartRepository.save(cart);

         em.flush(); //영속성 컨텍스트에 데이터 저장 후 트랜잭션이 끝날 때 데이터베이스에 기록
         em.clear(); // 영속성 컨텐스르를 비움.


         Cart saveCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new);
         System.out.println(saveCart);


     }
}