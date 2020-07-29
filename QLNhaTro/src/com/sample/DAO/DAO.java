/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.DAO;

import java.util.List;

/**
 *
 * @author akais
 * @param <DAO>
 */
public interface DAO<DAO> {
    
    List<DAO> select();
    
    void insert(DAO model);
 
    void update(DAO model);
 
    void delete(DAO model);
}
