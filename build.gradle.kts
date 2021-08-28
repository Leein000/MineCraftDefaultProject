plugins {
    kotlin("jvm") version "1.5.21"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    `maven-publish`
}

group = "kr.kro.minestar.murder"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-oss-snapshots"
    }
    maven(url = "https://jitpack.io/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("net.kyori:adventure-api:4.7.0")
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")

    implementation("org.reflections:reflections:0.9.12")

    //project_TL
    implementation("net.projecttl:InventoryGUI-api:4.1.1")

    //MineStar
}

tasks{
    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
        }
    }
    create<Jar>("sourcesJar") {
        archiveClassifier.set("source")
        from(sourceSets["main"].allSource)
    }
    shadowJar {
        archiveBaseName.set(project.name)
        archiveClassifier.set("")
        archiveVersion.set(project.version.toString())
        doLast {
            // jar file copy
            copy {
                from(archiveFile)
                val plugins = File("C:\\Users\\MineStar\\Desktop\\MC Server folder\\MCserver 1.17.1 - Murder\\plugins")
                into(if (File(plugins, archiveFileName.get()).exists()) plugins else plugins)
            }
        }
    }
}
publishing {
    publications {
        create<MavenPublication>(project.name) {
            artifact(tasks["sourcesJar"])
            from(components["java"])
        }
    }
}