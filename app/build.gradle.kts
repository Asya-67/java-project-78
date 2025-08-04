plugins {
    id("java")
    id ("application")
    id ("com.github.ben-manes.versions") version "0.51.0"
    id ("checkstyle")
    id("org.sonarqube") version "6.2.0.5505"
    id("jacoco")
}
jacoco {
    toolVersion = ("0.8.11")
}

sonar {
    properties {
        p property("sonar.projectKey", "java-project-78")
        property("sonar.organization", "AZhB9d5DPe5PAwi63HiO")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.login", System.getenv("SONAR_TOKEN"))
    }
}

checkstyle {
    toolVersion = ("10.26.1")
    configFile = rootProject.file("config/checkstyle/checkstyle.xml")
    isShowViolations = true
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}