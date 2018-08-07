# easy-swagger 说明文档

##1、下载代码，安装在本地仓库(maven依赖)
```
    git clone https://github.com/szliugx/easy-swagger.git

```

##2、应用服务配置：
1、引入依赖
```
        <!--swagger插件-->
        <dependency>
            <groupId>com.github.szliugx</groupId>
            <artifactId>easy-swagger</artifactId>
            <version>1.0.0</version>
        </dependency>

```

2、填写配置文件
```
    # swagger 文档插件配置
    easy_swagger:
      # 是否开启 swagger 插件
      enable: true    
      title: xxx公司_xx服务_接口文档
      description: 用于提供xx服务
      groupName: xx服务
      basePackage: cn.xxx.xxx.web.controller
```

3、启动类上添加配置文件
```
    @EnableEasySwagger
```

##3、应用文件函数上注解作用（Swagger2 基本使用，非必须想要更详细的可配置）：
```
    常用注解： 
    - @Api()用于类； 
    表示标识这个类是swagger的资源 
    - @ApiOperation()用于方法； 
    表示一个http请求的操作 
    - @ApiParam()用于方法，参数，字段说明； 
    表示对参数的添加元数据（说明或是否必填等） 
    - @ApiModel()用于类 
    表示对类进行说明，用于参数用实体类接收 
    - @ApiModelProperty()用于方法，字段 
    表示对model属性的说明或者数据操作更改 
    - @ApiIgnore()用于类，方法，方法参数 
    表示这个方法或者类被忽略 
    - @ApiImplicitParam() 用于方法 
    表示单独的请求参数 
    - @ApiImplicitParams() 用于方法，包含多个 @ApiImplicitParam

```
- 示例
```
package com.xxx.controller;

@Api(value = "用户信息管理", tags = {"用户相关接口"})
@RestController
@RequestMapping("/user/*")
public class UserController {

    private final static List<User> userList = new ArrayList<>();
    {
        userList.add(new User("1", "zhangsan", "张三"));
        userList.add(new User("2", "lisi", "李四"));
    }

    @ApiOperation("获取列表")
    @GetMapping("list")
    public List userList() {
        return userList;
    }

    @ApiOperation("新增用户")
    @PostMapping("save")
    public boolean save(User user) {
        return userList.add(user);
    }

    @ApiOperation("更新用户")
    @ApiImplicitParam(name = "user", value = "单个用户信息", dataType = "User")
    @PutMapping("update")
    public boolean update(User user) {
        return userList.remove(user) && userList.add(user);
    }

    @ApiOperation("批量删除")
    @ApiImplicitParam(name = "users", value = "N个用户信息", dataType = "List<User>")
    @DeleteMapping("delete")
    public boolean delete(@RequestBody List<User> users) {
        return userList.removeAll(users);
    }
    
    @ApiIgnore
    public void foo() {
        
    }
}
```



