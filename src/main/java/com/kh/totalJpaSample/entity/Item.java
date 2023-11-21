package com.kh.totalJpaSample.entity;


import com.kh.totalJpaSample.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@ToString
@Setter
@Getter
@Entity  //클래스를 엔티티로 선언!
@Table(name = "item")  //엔티티와 매핑할 테이블을 지정.
//클래스 이름은 대문자로 시작, 테이블이름은 소문자
//실제 생성될 테이블의 이름을 지정.

public class Item {
    @Id  //테이블의 기본 키 지정
    @Column(name = "item_id") // 실제 디비 테이블에서의 컬럼명을 지정.//필드와 컬럼을 매핑.
    @GeneratedValue(strategy = GenerationType.AUTO)
    //JPA구현체[hibernate]가 생선전략을 자동으로 결정.
    private Long id; // 상품 코드

    @Column(nullable = false, length = 50)
    //Null을 허용하지 않고 길이를 50자로 제한
    private String itemName; // 상품명, 디비에서는 item_name으로 표기될것, 카멜표기법이 적용 x


    @Column(name = "price", nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber; // 재고 수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;  // 상품 상세 설명

    @Enumerated(EnumType.STRING) //enum으로 정의된 값을 문자열로 db에 저장. ordinal은 enum의 순서값을 디비에 저장
    private ItemSellStatus itemSellStatus; // 상품 판매 상태
    private LocalDateTime regTime; // 등록 시간
    private LocalDateTime updateTime; // 수정 시간
}
