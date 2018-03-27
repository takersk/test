def useTest = true
def useBuild = true
def useDeploy = true

stage(
    "Flow Check", {
        try {
            println " TEST FLOW = $USE_TEST"
            useTest = "$USE_TEST" == "true"
        } catch (MissingPropertyException e) {
            println " TEST FLOW = true"
        }
        try {
            println " BUILD FLOW = $USE_BUILD"
            useBuild = "$USE_BUILD" == "true"
        } catch (MissingPropertyException e) {
            println " BUILD FLOW = true"
        }
        try {
            println " DEPLOY FLOW = $USE_DEPLOY"
            useBuild = "$USE_DEPLOY" == "true"
        } catch (MissingPropertyException e) {
            println " BUILD DEPLOY = true"
        }
})

stage("Git CheckOut", {
    if (useTest || useBuild)
        {
            println "Git CheckOut Started"
            checkout (
                [
                        $class : 'GitSCM',
                        branches : [[name: '${BRANCH_SELECTOR}']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions : [],
                        submoduleCfg : [],
                        userRemoteConfigs : [[url: '${GIT_URL']]
                ]
            ) println "Git CheckOut End"
        } else {
            println "Git CheckOut Skip"
        }
})

stage('Test') {
    if (useTest) { println "Test Started"
        try {
            sh "${tool name: GRADLE_VERSION, type: 'hudson.plugins.gradle.GradleInstallation'}/bin/gradle test -Dorg.gradle.daemon=true"
        } finally {
            junit allowEmptyResults: true, keepLongStdio: true, testResults: 'build/test-results/*.xml'
        }
        println "Test End"
    } else {
        println "Test Skip"
    }
}

stage("Deploy", {
    if(useDeploy) {
        println "Deploy Started"
        println "Deploy End"
    } else {
        println "Deploy Skip"
    }
})
