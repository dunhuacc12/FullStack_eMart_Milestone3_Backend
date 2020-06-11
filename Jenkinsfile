pipeline {
    agent any

    stages{
        stage('validate') {
            steps {
                // check tomcat path is setted
                echo "${tomcat_webapps_path}"
            }
        }
        stage('git clone') {
            steps {
                git credentialsId: '878e707f-a22e-4319-8bc5-267768a3fa33', url: 'https://github.com/dunhuacc12/FullStack_eMart_Milestone3_Backend.git'
            }
        }
        stage('Build') {
            steps {
                dir('./'){
                    bat 'mvn clean install -Dmaven.test.skip=true'
                }
            }
        }
        stage('deploy') {
            steps {
                // deploy by windows command
                echo 'start to deploy'
                bat "deploy.bat . ${tomcat_webapps_path}"
            }
        }
    }
    post {
        success {
            echo 'Pipeline process sucessful'
            // junit '**/target/*.xml'
        }
        failure {
            echo 'Pipeline process faild'
        }
    }
}