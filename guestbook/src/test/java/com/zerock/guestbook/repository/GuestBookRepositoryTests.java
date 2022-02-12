package com.zerock.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.zerock.guestbook.entity.GuestBook;
import com.zerock.guestbook.entity.QGuestBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTests {

    @Autowired
    private GuestBookRepository guestBookRepository;

    @Test
    public void insertDummies(){

        IntStream.rangeClosed(1,300).forEach(i -> {
            GuestBook guestBook = GuestBook.builder()
                    .title("Title......." + i)
                    .content("Content......." + i)
                    .writer("user" + (i%10))
                    .build();
            System.out.println(guestBookRepository.save(guestBook));
        });
    }

    @Test
    public void updateTest(){

        Optional<GuestBook> result = guestBookRepository.findById(300L);

        if(result.isPresent()) {

            GuestBook guestBook = result.get();

            guestBook.changeTitle("Changed Title.......");
            guestBook.changeContent("Changed Content.......");

            guestBookRepository.save(guestBook);
        }
    }

    @Test
    public void testQuery1(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        QGuestBook qGuestBook = QGuestBook.guestBook; //1

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression booleanExpression = qGuestBook.title.contains(keyword);

        builder.and(booleanExpression);

        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });
    }
    @Test
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

        Page<GuestBook> result = guestBookRepository.findAll(booleanBuilder, pageable);

        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });
    }

    @Test
    public void testQuery3(){

        Pageable pageable = PageRequest.of(0,10,Sort.by("gno").descending());

        QGuestBook qguestBook = QGuestBook.guestBook;

        String keyword = "3";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qguestBook.title.contains(keyword);

        BooleanExpression exContent = qguestBook.content.contains(keyword);

        BooleanExpression exWriter = qguestBook.writer.contains(keyword);

        BooleanExpression exAll = exTitle.or(exContent).or(exWriter);

        builder.and(exAll);

        builder.and(qguestBook.gno.gt(0L));

        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });
    }
}
