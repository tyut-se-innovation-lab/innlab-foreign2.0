package tyut.selab.modular.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.common.constant.KeyConstants;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.EnumUtils;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.common.utils.RedisUtils;
import tyut.selab.common.utils.StringUtils;
import tyut.selab.framework.domain.entity.ResourceEntity;
import tyut.selab.framework.mapper.ResourceMapper;
import tyut.selab.modular.domain.dto.param.PersonnelParam;
import tyut.selab.modular.domain.entity.PersonnelEntity;
import tyut.selab.modular.domain.vo.ImageVo;
import tyut.selab.modular.domain.vo.PersonnelVo;
import tyut.selab.modular.mapper.PersonnelMapper;
import tyut.selab.modular.service.IPersonnelService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PersonnelMapper
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-27 17:06
 * @Version: 1.0
 **/
@Service
public class PersonnelServiceImpl implements IPersonnelService {
    @Autowired
    private PersonnelMapper personnelMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public R getPersonnelForeign(PersonnelParam personnelParam){
        Page<PersonnelEntity> page = new Page<>(personnelParam.getPageNum(),personnelParam.getPageSize());
        QueryWrapper<PersonnelEntity> queryWrapper = new QueryWrapper<>();
        if(ObjectUtils.isNotNull(personnelParam.getDepartment())){
            queryWrapper.eq("department_id", EnumUtils.getDepartmentIdByName(personnelParam.getDepartment()));
        }
        queryWrapper.eq("state",true)
                .eq(ObjectUtils.isNotNull(personnelParam.getPeriod()),"personnel_period",personnelParam.getPeriod())
                .orderByAsc("personnel_sort");
        Page<PersonnelEntity> personnelPage = personnelMapper.selectPage(page,queryWrapper);
        List<PersonnelVo> personnelVoList = new ArrayList<>();
        personnelPage.getRecords().forEach(personnelEntity -> {
            PersonnelVo personnelVo = new PersonnelVo(personnelEntity);
            ResourceEntity resourceEntity = resourceMapper.selectById(personnelEntity.getPersonnelAvatar());
            if (ObjectUtils.isNotNull(resourceEntity)){
                ImageVo imageVo = new ImageVo();
                imageVo.setIsNewd(resourceEntity.getIsNewd());
                imageVo.setPwd(resourceEntity.getPwd());
                imageVo.setFId(resourceEntity.getFId());
                String lzLinkUrl = (String) redisUtils.getCacheObject(KeyConstants.LZ_LINEURL_KEY+imageVo.getFId());
                if (StringUtils.isNotEmpty(lzLinkUrl)){
                    imageVo.setUrl(lzLinkUrl);
                }else {
                    imageVo.setUrl(resourceEntity.getResourceUrl());
                }
                personnelVo.setPersonnelAvatar(imageVo);
            }
            personnelVoList.add(personnelVo);
        });
        Page<PersonnelVo> personnelVoPage = new Page<>(personnelPage.getCurrent(),personnelPage.getSize(),personnelPage.getTotal());
        personnelVoPage.setRecords(personnelVoList);
        return R.success(personnelVoPage);
    }
}
