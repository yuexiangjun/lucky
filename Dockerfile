FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

ADD /target/lucky-star-apiserver.jar app.jar

EXPOSE 30100

ENV TZ=Asia/Shanghai

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 执行启动命令 --add-opens必须加‘java’后面，字符串不能有空格
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]