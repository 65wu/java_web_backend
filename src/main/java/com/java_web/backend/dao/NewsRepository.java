package com.java_web.backend.dao;

import com.java_web.backend.model.po.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Integer> {
}
