package com.plus.hospital.usercenter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Arrays;
import java.util.List;

/**
 * MyBatisPlus代码生成
 * @author huguangquan
 * 2023/6/5
 **/
public class MyBatisPlusGenerator {
    public static void main(String[] args) {
        // 要生成的数据库表名
        List<String> tableNames = Arrays.asList("t_user_account","t_role","t_user_role","t_menu","t_role_menu");
        // 创建代码生成工具类
        FastAutoGenerator generator = create(tableNames);
        // 执行生成代码
        generator.execute();
    }

    private static FastAutoGenerator create(List<String> tableNames) {
        String url = "jdbc:mysql://localhost:3306/hospital-plus?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&serverTimezone=GMT%2B8";
        String name = "root";
        String password = "root";
        FastAutoGenerator generator = FastAutoGenerator.create(url, name, password)
                // 全局配置
                .globalConfig(builder -> {
                    // 获取生成的代码路径,这里没有写死，是运行时获取的，这样可以防止不同的开发人员来回修改生成路径的问题。
                    String outputDir = System.getProperty("user.dir") + "/usercenter-web/src/main/java";
                    builder.outputDir(outputDir)
                            .dateType(DateType.ONLY_DATE)
                            // 生成的类注释中的作者名称，为了统一表示，这里写死了
                            .author("Auto Generator");
                })
                // 生成的代码包路径配置
                .packageConfig(builder -> {
                    // 生成的代包公共路径
                    builder.parent("com.plus.hospital.usercenter.dao");
                    // 生成的mapper xml的存放目录，是在parent路径下面的
                    builder.xml("mapper")
                            // 生成的实例类目录
                            .entity("entity")
                            // 生成的service目录
                            .service("service")
                            // 生成的ampper目录
                            .mapper("mapper");

                }).strategyConfig(builder -> {
                    // 添加要生成的的数据库表
                    builder.addInclude(tableNames)
                            // 启用大写模式
                            .enableCapitalMode()
                            .addTablePrefix("t");
                    // 配置生成的实体类策略，不生成serialVersionID
                    builder.entityBuilder().disableSerialVersionUID()
                            // 如果数据库表名带下划线，按驼峰命名法
                            .columnNaming(NamingStrategy.underline_to_camel)
                            // 使用lombok
                            .enableLombok()
                            // 标记实例类的主键生成方式，如果插入时没有指定，刚自动分配一个，默认是雪花算法
                            .idType(IdType.AUTO)
                            // 实例类每次生成的时候，覆盖旧的实体类
                            .enableFileOverride()
                            // 指定生成的实体类名称
                            .convertFileName(entityName -> entityName + "Entity")
                            // 指定生成的service接口名称
                            .serviceBuilder().convertServiceFileName(entityName -> entityName + "Service")
                            // 指定生成的serviceImpl的名称
                            .convertServiceImplFileName(entityName -> entityName + "ServiceImpl");

                }).templateConfig(builder -> {
                    // 不生成Controller
                    builder.disable(TemplateType.CONTROLLER);
                });
        return generator;
    }
}
