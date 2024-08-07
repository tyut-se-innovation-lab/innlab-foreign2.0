package tyut.selab.modular.controller.background;

import groovy.util.logging.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tyut.selab.common.domain.R;
import tyut.selab.modular.domain.dto.AddItemDto;
import tyut.selab.modular.domain.dto.AddPersonalDto;
import tyut.selab.modular.domain.dto.UpdateItemDto;
import tyut.selab.modular.domain.dto.UpdatePersonDto;
import tyut.selab.modular.domain.dto.param.ItemParam;
import tyut.selab.modular.domain.dto.param.PersonnelParam;
import tyut.selab.modular.service.IPersonnelService;

/**
 * @ClassName: PersonnelController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-17 14:30
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/background/item")
@Tag(name = "人员管理")
@Slf4j
public class PersonnelController {
    @Autowired
    private IPersonnelService iPersonnelService;
    @PostMapping("/addPersonal")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "添加人员信息")
    public R addPersonal(@RequestBody @Validated AddPersonalDto addPersonalDto){
        return iPersonnelService.addPersoual(addPersonalDto);
    }
    @PostMapping("/getPersonalList")
    @Operation(summary = "获取人员列表")
    @PreAuthorize("@ss.hasPermission('admin')")
    public R getPersonallist(@RequestBody @Validated PersonnelParam personnelParam){
        return iPersonnelService.getPersonnelList(personnelParam);
    }

    @PostMapping("/updatePersonal")
    @PreAuthorize("@ss.hasPermission('admin')")
    @Operation(summary = "修改人员信息")
    public R updatePersonal(@RequestBody @Validated UpdatePersonDto updatePersonDto){
        return iPersonnelService.updatePersoual(updatePersonDto);
    }
    @PostMapping("/deletePersonal")
    @Operation(summary = "删除人员")
    @PreAuthorize("@ss.hasPermission('admin')")
    public R deletePersonalById(@RequestParam("personalId") Integer personalId){
        return iPersonnelService.deletePersoual(personalId);
    }
}
