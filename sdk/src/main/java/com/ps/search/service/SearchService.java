package com.ps.search.service;


import com.ps.dto.ArticleDTO;

import java.util.List;

public interface SearchService {

    ArticleDTO findById(String id);

    List<ArticleDTO> findByKeyWord(String keyWord,String[] params);
}
