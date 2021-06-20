package kp.staryankie.servicedesk.voc.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kp.staryankie.servicedesk.voc.model.Response;
import kp.staryankie.servicedesk.voc.model.Voc;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ResponseRepositoryTest {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private VocRepository vocRepository;

    @Test
    void save() {
        String vocUserId = "USERID";
        final Voc voc = Voc.builder().userid(vocUserId).build();
        final Voc savedVoc = vocRepository.save(voc);

        // given
        int resVocId = 1;
        String resContent = "CONTENT";

        final Response response = Response.builder().vocid(resVocId).content(resContent).build();

        // when
        final Response savedResponse = responseRepository.save(response);

        // then
        assertEquals(resContent, savedResponse.getContent());
    }

    @Test
    void saveWithInvalidForeignKey() {
        // given
        // 해당 VOC ID는 없으므로 INSERT시 에러 발생
        int resVocId = 1;
        String resContent = "CONTENT";

        final Response response = Response.builder().vocid(resVocId).content(resContent).build();

        // when
        try {
            final Response savedResponse = responseRepository.save(response);
            fail();
        } catch (Exception e) {

        }
    }
}
