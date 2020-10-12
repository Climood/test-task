package com.mcb.creditfactory.service.baseentity;

import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public abstract class BaseEntityService<T extends BaseEntity> {
    @Autowired
    private ExternalApproveService approveService;

    public abstract CrudRepository<T, Long> getRepository();

//    public abstract CollateralAdapterBase getAdapter(Object dto);
//
//    public abstract boolean approve(T dto);

    public T save(T entity) {
        return getRepository().save(entity);
    }

    public Optional<T> load(Long id) {
        return getRepository().findById(id);
    }

}
