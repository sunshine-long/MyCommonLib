# MyUtils
常用工具类
## 项目中封装了常用的工具类
####说明
项目中 myutils 目录为本人在开发中总结或者优化过的工具类

   otherutils  目录为网上收藏的第三方作者的工具类
   
   base目录为activity/fragment的基类
    
####简单介绍
- ActivityCollector 

主要用于管理项目中的activity，当要退出activity时销毁所有activity，结合项目中baseactivity一起使用。
- MyCameraUtils 

主要用于调用系统相机，进行权限的申请，拍照，存储，剪切；
剪切可以采用系统相机剪切，也集成了Ucrop开源框架进行剪切；
- PreferencesUtils 

封装了sharePrenance的常用操作，轻松读取项目存储、读取sp数据；

- MyRetrofitClient
封装了Rxjava + retrofit + 自定义拦截器，方便快捷的retrofit请求框架封装，以扩展，使用简单方便
实现了：链式调用，动态切换baseurl，及自定义headers 
项目已经创建单独的库进行维护 相关使用方式见：
https://github.com/sunshine-long/MyRetrofitClient

- LogUtils

对日志打印进行简单的封装

- ToastUtils 

对toast弹出进行简单的封装

- ScreenAdaptationUtil

今日头条屏幕适配方案工具类，一句代码调用！！！
