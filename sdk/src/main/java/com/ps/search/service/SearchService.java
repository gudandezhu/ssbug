package com.ps.search.service;


import com.ps.dto.ArticleDTO;

public interface SearchService {

    ArticleDTO findById(String id);

}
