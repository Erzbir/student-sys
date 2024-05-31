plugins {
    id("java")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":student-api"))
    implementation(project(":dispatcher-api"))
    runtimeOnly(project(":dispatcher-core"))
    compileOnly("cn.hutool:hutool-core:5.8.27")
    compileOnly("cn.hutool:hutool-json:5.8.27")
    compileOnly("cn.hutool:hutool-http:5.8.27")
}

tasks.test {
    useJUnitPlatform()
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}