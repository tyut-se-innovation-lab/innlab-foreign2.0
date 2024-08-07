package tyut.selab.common.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: lz
 * @Description: 蓝奏云
 * @Author: gmslymhn
 * @CreateTime: 2024-05-23 14:58
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lz implements Serializable  {

    @TableField("lz_pwd")
    @NotNull(message = "资源pwd不能为空")
    @Size(min = 4,max = 4,message = "资源fId格式错误！")
    private String pwd;
    @TableField("lz_f_id")
    @NotNull(message = "资源fId不能为空")
    @Size(min = 10,max = 14,message = "资源fId格式错误！")
    private String fId;
    @TableField("lz_is_newd")
    @NotNull(message = "资源lzNewd不能为空")
    @Size(min = 20,max = 30,message = "资源lzNewd格式错误！")
    private String isNewd;
}
