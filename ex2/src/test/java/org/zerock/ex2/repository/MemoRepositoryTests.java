package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.ex2.entity.Memo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;


    @Test
    public void testClass(){
        System.out.println(memoRepository.getClass().getName());
    }

    //100개의 메모텍스트 삽입 부분
    @Test
    public void testInsertDummies(){

        IntStream.rangeClosed(1,100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..."+i).build();
            memoRepository.save(memo);
        });

    }

    //100번의 long형 메모 찾기
    @Test
    public void testSelect(){

        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("=================================");

        if (result.isPresent()){
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    //100번의 메모 수정
    @Test
    public void testUpdate(){
        Memo memo = Memo.builder()
                .mno(100L)
                .memoText("Update Text").build();
        memoRepository.save(memo);
    }

    //100번의 메모 삭제
    @Test
    public void testDelete(){
        Long mno = 100L;
        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault(){
        //1페이지 10개
        Pageable pageable = PageRequest.of(0,10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);
        System.out.println("==================================");
        System.out.println("Total Pages : " + result.getTotalPages());
        System.out.println("Total Count : " + result.getTotalElements());
        System.out.println("Page Number : " + result.getNumber());
        System.out.println("Page Size : " + result.getSize());
        System.out.println("has next Page? : " + result.hasNext());
        System.out.println("first Pages? :" + result.isFirst());

    }

    @Test
    public void testSort(){

        Sort sort1 = Sort.by("mno").descending();
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2);

        Pageable pageable = PageRequest.of(0, 10, sortAll);
        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo -> {
            System.out.println(memo);
        });
    }

    @Test
    public void testQueryMethod(){
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L,80L);

        for (Memo memo : list){
            System.out.println(list);
        }
    }

    @Test
    public void testQueryMethodWithPageable(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(10L,50L, pageable);

        result.get().forEach(memo -> System.out.println(memo));
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQuery(){
        memoRepository.deleteMemoByMnoLessThan(10L);
    }
}