package kp.staryankie.servicedesk.voc.repository;

import javax.persistence.criteria.*;

import org.springframework.data.jpa.domain.Specification;

import kp.staryankie.servicedesk.voc.model.Voc;

final public class VocSpecification {
    private VocSpecification() {

    }

    public static Specification<Voc> notReplied() {
        return new Specification<Voc>() {
            @Override
            public Predicate toPredicate(Root<Voc> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("replied"), "N");
            }
        };
    }

    public static Specification<Voc> myVoc(String id) {
        return new Specification<Voc>() {
            @Override
            public Predicate toPredicate(Root<Voc> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("userid"), id);
            }
        };
    }

    public static Specification<Voc> occupied(String id) {
        return new Specification<Voc>() {
            @Override
            public Predicate toPredicate(Root<Voc> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("mngid"), id);
            }
        };
    }
}