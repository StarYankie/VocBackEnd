package kp.staryankie.servicedesk.voc.repository;

import kp.staryankie.servicedesk.voc.model.Response;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ResponseRepository extends JpaRepository<Response, Integer>, JpaSpecificationExecutor<Response> {
    Page<Response> findAll(Specification<Response> spec, Pageable pageable);
}
