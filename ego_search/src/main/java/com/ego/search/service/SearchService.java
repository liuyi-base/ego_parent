package com.ego.search.service;

import java.util.Map;

public interface SearchService {
    Map<String,Object> search(String q, int page, int size);
    int insert(long [] ids);
}
