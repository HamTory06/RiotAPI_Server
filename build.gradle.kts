import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("org.springframework.boot") version "2.7.4"
    id ("io.spring.dependency-management") version "1.1.0"
    id ("org.jetbrains.kotlin.jvm") version "1.8.21"
    id ("org.jetbrains.kotlin.plugin.spring") version "1.8.21"
    id ("org.jetbrains.kotlin.plugin.jpa") version "1.8.21"
}
allprojects{
    group = "com.api.study.riot_api"
    version = "0.0.1-SNAPSHOT"


    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
        kotlinOptions.freeCompilerArgs = listOf(
            "-Xjsr305=strict",
            "-Xjvm-default=all",
        )
    }

}


//configurations {
//    compileOnly {
//        extendsFrom annotationProcessor
//    }
//}
repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom ("org.springframework.cloud:spring-cloud-dependencies:2021.0.5")
    }
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.cloud:spring-cloud-starter-config")
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation ("org.jetbrains.kotlin:kotlin-reflect")
    implementation ("org.springframework.boot:spring-boot-starter-security")
    compileOnly ("org.projectlombok:lombok")
    developmentOnly ("org.springframework.boot:spring-boot-devtools")
    runtimeOnly ("mysql:mysql-connector-java")
    annotationProcessor ("org.projectlombok:lombok")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")


    //swagger
    implementation ("org.springdoc:springdoc-openapi-ui:1.6.15")

    implementation ("javax.servlet:javax.servlet-api:4.0.1")
    implementation ("jakarta.servlet:jakarta.servlet-api:4.0.4")

    //jwt
    implementation ("io.jsonwebtoken:jjwt:0.9.1")

    implementation ("org.springframework.boot:spring-boot-starter-validation")

    //Open Feign
    implementation ("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation ("org.springframework.cloud:spring-cloud-starter-bootstrap")



}

//tasks.named('test') {
//    useJUnitPlatform()
//}