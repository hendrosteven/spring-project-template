/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salatigacode.dao;

import com.salatigacode.SpringTemplateApplication;
import com.salatigacode.entity.Product;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author hendro.tampake
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringTemplateApplication.class)
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-product.sql"})
@WebIntegrationTest
public class ProductControllerTests {

    private static final String BASE_URL = "http://localhost:8080/api/product/";
    private final RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testSave() {
        Product p = new Product();
        p.setCode("PT-001");
        p.setName("Product Test 001");
        p.setPrice(BigDecimal.valueOf(102000.02));

        Product responseObj = restTemplate.postForObject(BASE_URL, p, Product.class);
        Assert.assertNotNull(responseObj.getId());

        //nama tidak diisi
        Product px = new Product();
        p.setCode("PT-002");
        Product responseObjx = restTemplate.postForObject(BASE_URL, px, Product.class);
        Assert.assertNull(responseObjx.getId());

    }

    @Test
    public void testDelete() {        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);      
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(BASE_URL + "abc123", HttpMethod.DELETE, entity, String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode().OK);
    }

}
