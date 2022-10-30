plugins {
	id("org.asciidoctor.jvm.convert") version "3.3.2"
}

val asciidoctorExt by configurations.creating

dependencies {
	implementation(project(":domain"))
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
	testImplementation("com.h2database:h2")
	asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
}

val snippetsDir by extra {
	file("build/generated-snippets")
}

tasks.test {
	outputs.dir(snippetsDir)
	useJUnitPlatform()
}

tasks.asciidoctor {
	inputs.dir(snippetsDir)
	dependsOn(tasks.test)
	configurations("asciidoctorExt")
	baseDirFollowsSourceFile()

	doLast {
		delete {
			file("src/main/resources/static/docs")
		}
	}
}

tasks.register("copyDocument", Copy::class) {
	dependsOn(tasks.asciidoctor)
	from("build/docs/asciidoc")
	into("src/main/resources/static/docs")
}

tasks.build {
	dependsOn(tasks.getByName("copyDocument"))
}

