package com.kisin.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kisin.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.kisin.gen.common.pagination.Page;
import com.kisin.product.entity.Product;
import com.kisin.gen.common.data.Result;
import com.kisin.gen.common.data.ResultCode;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 产品 前端控制器
 * </p>
 *
 * @author kisin
 * @since 2019-11-21
 */
@Controller
@RequestMapping("/product/product")
@Api(value = "产品的接口",tags = {"产品的controller"})
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/list")
    String list(){
        return "product/list";
    }

    @ResponseBody
    @PostMapping("/list")
    @ApiOperation(value = "产品列表",notes = "产品列表数据，分页数据", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "offset",value = "起始数据行", required = true, paramType = "path"),
        @ApiImplicitParam(name = "limit",value = "每页数据行数", required = true, paramType = "path"),
    })
    Page<Product> list(Page<Product> page){
        return (Page<Product>)productService.findAllInfo(page);
    }

    @GetMapping("/view")
    String view() {
        return "product/view";
    }

    @ResponseBody
    @PostMapping("/data")
    @ApiOperation(value = "产品数据",notes = "产品数据，根据主键或唯一组合条件", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "company",value = "商家"),
        @ApiImplicitParam(name = "id",value = "主键"),
        @ApiImplicitParam(name = "sell",value = "销售"),
        @ApiImplicitParam(name = "name",value = "名称"),
        @ApiImplicitParam(name = "status",value = "状态"),
        @ApiImplicitParam(name = "colors",value = "颜色"),
        @ApiImplicitParam(name = "typeId",value = "类型"),
    })
    Result<Product> data(Product product) {
        return new Result<>().
            setCode(ResultCode.SUCCESS).
            setData(productService.getOne(new QueryWrapper<>(product)));
    }

    @GetMapping("/edit")
    String edit() {
        return "product/edit";
    }

    @ResponseBody
    @PostMapping("/edit")
    @ApiOperation(value = "产品数据新增修改",notes = "产品数据的新增或修改，主键数据存在则为修改", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "company",value = "商家"),
        @ApiImplicitParam(name = "id",value = "主键"),
        @ApiImplicitParam(name = "sell",value = "销售"),
        @ApiImplicitParam(name = "name",value = "名称"),
        @ApiImplicitParam(name = "status",value = "状态"),
        @ApiImplicitParam(name = "colors",value = "颜色"),
        @ApiImplicitParam(name = "typeId",value = "类型"),
    })
    Result<Product> edit(Product product) {
        productService.saveOrUpdate(product);
        return new Result<>().
            setCode(ResultCode.SUCCESS).
            setData(product);
    }

    @ResponseBody
    @PostMapping("/remove")
    @ApiOperation(value = "产品数据删除",notes = "产品数据删除，根据主键或唯一组合条件，删除指定的数据", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "company",value = "商家"),
        @ApiImplicitParam(name = "id",value = "主键"),
        @ApiImplicitParam(name = "sell",value = "销售"),
        @ApiImplicitParam(name = "name",value = "名称"),
        @ApiImplicitParam(name = "status",value = "状态"),
        @ApiImplicitParam(name = "colors",value = "颜色"),
        @ApiImplicitParam(name = "typeId",value = "类型"),
    })
    Result<Product> remove(Product product){
        productService.remove(new QueryWrapper<>(product));
        return new Result<>().
        setCode(ResultCode.SUCCESS).
        setData(product);
    }

}
