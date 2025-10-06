package com.dms.base.specification;

import org.springframework.data.jpa.domain.Specification;

import com.dms.base.model.Branch;
import com.dms.base.model.Company;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class CompanySpecification {
    public static Specification<Company> joinCompanyBranch(Long companyId) {
        return (root, query, cb) -> {
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Branch> branchRoot = subquery.from(Branch.class);
            
            subquery.select(branchRoot.get("id"));
            subquery.where(
                cb.equal(branchRoot.get("companyId"), root.get("id")),
                cb.equal(root.get("id"), companyId)
            );

            return cb.exists(subquery);
        };
    }
}
