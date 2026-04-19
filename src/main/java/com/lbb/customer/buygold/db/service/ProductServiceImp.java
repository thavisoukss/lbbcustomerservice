package com.lbb.customer.buygold.db.service;

import com.lbb.customer.buygold.db.entity.ProductEntity;
import com.lbb.customer.buygold.db.mapper.ListProductMapper;
import com.lbb.customer.buygold.db.repository.ProductRepository;
import com.lbb.customer.buygold.model.ProductData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ListProductMapper listProductMapper;

    public ProductServiceImp(ProductRepository productRepository, ListProductMapper listProductMapper) {
        this.productRepository = productRepository;
        this.listProductMapper = listProductMapper;
    }

    @Override
    public List<ProductData> getAllProduct() {

        List<ProductEntity> entities = productRepository.getAllProduct();

        return listProductMapper.toProductResponseList(entities);
    }
}
