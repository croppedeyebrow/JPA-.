package com.kh.totalJpaSample.repository;

import com.kh.totalJpaSample.constant.ItemSellStatus;
import com.kh.totalJpaSample.entity.Item;
import com.kh.totalJpaSample.entity.Order;
import com.kh.totalJpaSample.entity.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties")

class OrderRepositoryTest {
       @Autowired
       OrderRepository orderRepository;

       @Autowired
       ItemRepository itemRepository;

       @PersistenceContext
        EntityManager em;


       public Item createItem() {
           Item item = new Item();
           item.setItemName("테스트 상품");
           item.setPrice(10000);
           item.setItemDetail("테스트 상품 상세 설명");
           item.setItemSellStatus(ItemSellStatus.SELL);
           item.setStockNumber(100);
           return item;
       }



       @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest() {
           Order order = new Order();
           for (int i = 0; i <3; i++) {
               Item item = this.createItem();
               itemRepository.save(item);
               OrderItem orderItem = new OrderItem();
               orderItem.setItem(item);
               orderItem.setCount(10);
               orderItem.setOrderPrice(1000);
               orderItem.setOrder(order);
               order.getOrderItemList().add(orderItem);
           }
           //엔티티를 저장하면서 DB에 반영
           orderRepository.saveAndFlush(order);
           em.clear(); // 영속성 상태를 초기화.
           //주문 엔티티 조회
           Order saveOrder = orderRepository.findById(order.getId()).orElseThrow(EntityNotFoundException::new);
           //Order 객체의 orderItem 개수가 3개인지 확인.
           log.warn(String.valueOf(saveOrder.getOrderItemList().size()));
       }
}