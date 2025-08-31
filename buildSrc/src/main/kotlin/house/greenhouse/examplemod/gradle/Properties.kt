package house.greenhouse.examplemod.gradle

object Properties {
	const val MOD_VERSION = "0.1.0"
	const val JAVA_VERSION = 21

	const val GROUP = "house.greenhouse"
	const val MOD_NAME = "Example Mod"
	const val MOD_ID = "examplemod"
	const val MOD_AUTHOR = "Greenhouse Team"
	val MOD_CONTRIBUTORS = listOf("Insert", "People", "Here")
	const val DESCRIPTION = "A cool ass mod!"
	const val LICENSE = "CC0-1.0"

	val SUPPORTED_MINECRAFT_VERSIONS = listOf("1.21.6", "1.21.7", "1.21.8")

	const val FABRIC_LOADER_RANGE = ">=0.16"
	const val FABRIC_MINECRAFT_RANGE = ">=1.21.6 <=1.21.8"

	const val NEOFORGE_LOADER_RANGE = "[4,)"
	const val NEOFORGE_MINECRAFT_RANGE = "[1.21.8,1.21.9)"

	const val CURSEFORGE_PAGE = "https://www.curseforge.com/minecraft/mc-mods/curseforge-project"
	const val CURSEFORGE_PROJECT_ID = "000000"
	const val MODRINTH_PAGE = "https://modrinth.com/mod/modrinth-project"
	const val MODRINTH_PROJECT_ID = "????????"
	const val GITHUB_REPO = "GreenhouseModding/greenhouse-multiloader-template"
	const val GITHUB_COMMITISH = "1.21.8"
}
