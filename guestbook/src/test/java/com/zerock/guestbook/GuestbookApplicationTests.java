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


}
