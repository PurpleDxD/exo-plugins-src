buildscript {
    repositories {
        gradlePluginPortal()
    }
}

plugins {
    java
    checkstyle
}

project.extra["GithubUrl"] = "https://github.com/purpledxd/exo-plugins-src"

apply<BootstrapPlugin>()

allprojects {
    group = "com.openosrs.externals.exo"
    apply<MavenPublishPlugin>()
}

allprojects {
    apply<MavenPublishPlugin>()

    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
    }
}

subprojects {
    group = "com.openosrs.externals.exo"

    project.extra["PluginProvider"] = "Exo"
    project.extra["ProjectSupportUrl"] = ""
    project.extra["PluginLicense"] = "3-Clause BSD License"

    repositories {
        jcenter {
            content {
                excludeGroupByRegex("com\\.openosrs.*")
            }
        }

        exclusiveContent {
            forRepository {
                mavenLocal()
            }
            filter {
                includeGroupByRegex("com\\.openosrs.*")
            }
        }
    }

    apply<JavaPlugin>()

    val oprsVersion = "4.25.1"

    dependencies {
        annotationProcessor(Libraries.lombok)
        annotationProcessor(Libraries.pf4j)

        compileOnly("com.openosrs:http-api:$oprsVersion+")
        compileOnly("com.openosrs:runelite-api:$oprsVersion+")
        compileOnly("com.openosrs:runelite-client:$oprsVersion+")
        compileOnly("com.openosrs.rs:runescape-api:$oprsVersion+")

        compileOnly(Libraries.findbugs)
        compileOnly(Libraries.apacheCommonsText)
        compileOnly(Libraries.gson)
        compileOnly(Libraries.guice)
        compileOnly(Libraries.lombok)
        compileOnly(Libraries.okhttp3)
        compileOnly(Libraries.pf4j)
        compileOnly(Libraries.rxjava)


    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    configure<PublishingExtension> {
        repositories {
            maven {
                url = uri("$buildDir/repo")
            }
        }
        publications {
            register("mavenJava", MavenPublication::class) {
                from(components["java"])
            }
        }
    }

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }

        register<Copy>("copyDeps") {
            into("./build/deps/")
            from(configurations["runtimeClasspath"])
        }

        withType<Jar> {
            doLast {
                copy {
                    from("./build/libs/")
                    into(System.getProperty("user.home") + "/Documents/JavaProjects/My Plugins Jars")
                }
            }
        }

        withType<AbstractArchiveTask> {
            isPreserveFileTimestamps = false
            isReproducibleFileOrder = true
            dirMode = 493
            fileMode = 420
        }
    }
}