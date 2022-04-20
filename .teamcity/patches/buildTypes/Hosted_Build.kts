package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Hosted_Build'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Hosted_Build")) {
    triggers {
        remove {
            vcs {
            }
        }
    }
}