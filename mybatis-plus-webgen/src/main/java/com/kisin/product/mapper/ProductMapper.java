package com.kisin.product.mapper;

import com.kisin.product.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kisin
 * @since 2019-11-21
 */
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> selectAllInfo();
}
