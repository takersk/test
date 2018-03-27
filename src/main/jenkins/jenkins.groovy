node {
    properties(
        [
            [$class: 'ParametersDefinitionProperty', parameterDefinitions:
                [
                    [$class: 'BooleanParameterDefinition', defaultValue: false, description: 'test skip', name: 'skipTests']
                ]
            ]])

    def gradleHome

    stage('Preparation') {
        echo "Current workspace : ${workspace}"
        gradleHome = tool 'gradle-4.3'
        echo "gradle home : ${gradleHome}"
        echo "skipTests : ${skipTests}"
    }
    stage('Checkout') {
        checkout scm
    }
    stage('Build') {
        sh "'${gradleHome}/bin/gradle' clean"
    }

    if (skipTests != "true") {
        stage('Test') {
            sh "'${gradleHome}/bin/gradle' test"
        }
        stage('Store Test Results') {
            junit allowEmptyResults: true, keepLongStdio: true, testResults: 'build/test-results/*.xml'
        }
    }

    stage('Deploy') {
        echo "Deploy is not yet implemented"
    }
}
