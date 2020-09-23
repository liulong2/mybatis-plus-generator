package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

<#--    @Override-->
<#--    public ${entity} selectById(Long pkId) {-->
<#--        return selectById(pkId);-->
<#--    }-->

<#--    @Override-->
<#--    @Transactional(rollbackFor = Exception.class)-->
<#--    public void addRecord(${entity} ${controllerMappingHyphen?replace("-","")}) throws Exception {-->
<#--        save(${controllerMappingHyphen?replace("-","")});-->
<#--    }-->

<#--    @Override-->
<#--    @Transactional(rollbackFor = Exception.class)-->
<#--    public void delRecord(Long pkId) throws Exception {-->
<#--        if (!removeById(pkId)) {-->
<#--            throw new Exception("删除失败");-->
<#--         }-->
<#--    }-->

<#--    @Override-->
<#--    @Transactional(rollbackFor = Exception.class)-->
<#--    public void editRecordByPkId(${entity} ${controllerMappingHyphen?replace("-","")}) throws Exception {-->
<#--         if (!updateById(${controllerMappingHyphen?replace("-","")})) {-->
<#--             throw new Exception("跟新失败");-->
<#--         }-->
<#--    }-->

}
</#if>
