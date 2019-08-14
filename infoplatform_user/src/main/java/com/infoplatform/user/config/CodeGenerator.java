package com.infoplatform.user.config;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 *
 * @author 15293
 **/
public class CodeGenerator {

    /**
     * 数据库地址
     **/
    private static String url = "jdbc:mysql://localhost:3312/im_user?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&autoReconnect=true";

    /**
     * 驱动名称
     **/
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";

    /**
     * 用户
     **/
    private static String username = "root";

    /**
     * 密码
     **/
    private static String password = "123456";

    /**
     * 子模块的名称
     **/
    //todo 这个每次都要修改de
    private static String childModuleName = "/infoplatform_user";
    /**
     * 基础的包名
     **/
    private static String basePackageName = "com.infoplatform.user";

    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        // 生成文件的输出目录
        gc.setOutputDir(projectPath + childModuleName + "/src/main/java");
        gc.setAuthor("jack");
        // 是否打开输出目录
        gc.setOpen(false);
        // 开启 swagger2 模式
        gc.setSwagger2(true);
        // 设置日期类型
        gc.setDateType(DateType.TIME_PACK);
        // 设置是否覆盖文件
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driverClassName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent(basePackageName);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自己控制需要生成什么类型的文件
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建,这里调用默认的方法
                checkDir(filePath);
                //对于已存在的文件，只需重复生成 entity 和 mapper.xml
                File file = new File(filePath);
                boolean exist = file.exists();
                if (exist) {
                    return filePath.endsWith("Mapper.xml") || FileType.ENTITY == fileType || FileType.MAPPER == fileType;
                }
                //不存在的文件都需要创建
                return true;
            }
        });
        mpg.setCfg(cfg);

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                System.err.println(projectPath + childModuleName + "/src/main/resources/" + pc.getParent().replaceAll("\\.", "/") + "/mapper"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML);
                return projectPath + childModuleName + "/src/main/resources/" + pc.getParent().replaceAll("\\.", "/") + "/mapper"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        // xml输出配置
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 生成lombok
        strategy.setEntityLombokModel(true);
        // 生成restcontroller
        strategy.setRestControllerStyle(true);
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        // 自定义基础的Entity类，公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}