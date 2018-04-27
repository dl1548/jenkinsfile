//Jenkinsfile (Declarative Pipeline)//
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'ls -la'
                sh 'pwd'
                sh 'mvn clean -f pipeline-test/moni/pom.xml'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'pwd'
                sh 'mvn clean verify sonar:sonar '
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
