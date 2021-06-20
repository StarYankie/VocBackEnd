package kp.staryankie.servicedesk.voc.repository;

import kp.staryankie.servicedesk.voc.model.Voc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VocRepository extends JpaRepository<Voc, Integer>, JpaSpecificationExecutor<Voc> {
    Page<Voc> findAll(Specification<Voc> spec, Pageable pageable);
}
