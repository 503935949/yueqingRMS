v 0.20版（完成基本功能）

框架：spring + springMVC + mybatis

数据库：mySql、oracle

前端 ：
	1、脚手架：bootstrap
	2、基础框架：jQuery/kendouUI
	3、图表：Echarts 
	4、表单验证：jquery-validation-1.16.0/kendouUI （？）
	5、模态框：layer-v3.0.3 （yrms-layUI-layer.js）
	6、分页组件（表单组件）：kendouUI（？）
	7、文件异步上传（layUI-file组件）
	8、同步上传文件（JavaScript——ajax多文件上传）
	8、基础交互功能界面（demo模块）

完成技术点：
	1、基本框架搭建
	2、主页框架搭建
	3、基础交互功能界面（demo模块）
	4、国际化（jstl，spring标签实现）
	5、文件上传（common-upload/spring）（多文件上传、组件可新增/删除）
	6、文件下载（servlet/spring）
	7、et/xls/xlsx文件解析(poi)
	8、et/xls/xlsx文件导出(poi)
	9、邮件发送（javaMail/spring）
	
	10、邮件拉取（？）（没必要做）
	11、分页处理（demo模块，前端利用kendoUI grid 插件完成，后台利用mybatis拦截器方式执行分页逻辑）
	
	12、aop (projectAspect.java)
	13、拦截器 （LoginInterceptor.java）
	14、spring统一异常处理（GlobalExceptionResolver.java）（前台统一弹出框提示格式）
	15、系统日志（log4j___${webapp.root}/WEB-INF/logs/yrms.log）

	16、Dom4J 操作，生成，解析xml 格式文件/数据（text/dom4j）
	17、定时任务实现（springTask /Quartz 中两种方式Cron、simple）
	18、多数据源切换（jdbc/jndi）

	19、自定义标签（用具权限功能功能）（demo中引用yueqingRMS.tld的标签yrms：demo）
	
	20、使用maven重构项目，并升级spring版本至5.X
	
	
	缓存集成（？）
	工作流集成（？）
	
业务性质：
	1、websocket(?)
	2、登录（？）
	3、权限（？）
	
	
	
	
版本变化
	
	0.17以前
	构建基本框架
	收录常用方法
	
	0.18
	清除个人敏感测试数据（可送人了）
	统一了类ajax事件处理窗口
	分页功能实现
	
	0.19
	kendo grid 导出数据为文件
	grid全选功能
	
	0.20
	处理并完成crud相关模态框传值问题（bootstrap，kendo相关知识点）
	
	
	
	
	
2018/4/22：
	新增：
		3D 条形统计图实例
		双Y轴条形统计图实例
		老版中国地图下转（暂时不符合国家版图，不建议使用）
	
	