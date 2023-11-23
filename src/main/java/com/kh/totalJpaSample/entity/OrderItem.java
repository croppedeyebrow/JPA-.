package com.kh.totalJpaSample.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class OrderItem {
    @Id
    @GeneratedValue   // 기본이 auto
    @Column(name = "order_item_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "item_id")
    private  Item item;



    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "order") // 연관관계의 주인이 아님을 표시.
    private List<OrderItem> orderItemList = new ArrayList<>();

}
