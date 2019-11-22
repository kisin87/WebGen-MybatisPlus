package com.kisin.dict.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * 这个是字典表！！！
 * </p>
 *
 * @author kisin
 * @since 2019-11-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dict")
public class Dict extends Model<Dict> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dict_id", type = IdType.UUID)
    private String dictId;

    private String dictValue;

    private String typeName;

    private Boolean delFlag;

    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
