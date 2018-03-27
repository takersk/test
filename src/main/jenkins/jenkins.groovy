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
    }
    stage('Checkout') {
        checkout scm
    }
    stage('Build') {
        sh "'${gradleHome}/bin/gradle' clean"
    }

    if (skipTests != true) {
        stage('Test') {
            sh "'${gradleHome}/bin/gradle' test"
        }
        stage('Store Test Results') {
            junit '**/target/surefire-reports/TEST-*.xml'
        }
    }

    stage('Deploy') {
        echo "Deploy is not yet implemented"
    }
}