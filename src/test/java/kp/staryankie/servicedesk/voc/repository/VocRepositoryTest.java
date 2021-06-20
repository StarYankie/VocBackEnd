package kp.staryankie.servicedesk.voc.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kp.staryankie.servicedesk.voc.model.Voc;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class VocRepositoryTest {
    @Autowired
    private VocRepository vocRepository;

    @Test
    void save() {
        // given
        String vocUserId = "USERID";
        String vocTitle = "TITLE";
        String vocContent = "CONTENT";

        final Voc voc = Voc.builder().userid(vocUserId).title(vocTitle).content(vocContent).build();

        // when
        final Voc savedVoc = vocRepository.save(voc);

        // then
        assertEquals(vocTitle, savedVoc.getTitle());
    }
}
