/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;

import com.web.pojo.Comments;
import com.web.repository.CommentRepository;
import com.web.service.CommentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository comRepo;

    @Override
    public List<Comments> getComments(Map<String, String> params) {
        return this.comRepo.getComments(params);
    }
    

    @Override
    public int countComment() {
        return this.comRepo.countComment();
    }

    @Override
    public boolean addOrUpdateComment(Comments com) {
        return this.comRepo.addOrUpdateComment(com);
    }

    @Override
    public Comments getCommentById(int idCommet) {
        return this.comRepo.getCommentById(idCommet);
    }

    @Override
    public boolean deleteComment(int idCommet) {
        return this.comRepo.deleteComment(idCommet);
    }

}
