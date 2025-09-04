package com.dms.base.repository;
import com.dms.base.model.CollectionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionItemRepository extends JpaRepository<CollectionItem,Long>{
    
}


