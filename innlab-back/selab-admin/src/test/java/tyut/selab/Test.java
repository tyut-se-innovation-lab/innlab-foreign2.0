package tyut.selab;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.common.utils.RedisUtils;
import tyut.selab.framework.domain.PageParam;
import tyut.selab.framework.domain.entity.AccessLogEntity;
import tyut.selab.framework.domain.entity.ResourceEntity;
import tyut.selab.framework.mapper.AccessLogMapper;
import tyut.selab.framework.mapper.ResourceMapper;
import tyut.selab.modular.domain.entity.ActivityEntity;
import tyut.selab.modular.domain.entity.ItemEntity;
import tyut.selab.modular.domain.entity.SubTitleEntity;
import tyut.selab.modular.mapper.ActivityMapper;
import tyut.selab.modular.mapper.ItemMapper;
import tyut.selab.modular.mapper.SubTitleMapper;

import java.util.*;
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

    @Autowired
    private AccessLogMapper accessLogMapper;

    @Autowired
    private RedisUtils redisUtils;

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
        String abc = "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/4201eb0a685fe442303346decb5a40550cdca1bb0029d16b20a193fc7a63d05218ea942585e530a073e4ff4d59de3f52?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-30tQWCS.png\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/4201eb0a685fe442303346decb5a40550cdca1bb0029d16b20a193fc7a63d05218ea942585e530a073e4ff4d59de3f52?pictype=scale&from=30013&version=3.3.3.3&fname=2025-05-30tQWCS.png\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/bad2f0dacb73aa201c90b975c1f748cc92d6e34ec3c3f7eb23e9eefd6e8ab62840340dd4b3cd0f1ecc77a2338c7162f7?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-09oQGQm.jpg\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/bad2f0dacb73aa201c90b975c1f748cc92d6e34ec3c3f7eb23e9eefd6e8ab62840340dd4b3cd0f1ecc77a2338c7162f7?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-09oQGQm.jpg\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/13a5cb33eb41ba2d6f76c3962aba363ea0f8ece7cf14b93885cda6750b8b1325dce9dac5e0da318cf033e689f81488c8?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10CnhJ0.jpg\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/13a5cb33eb41ba2d6f76c3962aba363ea0f8ece7cf14b93885cda6750b8b1325dce9dac5e0da318cf033e689f81488c8?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10CnhJ0.jpg\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/f8330f48c3b0b9714a44d78dbcd49c4fc899de4cf3c714b305ffd2a10fa86d8d6597b1706ba6c3bbfeeeab84862853d7?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10NcMy2.jpg\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/f8330f48c3b0b9714a44d78dbcd49c4fc899de4cf3c714b305ffd2a10fa86d8d6597b1706ba6c3bbfeeeab84862853d7?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10NcMy2.jpg\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/789e98a36e523589717957ce263e014da521d2cdffd34b207cf7906e378da1b43f0288d7c1393eb05db3f19d4a775164?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10dg2BS.jpg\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/789e98a36e523589717957ce263e014da521d2cdffd34b207cf7906e378da1b43f0288d7c1393eb05db3f19d4a775164?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10dg2BS.jpg\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/d39717086211203d3682585b8c39f6b50e5e69770bb06f21e347324338b89e6faacbfdd5457400e295a63adf7e123d13?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10fNBao.jpg\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/d39717086211203d3682585b8c39f6b50e5e69770bb06f21e347324338b89e6faacbfdd5457400e295a63adf7e123d13?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10fNBao.jpg\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/4024847cb4165c4993b2af7dbb445a85b375f6be33c2bba440868930015b43da01aabd03bd9ed6ca6b866f03934f44c7?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10llDMA.jpg\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/4024847cb4165c4993b2af7dbb445a85b375f6be33c2bba440868930015b43da01aabd03bd9ed6ca6b866f03934f44c7?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10llDMA.jpg\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/1b99c1c2556547f5ad90cc14317968450a486c9be3b648968b366507a4b4fe858d191c82aa23ed6df236fd7b90e691ba?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10tEPUF.jpg\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/1b99c1c2556547f5ad90cc14317968450a486c9be3b648968b366507a4b4fe858d191c82aa23ed6df236fd7b90e691ba?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10tEPUF.jpg\",\n" +
                "                                    \"thumb_url\": \"http://picabstract.preview.ftn.qq.com/ftn_pic_abs_v3/2122c0078e02bff88bdeeced6df77799a84b1764a74caf94927eef3f20bdd162a89ef5c7de871d48792e84d9b1675da3?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10xRobb.jpg\",\n" +
                "                                    \"https_url\": \"https://picabstract-preview-ftn.weiyun.com/ftn_pic_abs_v3/2122c0078e02bff88bdeeced6df77799a84b1764a74caf94927eef3f20bdd162a89ef5c7de871d48792e84d9b1675da3?pictype=scale&from=30013&version=3.3.3.3&fname=2025-06-10xRobb.jpg\",\n";
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

    @org.junit.Test
    public void test3(){
        int id = 125;
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

    @org.junit.Test
    public void test4(){
        Map<String, Object> keys = redisUtils.scanValuesByPrefix("lz_lineurl:");
        for (Map.Entry<String, Object> key : keys.entrySet()) {

            System.out.println("Key = " + key.getKey() + ", Value = " + key.getValue());

        }
    }

    @org.junit.Test
    public void test5(){

        Random rand = new Random();
        for (int i = 0;i<10000;i++){
            AccessLogEntity accessLogEntity = accessLogMapper.selectById(rand.nextInt(10000)+200);
            accessLogEntity.setLogId(null);
            accessLogEntity.setAccessTime(null);
            accessLogMapper.insert(accessLogEntity);
        }
    }
    public static int[] stringToIntegerArray(String s) {
        String[] parts = s.split("\\."); // 使用正则表达式分割，因为点号是特殊字符
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].isBlank()){
                continue;
            }
            result[i] = Integer.parseInt(parts[i]);
        }
        return result;
    }
}
