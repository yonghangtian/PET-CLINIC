package tian.springframework.petclinic.services;

import java.util.Set;

/**
 * @author tianyh
 * @since 6/5/19 11:15 AM
 */
public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(Long id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
