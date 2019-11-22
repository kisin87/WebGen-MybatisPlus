package com.kisin.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kisin.product.entity.Product;
import com.kisin.product.mapper.ProductMapper;
import com.kisin.product.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kisin
 * @since 2019-11-21
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    ProductMapper productMapper;

    public IPage<Product> findAllInfo(IPage<Product> page){
        return page.setRecords(productMapper.selectAllInfo());
    }

}
