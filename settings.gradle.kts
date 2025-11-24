plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "stahl-geist"

include("stahl-admin")
include("stahl-config")
include("stahl-eureka")
include("stahl-gateway")
include("stahl-shared")
include("stahl-users")
include("stahl-bots")
include("stahl-bots:nivora")
include("stahl-bots:serafim")
include("stahl-bots:zentra")
include("stahl-models")
include("stahl-resource")
include("stahl-bots:bots-shared")
include("stahl-posts")