<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd" >
    <parent >
        <artifactId >eccrm-${module}</artifactId >
        <groupId >eccrm</groupId >
        <version >1.0.0-SNAPSHOT</version >
        <relativePath >../pom.xml</relativePath >
    </parent >
    <modelVersion >4.0.0</modelVersion >

    <artifactId >${module}-impl</artifactId >

    <dependencies >
        <dependency >
            <groupId >eccrm</groupId >
            <artifactId >${module}-api</artifactId >
            <version >1.0.0-SNAPSHOT</version >
        </dependency >
        <dependency >
            <groupId >junit</groupId >
            <artifactId >junit</artifactId >
            <scope >test</scope >
        </dependency >
        <dependency >
            <groupId >org.springframework</groupId >
            <artifactId >spring-test</artifactId >
            <scope >test</scope >
        </dependency >

        <!-- 实现部分公共依赖 -->
        <!-- spring mvc -->
        <dependency >
            <groupId >org.springframework</groupId >
            <artifactId >spring-webmvc</artifactId >
        </dependency >

        <dependency >
            <groupId >org.springframework</groupId >
            <artifactId >spring-web</artifactId >
        </dependency >

        <!-- hibernate -->
        <dependency >
            <groupId >org.hibernate</groupId >
            <artifactId >hibernate-core</artifactId >
        </dependency >
        <dependency >
            <groupId >javassist</groupId >
            <artifactId >javassist</artifactId >
        </dependency >
        <dependency >
            <groupId >org.springframework</groupId >
            <artifactId >spring-orm</artifactId >
        </dependency >
        <dependency >
            <groupId >org.aspectj</groupId >
            <artifactId >aspectjrt</artifactId >
        </dependency >

        <dependency >
            <groupId >org.aspectj</groupId >
            <artifactId >aspectjweaver</artifactId >
        </dependency >

        <!-- 数据库 -->
        <dependency >
            <groupId >commons-dbcp</groupId >
            <artifactId >commons-dbcp</artifactId >
        </dependency >

        <dependency >
            <groupId >com.oracle</groupId >
            <artifactId >ojdbc6</artifactId >
            <scope >runtime</scope >
        </dependency >

        <dependency >
            <groupId >com.mchange</groupId >
            <artifactId >c3p0</artifactId >
        </dependency >

        <!-- 其他常用工具包 -->
        <dependency >
            <groupId >log4j</groupId >
            <artifactId >log4j</artifactId >
        </dependency >
        <dependency >
            <groupId >commons-logging</groupId >
            <artifactId >commons-logging</artifactId >
        </dependency >
        <dependency >
            <groupId >commons-beanutils</groupId >
            <artifactId >commons-beanutils</artifactId >
        </dependency >
    </dependencies >

</project >