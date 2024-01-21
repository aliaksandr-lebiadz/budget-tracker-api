package org.alebiadz.budget.tracker.service.specification

import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Path
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

abstract class AbstractSpecification<T> : Specification<T> {

    companion object {
        const val ID = "id"
    }

    protected fun applyDefault(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder) {

        query.orderBy(criteriaBuilder.asc(root.get<Long>(ID)))
    }

    protected fun <T> equalTo(path: Path<T>, value: T?, criteriaBuilder: CriteriaBuilder): Predicate? {

        return value?.let { criteriaBuilder.equal(path, it) }
    }
}