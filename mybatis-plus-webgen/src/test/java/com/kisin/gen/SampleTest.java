package com.kisin.gen;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.kisin.gen.dao.QuoteTableMapper;
import com.kisin.gen.dao.TableInfoMapper;
import com.kisin.gen.entity.QuoteTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-15 16:58
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private QuoteTableMapper quoteTableMapper;

    @Test
    public void testSelect() {
        System.out.println("===========select All===============");
//        TableInfo info = new TableInfo();
//        info.setMainTable(true);
//        info.setName("t_dict");
        List<QuoteTable> tableList = quoteTableMapper.findAll();
//        System.out.println(table);
        tableList.forEach(System.out::println);
//        infoList.forEach(System.out::println);
    }
}
