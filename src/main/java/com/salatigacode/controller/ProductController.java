/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salatigacode.controller;

import com.salatigacode.dao.ProductDao;
import com.salatigacode.entity.Product;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hendro.tampake
 */
@RestController
@RequestMapping("/api/product")
@Transactional(readOnly = true)
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public Product create(@RequestBody @Valid Product p) {
        return productDao.save(p);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void delete(@PathVariable("id") String id) throws Exception {
        if (!productDao.exists(id)) {
            throw new Exception("No data with the specified id");
        }
        productDao.delete(id);
    }
}
