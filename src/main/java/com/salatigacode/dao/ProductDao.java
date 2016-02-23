/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salatigacode.dao;

import com.salatigacode.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author hendro.tampake
 */
public interface ProductDao extends PagingAndSortingRepository<Product, String> {

}
