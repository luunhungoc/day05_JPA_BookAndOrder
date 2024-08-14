package jpa.repository;

import jpa.entity.CategoryEntity;
import jpa.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity,Integer> {
}
