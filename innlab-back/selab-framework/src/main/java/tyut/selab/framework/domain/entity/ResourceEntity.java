package tyut.selab.framework.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import tyut.selab.common.domain.Lz;
import tyut.selab.common.domain.BaseEntity;

/**
 * @ClassName: TyutResource
 * @Description: 静态资源
 * @Author: gmslymhn
 * @CreateTime: 2024-02-14 17:53
 * @Version: 1.0
 **/
@TableName("tyut_resource")
@Data
@NoArgsConstructor
public class ResourceEntity extends BaseEntity {
    /**
     * 静态资源id
     */
    @TableId(value = "resource_id" ,type = IdType.AUTO)
    private Integer resourceId;
    /**
     * 资源名称
     */
    @TableField("resource_name")
    private String resourceName;
    /**
     * 资源描述
     */
    @TableField("resource_description")
    private String resourceDescription;
    /**
     * 资源路径
     */
    @TableField("resource_path")
    private String resourcePath;
    /**
     * 资源类型 (0文本 1图片 2视频)
     */
    @TableField("resource_type")
    private Integer resourceType;
    @TableField("resource_url")
    private String resourceUrl;
    @TableField("lz_pwd")
    private String pwd;
    @TableField("lz_f_id")
    private String fId;
    @TableField("lz_is_newd")
    private String isNewd;
}
