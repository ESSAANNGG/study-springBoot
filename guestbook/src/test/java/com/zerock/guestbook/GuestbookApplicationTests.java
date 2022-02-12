package com.zerock.guestbook;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.zerock.guestbook.entity.GuestBook;
import com.zerock.guestbook.entity.QGuestBook;
import com.zerock.guestbook.repository.GuestBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
class GuestbookApplicationTests {

    @Test
    void contextLoads() {
    }

    /*@Test
    public void testQuery2(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        QGuestBook qGuestBook = QGuestBook.guestBook;

        String keyword = "1";

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestBook.title.contains(keyword);

        BooleanExpression exContent = qGuestBook.content.contains(keyword);

        BooleanExpression exALl = exTitle.or(exContent); // 1-----------------

        booleanBuilder.and(exALl);

        booleanBuilder.and(qGuestBook.gno.gt(0L));

        Page<GuestBook> result = GuestBookRepository.findAll(booleanBuilder, pageable);
    }*/
}
