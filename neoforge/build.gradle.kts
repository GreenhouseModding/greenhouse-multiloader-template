import house.greenhouse.examplemod.gradle.Properties
import org.apache.tools.ant.filters.LineContains

plugins {
	id("conventions.loader")
	id("net.neoforged.moddev")
	id("me.modmuss50.mod-publish-plugin")
}

dependencies {
	testImplementation(libs.neoforge.test.framework)
}

neoForge {
	version = libs.versions.neoforge.get()
	parchment {
		minecraftVersion = libs.versions.minecraft.parchment.get()
		mappingsVersion = libs.versions.parchment.get()
	}
	addModdingDependenciesTo(sourceSets["test"])

	val at = project(":xplat").file("src/main/resources/${Properties.MOD_ID}.cfg")
	if (at.exists())
		setAccessTransformers(at)
	validateAccessTransformers = true

	mods {
		register(Properties.MOD_ID) {
			sourceSet(sourceSets["test"])
		}
		register(Properties.MOD_ID + "_gametest") {
			sourceSet(sourceSets["gametest"])
		}
	}

	runs {
		configureEach {
			systemProperty("forge.logging.markers", "REGISTRIES")
			systemProperty("forge.logging.console.level", "debug")
			systemProperty("neoforge.enabledGameTestNamespaces", "${Properties.MOD_ID},${Properties.MOD_ID}_test")
		}
		create("client") {
			client()
			ideName = "NeoForge Client (:${project.name})"
			gameDirectory.set(file("runs/client"))
			sourceSet = sourceSets["test"]
			jvmArguments.set(setOf("-Dmixin.debug.verbose=true", "-Dmixin.debug.export=true"))
		}
		create("server") {
			server()
			ideName = "NeoForge Server (:${project.name})"
			gameDirectory.set(file("runs/server"))
			programArgument("--nogui")
			sourceSet = sourceSets["test"]
			jvmArguments.set(setOf("-Dmixin.debug.verbose=true", "-Dmixin.debug.export=true"))
		}
		create("gameTest") {
			type = "gameTestServer"
			ideName = "NeoForge Game Tests (:${project.name})"
			gameDirectory.set(file("build/gametest"))
			programArgument("--nogui")
			sourceSet = sourceSets["gametest"]
			loadedMods = setOf(
				mods.getByName(Properties.MOD_ID),
				mods.getByName("${Properties.MOD_ID}_gametest")
			)
			systemProperty("neoforge.enabledGameTestNamespaces", Properties.MOD_ID + "_gametest")
		}
	}
}

tasks {
	named<ProcessResources>("processResources").configure {
		filesMatching("*.mixins.json") {
			filter<LineContains>("negate" to true, "contains" to setOf("refmap"))
		}
	}
}

publishMods {
	file.set(tasks.named<org.gradle.jvm.tasks.Jar>("jar").get().archiveFile)
	modLoaders.add("neoforge")
	changelog = rootProject.file("CHANGELOG.md").readText()
	displayName = "v${Properties.MOD_VERSION} (NeoForge ${libs.versions.minecraft.asProvider().get()})"
	version = "${Properties.MOD_VERSION}+${libs.versions.minecraft.asProvider().get()}-neoforge"
	type = BETA

	curseforge {
		projectId = Properties.CURSEFORGE_PROJECT_ID
		accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")

		minecraftVersions.addAll(Properties.SUPPORTED_MINECRAFT_VERSIONS)
		javaVersions.add(JavaVersion.VERSION_21)

		clientRequired = true
		serverRequired = true

		optional("emi")
		optional("item-descriptions")
	}

	modrinth {
		projectId = Properties.MODRINTH_PROJECT_ID
		accessToken = providers.environmentVariable("MODRINTH_TOKEN")

		minecraftVersions.addAll(Properties.SUPPORTED_MINECRAFT_VERSIONS)

		optional("emi")
		optional("item-descriptions")
	}
}
