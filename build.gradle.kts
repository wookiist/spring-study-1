plugins {
	java
	id("org.springframework.boot") version "2.7.16"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "hello"
version = "0.0.1-SNAPSHOT"

// lombok 설정 추가 시작
configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}
// lombok 설정 추가 끝

java {
	sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// lombok 라이브러리 추가 시작
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	testCompileOnly("org.projectlombok:lombok")
	testAnnotationProcessor("org.projectlombok:lombok")
	// lombok 라이브러리 추가 끝
}

tasks.withType<Test> {
	useJUnitPlatform()
}
