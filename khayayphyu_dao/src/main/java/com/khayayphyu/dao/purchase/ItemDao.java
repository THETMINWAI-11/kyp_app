package com.khayayphyu.dao.purchase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.khayayphyu.entity.product.Item;

@Repository
public interface ItemDao extends CrudRepository<Item, Long> {

}
