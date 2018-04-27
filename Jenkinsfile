//Jenkinsfile (Declarative Pipeline)//
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'ls -la'
                sh 'mvn clean'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn clean verify sonar:sonar'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
