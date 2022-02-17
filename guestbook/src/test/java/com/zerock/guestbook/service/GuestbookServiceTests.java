package com.zerock.guestbook.service;

import com.zerock.guestbook.dto.GuestbookDTO;
import com.zerock.guestbook.dto.PageRequestDTO;
import com.zerock.guestbook.dto.PageResultDTO;
import com.zerock.guestbook.entity.GuestBook;
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

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDTO<GuestbookDTO, GuestBook> resultDTO = service.getList(pageRequestDTO);

        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
    }
}
