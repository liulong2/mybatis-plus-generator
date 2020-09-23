package com.liu.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ProjectName: mybatis-plus
 * @Package: com.jiangfeixiang.mybatisplus.mpconfig
 * @ClassName: CodeGenerator
 * @Author: jiangfeixiang
 * @email: 1016767658@qq.com
 * @Description: 代码生成器
 * @Date: 2019/5/10/0010 21:41
 */
@Service
public class MpGenerator {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${mybatis-plus.global-config.db-config.id-type}")
    private IdType idType;

    /**
     * 读取控制台内容
     */
    public static String scanner(String tables) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tables + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tables + "！");
    }

    @Transactional(rollbackFor = Exception.class)
    public void generateByTables(String ipt,String prefix) throws Exception {

        if (!StringUtils.isNotEmpty(ipt)) {
            throw new Exception("输入为空");
        }
        /**
         * 代码生成器
         */
        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());


        /**
         * 全局配置
         */
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
        GlobalConfig globalConfig = new GlobalConfig();
        //设计主键类型
        globalConfig.setIdType(idType);
        //生成文件的输出目录
        // TODO: 2020/4/5  不能修改为项目根目录路径 不然会替换当前项目修改文件
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        //Author设置作者
        globalConfig.setAuthor("liu");
        //是否覆盖文件
        globalConfig.setFileOverride(true);
        //生成后打开文件
        globalConfig.setOpen(false);


        //if (!serviceNameStartWithI) {
        // TODO: 2020/4/5 自定义文件名
        globalConfig.setMapperName("%sDAO");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        globalConfig.setEntityName("%sEntity");
        globalConfig.setXmlName("%sMapper");
        //}
        mpg.setGlobalConfig(globalConfig);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
//        globalConfig.setMapperName("%sDAO");
//        globalConfig.setXmlName("%sMapper");
//        globalConfig.setServiceName("MP%sService");
//        globalConfig.setServiceImplName("%sServiceImpl");
//        globalConfig.setControllerName("%sController");
//        globalConfig.setEntityName("%sEntity");

        /**
         * 数据源配置
         */
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 数据库类型,默认MYSQL
        dataSourceConfig.setDbType(DbType.MYSQL);
        //自定义数据类型转换  将数据库里的字段对应到java字段上
        dataSourceConfig.setTypeConvert(new MySqlTypeConvertConfig());
        // TODO: 2020/4/5 数据库
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setDriverName(driverClassName);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        mpg.setDataSource(dataSourceConfig);

        /**
         * 包配置
         */
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("com.mybatis");
        //父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        // TODO: 2020/4/5 修改输出包名
        pc.setParent("tes");
        mpg.setPackageInfo(pc);


        /**
         * 自定义配置
         */
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        /**
         * 模板
         */
        //如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";


        /**
         * 自定义输出配置
         */
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
       /* focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"+ pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });*/
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        /**
         * 配置模板
         */
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        // TODO: 2020/4/5 自定义模板  不使用的话会默认使用mybatis-plus的模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        /*templateConfig.setEntity("/templates/entity.java");
        templateConfig.setService("/templates/service.java");
        templateConfig.setController("/templates/controller.java");
        templateConfig.setServiceImpl("/templates/mapper.xml");
        templateConfig.setServiceImpl("/templates/serviceImpl.java");
*/

        //templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        /**
         * 策略配置
         */

        StrategyConfig strategy = new StrategyConfig();
        //设置命名格式
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(ipt.split(","));
//        strategy.setSuperMapperClass("tes.com.mybatis.entity");
        //实体是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        //设置自定义继承的Entity类全称，带包名
        //strategy.setSuperEntityClass("com.jiangfeixiang.mpdemo.BaseEntity");
        //设置自定义继承的Controller类全称，带包名
        //strategy.setSuperControllerClass("com.wxgzh.config.AbstractHttp");
        //strategy.setSuperServiceImplClass();
        //设置自定义基础的Entity类，公共字段  解开后typeid注解不存在
        //strategy.setSuperEntityColumns("id");
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //表名前缀pc.getModuleName() +
        // TODO: 2020/4/5 删除第一个下划线之前的字母
        strategy.setTablePrefix(prefix);
        mpg.setStrategy(strategy);
        mpg.execute();

        System.out.println("**********************************************************");
        System.out.println("/\n/\n/\n/");
        System.out.println("/************************代码生成结束**********************/");
        System.out.println("/\n/\n/\n/");
        System.out.println("**********************************************************");
        System.exit(0);
    }
}
