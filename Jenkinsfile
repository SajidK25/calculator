pipeline{
    agent any
    stages{
        stage("compile"){
            steps{
                sh "./gradlew compileJava"
            }
        }
        stage("test"){
            steps{
                sh "./gradlew test"
            }
        }
    }
}