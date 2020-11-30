FROM docker.io/nginx

MAINTAINER soldier <soldier_wyyx@163.com>

RUN rm /etc/nginx/conf.d/default.conf

ADD default.conf /etc/nginx/conf.d/

# 删除多余页面
RUN rm /usr/share/nginx/html/*
COPY dist/ /usr/share/nginx/html/
