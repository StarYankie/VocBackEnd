package kp.staryankie.servicedesk.voc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kp.staryankie.servicedesk.voc.model.Response;
import kp.staryankie.servicedesk.voc.repository.ResponseRepository;
import kp.staryankie.servicedesk.voc.repository.ResponseSpecification;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class ResponseController {
    @Autowired
    private final ResponseRepository responseRepository;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(value = "/manager/responses/{vocid}")
    public List<Response> getResponseList(@RequestParam(required = true) Integer vocid) {
        // 파라미터로 입력된 문의 id의 답변 리턴
        return responseRepository.findAll(ResponseSpecification.findByVocId(vocid));
    }

    @PostMapping(value = "/manager/responses")
    public ResponseEntity<?> addVoc(@RequestBody Response v) {
        responseRepository.save(v);
        return ResponseEntity.ok("답변이 정상 등록되었습니다.");
    }
}