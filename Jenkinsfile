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
            steps {
                sh "docker push tech99/calculator"
            }
        }

        stage("Deploy to staging"){
            steps{
                sh "docker run -d --rm -p 8765:8080 --name calculator_stage tech99/calculator"
            }
        }

        stage("Acceptance Test"){
            steps{
                sleep 60
                sh "./gradlew acceptanceTest -Dcalculator.url=http://localhost:8765"
                // sh "chmod +x acceptance_test.sh && ./acceptance_test/sh"
            }
        }
    }
    post{
        always{
            sh "docker stop calculator_stage"
        }
    }
}