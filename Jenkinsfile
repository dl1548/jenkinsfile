//Jenkinsfile (Declarative Pipeline)//
//author: lizili//
pipeline {
    agent any

    triggers {
        //cron('H/10 * * * 1-5')//
        pollSCM('H/10 * * * 1-5')
    }
    stages {
        stage('git') {
            steps {
                echo 'Git..'
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [],
                    submoduleCfg: [],
                    userRemoteConfigs: [[
                        credentialsId: '221e2ea3-be6c-41f2-b43d-f55afa078a7d',
                        url: 'https://github.com/dl1548/monitor.git']]
                    ])
            }
        }

        stage('Build') {
            steps {
                echo 'Building..'
                //sh 'mvn clean install -f moni/pom.xml'//
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                //sh 'mvn clean verify sonar:sonar -f moni/pom.xml'//
            }
        }
        stage('Deploy') {
            when {
                expression {
                    currentBuild.result == null || currentBuild.result == 'SUCCESS'
                }
            }
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
                /*
                mail body: '${env.BUILD_ID} on ${env.JENKINS_URL}',
                     from: 'lizili@jingkunsystem.com',
                     replyTo: '',
                     subject: 'project build FAILURE',
                     to: 'lizili@jingkunsystem.com'
                */S
            }
        }
    }

    post {
        /*
        always {
            echo 'This will always run'
        }
        */
        success {
            echo 'send mail-BUILD-SUCCESS'
            mail body: '${env.BUILD_ID} on ${env.JENKINS_URL}',
                 from: 'lizili@jingkunsystem.com',
                 replyTo: '',
                 subject: 'project build SUCCESS',
                 to: 'lizili@jingkunsystem.com'
        }
        failure {
            echo 'send mail-BUILD-FAILURE'
            mail body: '${env.BUILD_ID} on ${env.JENKINS_URL}',
                 from: 'lizili@jingkunsystem.com',
                 replyTo: '',
                 subject: 'project build FAILURE',
                 to: 'lizili@jingkunsystem.com'
        }
        /*
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
        */
    }
}
