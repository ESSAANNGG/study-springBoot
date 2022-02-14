package com.zerock.guestbook.service;

import com.zerock.guestbook.dto.GuestbookDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister(){

        GuestbookDTO dto = GuestbookDTO.builder()
                .title("Sample Title.......")
                .content("Sample Content........")
                .writer("Sample writer.......")
                .build();
        System.out.println(service.register(dto));
    }
}
