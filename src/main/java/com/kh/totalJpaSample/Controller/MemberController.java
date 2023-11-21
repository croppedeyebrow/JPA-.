package com.kh.totalJpaSample.Controller;


import com.kh.totalJpaSample.dto.MemberDto;
import com.kh.totalJpaSample.entity.Member;
import com.kh.totalJpaSample.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kh.totalJpaSample.utils.Common.CORS_ORIGIN;

@Slf4j  //Log f4, 로그기록을 남겨서 쌓아두기 위한 것.
@CrossOrigin(origins =  CORS_ORIGIN)
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    //JPA 공부 기록.
//    엔티티가 데이터베이스의 기본적인 컬럼들을 매핑하고 상호작용하는 역할이고
//    컨트롤러가 dto에 담긴 객체를 가지고 클라이언트(프론트)와 통신을 담당하고,
//    레퍼지토리는 엔티티가 db에 담긴 내용과 상호작용 하는 것을 엑세스를 담당하며
//    crud작업을 수행하여 데이터베이스와 상호작용한다.
//
//    그리고, 서비스는
//    컨트롤러가 dto를 가지고 클라이언트에서 가져온 데이터와
//    레퍼지토리가 db에서 가져온 데이터를 모두 가공하고,
//    이 과정에서 여러 레퍼지토리를 사용하고
//    여러 엔티티 간의 관계를 처리하여 비즈니스 로직을 구현!
//     =엔티티의 없는 추가적인 기능을 수행한다.
       //[컨트롤러  -- 서비스가 비즈니스 로직 수행 -- 레퍼지토리]

//    이 과정에서 여러 엔티티간의 상호작용이 가능하고, 레퍼지토리 역시 여러개를 사용할 수 있다







    //회원 등록
    @PostMapping("/new")
    public ResponseEntity<Boolean> memberRegister(@RequestBody MemberDto memberDto) {
             boolean isTrue = memberService.saveMember(memberDto);
             return ResponseEntity.ok(isTrue);
    }

    // 회원 전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<MemberDto>> memberList() {
        List<MemberDto> list = memberService.getMemberList();
        return ResponseEntity.ok(list);
    }

    //회원 상세 조회
    @GetMapping("/detail/{email}")
    public ResponseEntity<MemberDto> memberDetail(@PathVariable String email) {
        MemberDto memberDto = memberService.getMemberDetail(email);
        return ResponseEntity.ok(memberDto);
    }

//    페이지 네이션 조회
    @GetMapping("/list/page")
    public ResponseEntity<List<MemberDto>> memberList(@RequestParam(defaultValue = "0")int page,
                                                      @RequestParam(defaultValue = "10")int size) {
        List<MemberDto> list = memberService.getMemberList(page,size);
        return ResponseEntity.ok(list);
    }

    //총 페이지 수 조회.
    @GetMapping("/list/page-cnt")
    public  ResponseEntity<Integer> memberPageCount(@RequestParam(defaultValue ="0" )int page,
                                                    @RequestParam(defaultValue = "5")int size) {
        int pageCnt = memberService.getMemberPage(page,size);
        return ResponseEntity.ok(pageCnt);
    }


}