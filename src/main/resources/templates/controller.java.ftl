package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
import ${package.ServiceImpl}.${table.serviceName};
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#--entity 名字不能在这里显示-->
@RequestMapping("<#--<#if package.ModuleName??>/${package.ModuleName}</#if>-->/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen?replace("-","/")}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    @Autowired
    private ${table.serviceName} ${controllerMappingHyphen?replace("-","")}Service;

<#--    @GetMapping("/select/id")-->
<#--    public ${entity} selectById(Long pkId){-->
<#--        return ${controllerMappingHyphen?replace("-","")}Service.selectById(pkId);-->
<#--    }-->

<#--    @PostMapping("/add")-->
<#--    public void addRecord(${entity} ${controllerMappingHyphen?replace("-","")}) throws Exception {-->
<#--        ${controllerMappingHyphen?replace("-","")}Service.addRecord(${controllerMappingHyphen?replace("-","")});-->
<#--    }-->

<#--    @PostMapping("/del")-->
<#--    public void delRecord(Long pkId) throws Exception {-->
<#--        ${controllerMappingHyphen?replace("-","")}Service.delRecord(pkId);-->
<#--    }-->

<#--    @PutMapping("/update")-->
<#--    public void editRecordByPkId(${entity} ${controllerMappingHyphen?replace("-","")}) throws Exception {-->
<#--        ${controllerMappingHyphen?replace("-","")}Service.editRecordByPkId(${controllerMappingHyphen?replace("-","")});-->
<#--    }-->
}
</#if>
