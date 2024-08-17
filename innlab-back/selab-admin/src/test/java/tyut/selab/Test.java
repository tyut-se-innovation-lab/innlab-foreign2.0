package tyut.selab;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.framework.domain.PageParam;
import tyut.selab.modular.domain.entity.SubTitleEntity;
import tyut.selab.modular.mapper.SubTitleMapper;

import java.util.List;

/**
 * @ClassName: Test
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-08 23:22
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    private SubTitleMapper subTitleMapper;

    @org.junit.Test
    public void test1(){
        PageParam param = new PageParam(1,1);
        Page<SubTitleEntity> page = new Page<>(param.getPageNum(),param.getPageSize());
        QueryWrapper<SubTitleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activity_id",99)
                .orderByDesc("subtitle_sort");
        IPage<SubTitleEntity> subTitleEntityIPage = subTitleMapper.selectPage(page, queryWrapper);
        List<SubTitleEntity> subTitleEntityList = subTitleEntityIPage.getRecords();
        if (subTitleEntityList.size()==0){
            System.out.println("3211321");
        }
        System.out.println(subTitleEntityList);
    }

    public static void main(String[] args) {
        String arr[] = ".321.321.321".split("\\.");
        for (String abc:arr){
            System.out.println(abc);
        }
    }
}
