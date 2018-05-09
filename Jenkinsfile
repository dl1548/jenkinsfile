//Jenkinsfile (Declarative Pipeline)//
pipeline {
    agent any


    triggers {
        //cron('H/10 * * * 1-5')//
        pollSCM('H/10 * * * 1-5')
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'pwd'
                sh 'mvn clean install -f moni/pom.xml'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'pwd'
                //sh 'mvn clean verify sonar:sonar -f moni/pom.xml'//
            }
        }
        stage('Deploy') {
            steps {
                echo 'upload file to server....'
                /*
                sh "sshpass -p centos scp $WORKSPACE/moni/target/monitor.war root@192.168.1.55:/root/war"
                echo 'Restart tomcat.....'
                sh "sshpass -p centos ssh root@192.168.1.55 '/usr/bin/bash ~/deploy.sh deploy monitor 80 /usr/local/tomcat-7.0.85 $BUILD_NUMBER'"
                */
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: '192_168_1_55',
                            transfers: [
                                sshTransfer(
                                    excludes: '',
                                    execCommand: '~/deploy.sh deploy monitor 80 /usr/local/tomcat-7.0.85 $BUILD_NUMBER',
                                    execTimeout: 120000,
                                    flatten: false,
                                    makeEmptyDirs: false,
                                    noDefaultExcludes: false,
                                    patternSeparator: '[, ]+',
                                    remoteDirectory: 'root/war/',
                                    remoteDirectorySDF: false,
                                    removePrefix: 'moni/target/',
                                    sourceFiles: 'moni/target/*.war'
                                )
                            ],
                            usePromotionTimestamp: false,
                            useWorkspaceInPromotion: false,
                            verbose: false
                        )
                    ]
                )
            }
        }
        stage('mail'){
            steps {
                echo 'send mail'
                mail bcc: '', body: 'pipeline mail test', cc: '', from: '', replyTo: '', subject: 'pipeline-test', to: 'lizili@jingkunsystem.com'
            }
        }
    }

    /*
    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
    */
}
