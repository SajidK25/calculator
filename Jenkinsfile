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
                sh "docker login --username 'tech99' --password '!Rafi_420*'"
            }
        }
}
        stage("Docker push"){
            steps{
                sh "docker push tech99/calculator"
            }
        }
    }
}