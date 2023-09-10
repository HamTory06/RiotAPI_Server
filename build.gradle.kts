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


    tasks.withType<KotlinCompile> {
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

    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")

    compileOnly("io.jsonwebtoken:jjwt-api:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly ("org.projectlombok:lombok")
    developmentOnly ("org.springframework.boot:spring-boot-devtools")
    annotationProcessor ("org.projectlombok:lombok")

    implementation ("org.mariadb.jdbc:mariadb-java-client")
    //swagger
    implementation ("org.springdoc:springdoc-openapi-ui:1.6.15")

    implementation ("javax.servlet:javax.servlet-api:4.0.1")
    implementation ("jakarta.servlet:jakarta.servlet-api:4.0.4")

    //Open Feign
    implementation ("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation ("org.springframework.cloud:spring-cloud-starter-bootstrap")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")


}

//tasks.named('test') {
//    useJUnitPlatform()
//}