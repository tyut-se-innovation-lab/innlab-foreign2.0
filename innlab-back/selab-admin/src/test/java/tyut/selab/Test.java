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
import tyut.selab.framework.domain.entity.ResourceEntity;
import tyut.selab.framework.mapper.ResourceMapper;
import tyut.selab.modular.domain.entity.ActivityEntity;
import tyut.selab.modular.domain.entity.ItemEntity;
import tyut.selab.modular.domain.entity.SubTitleEntity;
import tyut.selab.modular.mapper.ActivityMapper;
import tyut.selab.modular.mapper.ItemMapper;
import tyut.selab.modular.mapper.SubTitleMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ItemMapper itemMapper;
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


    public static List<String> extractHttpsUrls(String input) {
        List<String> httpsUrls = new ArrayList<>();
        String regex = "\"https_url\": \"(.*?)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String url = matcher.group(1);
            httpsUrls.add(url);
        }

        return httpsUrls;
    }

    @org.junit.Test
    public void test2(){
        String abc = "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/0b06deb36ce3e815f75d7e26734c801b5eceae05cd7400ee2866ad7f77b9b8e4d4467f7dac70dbab1a7485d94e52a4a3?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-15ILxXJ.png\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/0b06deb36ce3e815f75d7e26734c801b5eceae05cd7400ee2866ad7f77b9b8e4d4467f7dac70dbab1a7485d94e52a4a3?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-15ILxXJ.png\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/63d89317c484837da1e355ccd9268c5ae1578e0538cb2c84e1d17dc8b72e9e8239da00dc0a9c064eb69769c66d0a2350?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-15Kxby1.png\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/63d89317c484837da1e355ccd9268c5ae1578e0538cb2c84e1d17dc8b72e9e8239da00dc0a9c064eb69769c66d0a2350?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-15Kxby1.png\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/23fb011552eb88f745660c9efd570db3e3de6c77b63215cce16cb291e80b231594012af59f6158d8140b3bcf33203ea7?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-15MrvB0.png\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/23fb011552eb88f745660c9efd570db3e3de6c77b63215cce16cb291e80b231594012af59f6158d8140b3bcf33203ea7?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-15MrvB0.png\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/1541ea423cbfa3f02986d866077190a137be373f539b5f882b7cfac2d15aabba3fa728b91fd52597ca76bcd7d25f272a?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-15WZQfr.jpg\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/1541ea423cbfa3f02986d866077190a137be373f539b5f882b7cfac2d15aabba3fa728b91fd52597ca76bcd7d25f272a?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-15WZQfr.jpg\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/22b0b8392387317ff29669ea693d0edec474dc94a570167cff179369f30f19e17ce3fc5d03ae927607af34c4f69e52a3?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-15yt0DG.jpg\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/22b0b8392387317ff29669ea693d0edec474dc94a570167cff179369f30f19e17ce3fc5d03ae927607af34c4f69e52a3?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-15yt0DG.jpg\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/950c7ee1eda29aa1ba00a07041159cde2b165480148300e5f265f7d2c03a654480917d561c118747e1f82fe47933191c?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-152PA3t.png\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/950c7ee1eda29aa1ba00a07041159cde2b165480148300e5f265f7d2c03a654480917d561c118747e1f82fe47933191c?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-152PA3t.png\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/9b9aed977c61f21fd630603fa7c8ff27fe0a5d39ae16ba69fec79c8ce41244e0cfae608c43079661ac6d7939f8020459?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-155wvYo.png\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/9b9aed977c61f21fd630603fa7c8ff27fe0a5d39ae16ba69fec79c8ce41244e0cfae608c43079661ac6d7939f8020459?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-155wvYo.png\",\n";
        List<String> urls = extractHttpsUrls(abc);
        System.out.println("提取到的https_url列表如下：");
        ResourceEntity resourceEntity = new ResourceEntity();
        for (String url : urls) {
            resourceEntity.setResourceUrl(url+"&size=750");
            String name = url.substring(202,217);
            System.out.println(name);
            QueryWrapper<ResourceEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("resource_name",name);
            resourceMapper.update(resourceEntity,queryWrapper);
            System.out.println(url);
        }
    }
    public static void main(String[] args) {
//        String arr[] = ".321.321.321".split("\\.");
//        for (String abc:arr){
//            System.out.println(abc);
//        }


    }

    @org.junit.Test
    public void test3(){
        int id = 80;
        System.out.println(id);
        ActivityEntity activityEntity = activityMapper.selectById(id);
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setDepartmentId(activityEntity.getActivityDepartment());
        itemEntity.setHeaderImage(activityEntity.getHeaderImage());
        itemEntity.setItemTitle(activityEntity.getActivityTitle());
        itemEntity.setItemIntroduction(activityEntity.getActivityIntroduction());
        QueryWrapper<SubTitleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activity_id",id);
        SubTitleEntity subTitleEntity = subTitleMapper.selectOne(queryWrapper);
        itemEntity.setContentMarkdown(subTitleEntity.getSubtitleContent());
        itemEntity.setCreateTime(activityEntity.getCreateTime());
        itemEntity.setState(true);
        itemEntity.setDelFlag(0);
        itemEntity.setCreateUser(activityEntity.getCreateUser());
        itemMapper.insert(itemEntity);

    }
}
