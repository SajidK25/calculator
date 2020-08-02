pipeline{
    agent any
    triggers{
        pollSCM('* * * * *')
    }
    stages{
        stage("Compile"){
            steps{
                sh "./gradlew compileJava"
            }
        }

        stage("Unit test") {
            steps {
                sh "./gradlew test"
            }
        }
        stage("Code Coverage"){
            steps{
                sh "./gradlew jacocoTestReport"
                publishHTML(
                    target:[
                        reportDir:'build/reports/jacoco/test/html/',
                        reportFiles:'index.html',
                        reportName: 'JaCoCo Report'
                    ]
                )
                sh "./gradlew jacocoTestCoverageVerification"
            }
        }
        stage("Package"){
            steps{
                sh './gradlew build'
            }
        }
        stage("Docker login") {
            steps {
            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'docker-hub-credentials',
                   usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
               sh "docker login --username $USERNAME --password $PASSWORD"
          }
     }
        }

        stage("Docker push"){
            steps{
                script{
                    docker.withRegistry('',registryCredential)
                    dockerImage.push()
                }
            }
        }
    }
}