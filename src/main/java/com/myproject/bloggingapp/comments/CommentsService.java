package com.myproject.bloggingapp.comments;

import com.myproject.bloggingapp.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;
}
