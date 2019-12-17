package com.store.webstore.repos;

import com.store.webstore.domain.Automobile;
import org.springframework.data.repository.CrudRepository;

public interface AutomobileRepo extends CrudRepository<Automobile, Integer> {
}
