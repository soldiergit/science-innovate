# science-innovate-vue

#### 项目介绍
科创管理信息系统-PC端

#### 软件架构
>软件架构说明 :vue + element UI

#### 安装教程

1. 安装依赖：
```shell
npm install chromedriver --chromedriver_cdnurl=http://cdn.npm.taobao.org/dist/chromedriver
npm install
cd node_modules/
npm rebuild node-sass
npm install file-saver xlsx --save-dev
  ```
2. 开发运行：`npm run dev`
3. 构建编译：`npm run build`
3. 打包镜像：`docker build -t soldierdocker/science-innovate-vue:lastst .`
4. 推送仓库：`docker push soldierdocker/science-innovate-vue`
5. 拉取镜像：`docker pull soldierdocker/science-innovate-vue:lastst`
6. 部署镜像：`docker-componse -f docker-compose.yml -d`

>Linux平台前端项目vue无法热更新 在启动时请使用 sudo npm run dev

#### 持续集成平台
>travis ci
