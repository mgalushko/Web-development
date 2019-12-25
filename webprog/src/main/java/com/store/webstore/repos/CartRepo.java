package com.store.webstore.repos;

import com.store.webstore.domain.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepo extends CrudRepository<Cart, Integer> {
}
