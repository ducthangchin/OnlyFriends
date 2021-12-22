package com.ducthangchin.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentDao extends PagingAndSortingRepository<Comment, Long> {
    Iterable<Comment> findCommentsByPostOrderByAddedDesc(StatusUpdate post);
}
