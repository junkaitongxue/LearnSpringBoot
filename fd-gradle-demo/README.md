学习材料：
- idea-》tool-〉gradleConsole
- GRADLE_USER_HOME 设置跟M2仓库一样 + mavenLocal（）
- settings.gradle -> including () + compile project（”：otherModuleName“）
- springboot打包： https://www.cnblogs.com/hfultrastrong/p/8528908.html
- 黑马：https://www.bilibili.com/video/BV1iW411C7CV?p=12&vd_source=8ca65501683ab25438a7feba212c83ec
- springboot + gradle： https://www.bilibili.com/video/BV1iW411C7CV?p=12&vd_source=8ca65501683ab25438a7feba212c83ec
- springboot jar：  https://blog.csdn.net/qq_26819733/article/details/87924124
- springboot-gradle-plugin简述：https://blog.csdn.net/qq_26819733/article/details/87924124
- Gradle创建SpringBoot项目：https://blog.csdn.net/J080624/article/details/134230694
- [Gradle8.4构建SpringBoot多模块项目](https://www.cnblogs.com/kakarotto-chen/p/17784126.html)

FQA：
1、在配置都一模一样的时候，就是没法下载到对应的gradle依赖包和插件的时候，
执行gradle -v看能不能正常呈现，需要确保添加了JAVA_HOME的环境变量, 跟项目运行的是java是配套