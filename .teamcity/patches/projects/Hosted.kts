package patches.projects

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project
import jetbrains.buildServer.configs.kotlin.projectFeatures.GitLabIssueTracker
import jetbrains.buildServer.configs.kotlin.projectFeatures.gitlabIssues
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the project with id = 'Hosted'
accordingly, and delete the patch script.
*/
changeProject(RelativeId("Hosted")) {
    features {
        val feature1 = find<GitLabIssueTracker> {
            gitlabIssues {
                id = "PROJECT_EXT_274"
                displayName = "Local server"
                repositoryURL = "http://10.128.93.61/kilina/nomaster"
                authType = anonymous()
            }
        }
        feature1.apply {
            repositoryURL = "http://10.128.93.61/jetbrains/pullrequests"
            authType = accessToken {
                accessToken = "credentialsJSON:1fe34134-65f4-4015-b7d0-aa9de89d10d2"
            }
        }
    }
}