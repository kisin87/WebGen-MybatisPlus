package com.kisin.dict.service;

import com.kisin.dict.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 这个是字典表！！！ 服务类
 * </p>
 *
 * @author kisin
 * @since 2019-11-08
 */
public interface DictService extends IService<Dict> {

    List<Map<String, String>> findDictType();
}
