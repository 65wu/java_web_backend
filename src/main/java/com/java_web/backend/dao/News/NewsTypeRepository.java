package com.java_web.backend.dao.News;

import com.java_web.backend.model.po.NewsType;
import org.springframework.data.repository.CrudRepository;

public interface NewsTypeRepository extends CrudRepository<NewsType, Integer> {
}
