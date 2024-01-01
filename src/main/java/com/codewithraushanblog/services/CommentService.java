package com.codewithraushanblog.services;

import com.codewithraushanblog.payloads.CommentDto;

public interface CommentService {


    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);

}
