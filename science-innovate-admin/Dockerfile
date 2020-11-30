FROM java:8
# 项目在容器内部运行的端口
EXPOSE 8080

VOLUME /tmp
ADD /target/science-innovate-admin.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
