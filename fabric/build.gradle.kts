import house.greenhouse.examplemod.gradle.Properties

plugins {
	id("conventions.loader")
	alias(libs.plugins.loom)
	alias(libs.plugins.mod.publish)
}

dependencies {
	minecraft(libs.minecraft)
	mappings(loom.layered {
		officialMojangMappings()
		parchment(libs.parchment)
	})

	modImplementation(libs.fabric.loader)
	modImplementation(libs.fabric.api)
	modLocalRuntime(libs.mod.menu)
}

fabricApi {
	configureDataGeneration {
		modId = Properties.MOD_ID + "_datagen"
		outputDirectory = file("../xplat/src/generated/resources")
		createSourceSet = true
		createRunConfiguration = false
		addToResources = false
	}
	@Suppress("UnstableApiUsage")
	configureTests {
		modId = Properties.MOD_ID + "_tests"
		enableGameTests = false
		enableClientGameTests = false
		clearRunDirectory = true
	}
}

loom {
	val aw = file("src/main/resources/${Properties.MOD_ID}.accesswidener")
	if (aw.exists())
		accessWidenerPath.set(aw)
	mixin {
		defaultRefmapName.set("${Properties.MOD_ID}.refmap.json")
	}
	mods {
		register(Properties.MOD_ID) {
			sourceSet(sourceSets["main"])
			sourceSet(sourceSets["test"])
		}
		register(Properties.MOD_ID + "_gametest") {
			sourceSet(sourceSets["gametest"])
		}
	}
	runs {
		named("client") {
			client()
			configName = "Fabric Client"
			setSource(sourceSets["test"])
			ideConfigGenerated(true)
			vmArgs("-Dmixin.debug.verbose=true", "-Dmixin.debug.export=true")
			runDir("runs/client")
		}
		named("server") {
			server()
			configName = "Fabric Server"
			setSource(sourceSets["test"])
			ideConfigGenerated(true)
			vmArgs("-Dmixin.debug.verbose=true", "-Dmixin.debug.export=true")
			runDir("runs/server")
		}
		register("datagen") {
			server()
			configName = "Fabric Datagen"
			setSource(sourceSets["datagen"])
			ideConfigGenerated(true)
			vmArgs(
				"-Dfabric-api.datagen",
				"-Dfabric-api.datagen.output-dir=${file("../xplat/src/generated/resources")}",
				"-Dfabric-api.datagen.modid=${Properties.MOD_ID}_datagen"
			)
			runDir("build/datagen")
		}
		register("gameTest") {
			server()
			configName = "Fabric Game Tests"
			runDir("build/gametest")
			setSource(sourceSets["gametest"])
			ideConfigGenerated(true)
			property("fabric-api.gametest")
			runDir("build/gametest")
		}
	}
}

gradle.projectsEvaluated {
	sourceSets {
		getByName("datagen") {
			compileClasspath += project(":xplat").sourceSets["main"].output
			runtimeClasspath += project(":xplat").sourceSets["main"].output
		}
	}
}

tasks {
	named<ProcessResources>("processResources").configure {
		exclude("${Properties.MOD_ID}.cfg")
	}
}

publishMods {
	file.set(tasks.named<org.gradle.jvm.tasks.Jar>("remapJar").get().archiveFile)
	modLoaders.add("fabric")
	changelog = rootProject.file("CHANGELOG.md").readText()
	displayName = "v${Properties.MOD_VERSION} (Fabric ${libs.versions.minecraft.asProvider().get()})"
	version = "${Properties.MOD_VERSION}+${libs.versions.minecraft.asProvider().get()}-fabric"
	type = BETA

	curseforge {
		projectId = Properties.CURSEFORGE_PROJECT_ID
		accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")

		minecraftVersions.addAll(Properties.SUPPORTED_MINECRAFT_VERSIONS)
		javaVersions.add(JavaVersion.VERSION_21)

		clientRequired = true
		serverRequired = true

		requires("fabric-api")
	}

	modrinth {
		projectId = Properties.MODRINTH_PROJECT_ID
		accessToken = providers.environmentVariable("MODRINTH_TOKEN")

		minecraftVersions.addAll(Properties.SUPPORTED_MINECRAFT_VERSIONS)

		requires("fabric-api")
	}
}
