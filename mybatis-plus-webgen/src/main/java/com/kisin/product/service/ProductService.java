package com.kisin.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kisin.product.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kisin
 * @since 2019-11-21
 */
public interface ProductService extends IService<Product> {

    IPage<Product> findAllInfo(IPage<Product> page);

}
