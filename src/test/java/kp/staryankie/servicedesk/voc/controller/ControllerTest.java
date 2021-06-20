package kp.staryankie.servicedesk.voc.controller;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class ControllerTest {
    @Autowired
    VocController vocController;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mock;

    @BeforeEach
    public void setup() {
        mock = MockMvcBuilders.standaloneSetup(vocController).build();
    }

    @Test
    public void getVocsTest() throws Exception {
        // 올바른 url 호출 시 200 리턴
        mock.perform(get("/v1/customer/vocs")).andExpect(status().isOk());
    }

    @Test
    public void getVocsMTest() throws Exception {
        // 올바른 url 호출 시 200 리턴
        mock.perform(get("/v1/manager/vocs")).andExpect(status().isOk());
    }

    @Test
    public void getWrongUrlTest() throws Exception {
        // 잘못된 url 호출 시 404 리턴
        mock.perform(get("/v1/customer/voc")).andExpect(status().isNotFound());
    }

    @Test
    public void saveVocsTest() throws Exception {
        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
        info.toSingleValueMap().put("userid", "test");

        String content = objectMapper.writeValueAsString(info);
        // 데이터 POST 시 200 리턴
        mock.perform(post("/v1/customer/vocs").content(content).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
