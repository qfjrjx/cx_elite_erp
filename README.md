### 编辑器记得安装lombok插件

本地部署账号密码：

账号 | 密码| 权限
---|---|---
mrbird | 1234qwer |超级管理员，拥有所有增删改查权限
scott | 1234qwer | 注册账户，拥有查看，新增权限（新增用户除外）和导出Excel权限
micaela | 1234qwer |系统监测员，负责整个系统监控模块

### 更多版本
当前分支为2.0版本，页面采用Layui全新构建，FEBS的其他版本：

### 系统模块
系统功能模块组成如下所示：
```
├─系统管理
│  ├─用户管理
│  ├─角色管理
│  ├─菜单管理
│  └─部门管理
├─系统监控
│  ├─在线用户
│  ├─系统日志
│  ├─登录日志
│  ├─请求追踪
│  ├─系统信息
│  │  ├─JVM信息
│  │  ├─TOMCAT信息
│  │  └─服务器信息
├─代码生成
│  ├─生成配置
│  ├─代码生成
└─其他模块
```
### 系统特点

1. 前后端请求参数校验

2. 支持Excel导入导出

3. 前端页面布局多样化，主题多样化

4. 支持多数据源，代码生成

5. 多Tab页面，适合企业应用

6. 用户权限动态刷新

7. 浏览器兼容性好，页面支持PC，Pad和移动端。

8. 代码简单，结构清晰

### 技术选型

#### 后端
- [Spring Boot 2.5.3](http://spring.io/projects/spring-boot/)
- [Mybatis-Plus](https://mp.baomidou.com/guide/)
- [MySQL 5.7.x](https://dev.mysql.com/downloads/mysql/5.7.html#downloads)
- [Hikari](https://brettwooldridge.github.io/HikariCP/)
- [Redis](https://redis.io/)
- [Shiro 1.6.0](http://shiro.apache.org/)

#### 前端
- [Layui 2.6.8](https://www.layui.com/)
- [Nepadmin](https://gitee.com/june000/nep-admin)
- [eleTree 树组件](https://layuiextend.hsianglee.cn/eletree/)
- [xm-select](https://gitee.com/maplemei/xm-select)
- [Apexcharts图表](https://apexcharts.com/)

### 系统截图

#### PC端
![screenshot](screenshot/pc_screenshot_1.jpg)
![screenshot](screenshot/pc_screenshot_2.jpg)
![screenshot](screenshot/pc_screenshot_3.jpg)
![screenshot](screenshot/pc_screenshot_4.jpg)
![screenshot](screenshot/pc_screenshot_5.jpg)
![screenshot](screenshot/pc_screenshot_6.jpg)

#### 手机
![screenshot](screenshot/mobile_screenshot_1.jpg)
![screenshot](screenshot/mobile_screenshot_2.jpg)
#### Pad
![screenshot](screenshot/pad_screenshot_1.jpg)
![screenshot](screenshot/pad_screenshot_2.jpg)
![screenshot](screenshot/pad_screenshot_3.jpg)
### 浏览器兼容
|[<img src="https://raw.github.com/alrra/browser-logos/master/src/archive/internet-explorer_9-11/internet-explorer_9-11_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>IE| [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/edge/edge_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Edge | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/firefox/firefox_48x48.png" alt="Firefox" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Firefox | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/chrome/chrome_48x48.png" alt="Chrome" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Chrome | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/safari/safari_48x48.png" alt="Safari" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Safari |[<img src="https://raw.github.com/alrra/browser-logos/master/src/opera/opera_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Opera
| --------- | --------- | --------- | --------- | --------- |--------- |
|IE 10+| Edge| last 15 versions| last 15 versions| last 10 versions| last 15 versions


