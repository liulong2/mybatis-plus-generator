package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

<#--    ${entity} selectById(Long pkId);-->

<#--    void addRecord(${entity} ${controllerMappingHyphen?replace("-","")}) throws Exception;-->

<#--    void delRecord(Long pkId) throws Exception;-->

<#--    void editRecordByPkId(${entity} ${controllerMappingHyphen?replace("-","")}) throws Exception;-->
}
</#if>
