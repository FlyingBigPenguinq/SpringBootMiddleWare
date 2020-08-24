package com.study.boot.SpringBootMiddleWare.server.Enums;

/**
 * @ClassName SysMudule
 * @Description: TODO　系统/项目枚举类型
 * @Author lxl
 * @Date 2020/8/23
 * @Version V1.0
 **/
public enum SysMudule {
    ModuleItem("item","商品模块"),
    ModuleUser("user","用户模块"),
    ModuleOrder("order","订单模块")
    ;

    private String code;
    private String name;

    SysMudule(String code, String name){
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
