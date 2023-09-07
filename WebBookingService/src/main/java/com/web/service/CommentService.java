/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.service;

import com.web.pojo.Comments;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface CommentService {
    List<Comments> getComments(Map<String, String>params);
    int countComment();
    boolean addOrUpdateComment(Comments com);
    Comments getCommentById(int idCommet);
    boolean deleteComment(int idCommet);
}
