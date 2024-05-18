import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.21"
	kotlin("plugin.spring") version "1.9.21"
	kotlin("plugin.jpa") version "1.9.21"
	id("io.gitlab.arturbosch.detekt") version "1.23.4"
}

group = "br.com"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Database
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")
	implementation("org.flywaydb:flyway-core")

	// timer lib
	implementation("joda-time:joda-time:2.10.14")

	// mockk
	testImplementation("io.mockk:mockk:1.12.4")
	testImplementation("com.ninja-squad:springmockk:3.1.1")

	// web - rest api
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Lint
	detekt("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.4")
	detekt("io.gitlab.arturbosch.detekt:detekt-cli:1.23.4")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

detekt {
	toolVersion = "1.23.4"
	source.setFrom(files("./"))
	config.setFrom(files("./detekt-config.yml"))
	autoCorrect = true
}
