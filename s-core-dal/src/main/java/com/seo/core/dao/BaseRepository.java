package com.seo.core.dao;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface BaseRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

}
