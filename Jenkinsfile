node {
    try {
        // An explicit checkout is still needed, otherwise Jenkins
        // uses the last version of the repository that it has.
        checkout scm

        stage("Linting & validation") {
            sh "helm lint --strict ./helm/hello-world/"
            sh "helm lint --strict ./helm/mob-nsapp/"
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
    
        stage("Deploy to EKS") {
            sh "helm upgrade --install hello-world ./helm/hello-world/"
            sh "helm upgrade --install mob-nsapp ./helm/mob-nsapp/"
        }
    }
    catch (Exception e) {
        notify("The pipeline failed")
        throw e
    }
}

def notify(status) {
    emailext(
        to: "rjovanov@mobiquityinc.com",
        subject: "${status}: Job '${env.JOB_NAME} [${env.BUILD_NAME}]'",
        body: """${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
        Check console output at ${env.BUILD_URL}""",)
}
