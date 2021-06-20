package kp.staryankie.servicedesk.voc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kp.staryankie.servicedesk.voc.model.Voc;
import kp.staryankie.servicedesk.voc.repository.VocRepository;
import kp.staryankie.servicedesk.voc.repository.VocSpecification;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class VocController {
    @Autowired
    private final VocRepository vocRepository;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(value = "/customer/vocs")
    public List<Voc> getVocList(@RequestParam(required = false) String userid,
            @RequestParam(required = false) String replied, @RequestParam(required = false) String manager) {
        if (userid != null) {
            // userid 입력한 경우 해당 사용자가 작성한 문의만 조회
            return vocRepository.findAll(VocSpecification.myVoc(userid));
        } else {
            // 파라미터 없는 경우 전체 문의 조회
            return vocRepository.findAll();
        }
    }

    @GetMapping(value = "/manager/vocs")
    public List<Voc> getVocListM(@RequestParam(required = false) String userid,
            @RequestParam(required = false) String replied, @RequestParam(required = false) String manager) {
        if (replied == "N") {
            // replied=N 입력한 경우 미답변 문의만 조회
            return vocRepository.findAll(VocSpecification.notReplied());
        } else if (manager != null) {
            // manager 입력한 경우 해당 상담사가 접수한 문의만 조회
            return vocRepository.findAll(VocSpecification.occupied(manager));
        } else {
            // 파라미터 없는 경우 전체 문의 조회
            return vocRepository.findAll();
        }
    }

    @PostMapping(value = "/customer/vocs")
    public ResponseEntity<?> addVoc(@RequestBody Voc v) {
        vocRepository.save(v);
        return ResponseEntity.ok("문의가 정상적으로 등록되었습니다.");
    }

    @PatchMapping(value = "/manager/vocs/{id}")
    public ResponseEntity<?> reserveVoc(@PathVariable Integer id, @RequestParam(required = true) String mngid) {
        Optional<Voc> oVoc = vocRepository.findById(id);
        if (oVoc.get().getMngid() != null)
            return ResponseEntity.ok("이미 담당자가 지정된 문의건입니다");
        if (oVoc.isPresent()) {
            Voc voc = oVoc.get();
            voc.setMngid(mngid);
            vocRepository.save(voc);
            return ResponseEntity.ok("담당자 지정 완료");
        } else {
            return ResponseEntity.ok("입력한 ID의 문의가 없습니다.");
        }
    }

    @PatchMapping(value = "/manager/vocs/replied/{id}")
    public ResponseEntity<?> repliedVoc(@PathVariable Integer id, @RequestParam(required = true) String mngid) {
        Optional<Voc> oVoc = vocRepository.findById(id);
        if (oVoc.get().getReplied() != "N")
            return ResponseEntity.ok("이미 답변이 완료 된 문의건입니다");
        if (oVoc.isPresent()) {
            Voc voc = oVoc.get();
            voc.setReplied("Y");
            vocRepository.save(voc);
            return ResponseEntity.ok("답변 등록 완료");
        } else {
            return ResponseEntity.ok("입력한 ID의 문의가 없습니다.");
        }
    }
}