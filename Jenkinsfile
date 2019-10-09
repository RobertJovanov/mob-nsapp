node{

    stage("Checkout") {
        checkout([
            $class: 'GitSCM',
            branches: [[name: '*/master']],
            doGenerateSubmoduleConfigurations: false,
            extensions: [],
            submoduleCfg: [],
            userRemoteConfigs: [[url: 'https://github.com/RobertJovanov/mob-nsapp.git']]
        ])
    }

    stage("Build") {
        dir('mobtravelapp') {
            sh 'mvn clean package'
            archiveArtifacts 'target/mobtravelapp-0.0.1-SNAPSHOT.jar'
        }
    }

    stage("Docker build & push") {
        def image = docker.build(
            "473293451041.dkr.ecr.eu-central-1.amazonaws.com/mob-nsapp"
        )
        image.push("0.${env.BUILD_NUMBER}")
    }

    stage("Approval"){
        notify("Approval is needed for deployment")
        input 'Is this ready for deployment to production?'
    }
}

def notify(status) {
    emailext(
        to: "rjovanov@mobiquityinc.com",
        subject: "${status}: Job '${env.JOB_NAME} [${env.BUILD_NAME}]'",
        body: """${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
        Check console output at ${env.BUILD_URL}""",)
}
