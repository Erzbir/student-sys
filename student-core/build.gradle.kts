plugins {
    id("java")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":student-api"))
    implementation(project(":dispatcher-api"))
    runtimeOnly(project(":dispatcher-core"))
    implementation("cn.hutool:hutool-jwt:5.8.27")
}

tasks.test {
    useJUnitPlatform()
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}