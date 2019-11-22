package com.kisin.product.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.beans.Transient;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.kisin.dict.entity.Dict;
/**
 * <p>
 * 产品
 * </p>
 *
 * @author kisin
 * @since 2019-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_product")
@ApiModel(value="Product对象", description="产品")
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    private String company;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private Integer sell;

    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private Dict sellDict;

    private String name;

    private Integer status;
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private Dict statusDict;

    @ApiModelProperty(value = "颜色")
    private String colors;
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private Dict colorsDict;

    private String typeId;

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
