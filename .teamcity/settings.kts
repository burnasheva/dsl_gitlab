import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.projectFeatures.gitlabEEConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.gitlabIssues
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

project {

    vcsRoot(Cloud_HttpsGitlabComNBurnashevaMvn2springOpenjpaGitRefsHeadsMaster)

    subProject(Cloud)
    subProject(CloudPublic)
    subProject(Hosted)
}

object Cloud_HttpsGitlabComNBurnashevaMvn2springOpenjpaGitRefsHeadsMaster : GitVcsRoot({
    name = "https://gitlab.com/n.burnasheva/mvn2-spring-openjpa.git#refs/heads/master"
    url = "https://gitlab.com/n.burnasheva/mvn2-spring-openjpa.git"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
})


object Cloud : Project({
    name = "Cloud"

    vcsRoot(Cloud_GitlabPrivateRepository)

    buildType(Cloud_HelloWorld)

    features {
        gitlabIssues {
            id = "PROJECT_EXT_265"
            displayName = "Project without master branch"
            repositoryURL = "https://gitlab.com/n.burnasheva/without-master-branch"
            authType = accessToken {
                accessToken = "credentialsJSON:2e407222-ca07-486c-ae9d-3a878bea3f45"
            }
        }
    }
})

object Cloud_HelloWorld : BuildType({
    name = "hello world"

    vcs {
        root(Cloud_GitlabPrivateRepository)
    }
})

object Cloud_GitlabPrivateRepository : GitVcsRoot({
    name = "gitlab private repository"
    url = "https://gitlab.com/n.burnasheva/without-master-branch.git"
    branch = "refs/heads/trash"
    authMethod = password {
        userName = "n.burnasheva"
        password = "credentialsJSON:2e407222-ca07-486c-ae9d-3a878bea3f45"
    }
    param("oauthProviderId", "PROJECT_EXT_144")
})


object CloudPublic : Project({
    name = "Cloud Public"

    buildType(CloudPublic_BuildInPublicRepository)

    features {
        gitlabIssues {
            id = "PROJECT_EXT_267"
            displayName = "public project"
            repositoryURL = "https://gitlab.com/n.burnasheva/mvn2-spring-openjpa"
            authType = accessToken {
                accessToken = "credentialsJSON:2e407222-ca07-486c-ae9d-3a878bea3f45"
            }
        }
    }
})

object CloudPublic_BuildInPublicRepository : BuildType({
    name = "build in public repository"

    vcs {
        root(Cloud_HttpsGitlabComNBurnashevaMvn2springOpenjpaGitRefsHeadsMaster)
    }
})


object Hosted : Project({
    name = "Hosted"

    vcsRoot(Hosted_Http101289361jetbrainsPullrequestsGitRefsHeadsMaster)

    buildType(Hosted_Build)

    features {
        gitlabEEConnection {
            id = "PROJECT_EXT_266"
            displayName = "GitLab CE/EE"
            serverUrl = "http://10.128.93.61/"
            applicationId = "6deb01a38814457ecb606f2558e668b6eadfe3f24ceff7155da9f1efd8d33d13"
            clientSecret = "credentialsJSON:ddadcc19-7637-415c-932c-495e87899cc2"
        }
    }
})

object Hosted_Build : BuildType({
    name = "Build"

    vcs {
        root(Hosted_Http101289361jetbrainsPullrequestsGitRefsHeadsMaster)
    }

    triggers {
        vcs {
        }
    }

    features {
        pullRequests {
            vcsRootExtId = "${Hosted_Http101289361jetbrainsPullrequestsGitRefsHeadsMaster.id}"
            provider = gitlab {
                authType = token {
                    token = "credentialsJSON:8f85892c-e2c0-4dd7-aee0-5e270109feea"
                }
            }
        }
    }
})

object Hosted_Http101289361jetbrainsPullrequestsGitRefsHeadsMaster : GitVcsRoot({
    name = "http://10.128.93.61/jetbrains/pullrequests.git#refs/heads/master"
    url = "http://10.128.93.61/jetbrains/pullrequests.git"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "jetbrains"
        password = "credentialsJSON:4fe96991-24b8-4937-8713-e2db8b21ab84"
    }
})
