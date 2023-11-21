package com.kh.totalJpaSample.repository;

import com.kh.totalJpaSample.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


//인터페이스의 기능상속을 받는 것이, jpa의 구현체(하이버네이트)
//쿼리 메소드의 변수 이름은, 엔티티의 필드명(컬럼명)
@Repository
//기본적인 CRUD는 JpaRepositorty에 이미 정의 되어 있음, 페이징 처리도 포함 되어 있음
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemName (String itemName);

    //OR 조건 처리
    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);

    //LessThan 조건 처리 : price 변수 보다 작은 상품 데이터 조회하는 쿼리.
    List<Item> findByPriceLessThan(Integer price);

    //OrderBy로 정렬하기
    List<Item> findAllByOrderByPriceDesc();

    //JPQL 쿼리 작성하기 : SQL과 유사한 객체지향 쿼리 언어.
    //객체지향이기에, 디비쪽이 아닌, 엔티티(필드)족으로 날려야 함. sql문이지만 sql문 형태가 아님.
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> priceSorting(@Param("itemDetail") String itemDetail);


    //nativeQuery 사용하기
    @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> priceSortingNative(@Param("itemDetail") String itemDetail);
}

