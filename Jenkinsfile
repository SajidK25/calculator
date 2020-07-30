pipeline{
    agent any
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
                        reportDir:'build/reports/tests/test/index.html',
                        reportFiles:'index.html',
                        reportName: 'JaCoCo Report'
                    ]
                )
                sh "./gradlew jacocoTestCoverageVerification"
            }
        }
    }
}