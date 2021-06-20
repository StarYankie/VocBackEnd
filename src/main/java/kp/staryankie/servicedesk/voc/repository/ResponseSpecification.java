package kp.staryankie.servicedesk.voc.repository;

import javax.persistence.criteria.*;

import org.springframework.data.jpa.domain.Specification;

import kp.staryankie.servicedesk.voc.model.Response;

final public class ResponseSpecification {
    private ResponseSpecification() {

    }

    public static Specification<Response> findByVocId(Integer vocid) {
        return new Specification<Response>() {
            @Override
            public Predicate toPredicate(Root<Response> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("vocid"), vocid);
            }
        };
    }
}