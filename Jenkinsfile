pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
            PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
            JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21'  // Adjust to your actual JDK pat
            SONARQUBE_SERVER = 'SonarQubeServer'  // The name of the SonarQube server configured in Jenkins
            SONAR_TOKEN = credentials('sonar-token')
            DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
            DOCKERHUB_REPO = 'temetski33/fc'
            DOCKER_IMAGE_TAG = 'v1'
        }



    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/Temetski33/OTP2_inclass_2.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeServer') {
                    bat """
                        ${tool 'SonarScanner'}\\bin\\sonar-scanner ^
                        -Dsonar.projectKey=fuelcalc ^
                        -Dsonar.sources=src ^
                        -Dsonar.projectName=fuelcalc ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.login=${env.SONAR_TOKEN} ^
                        -Dsonar.java.binaries=target/classes
                        """
                }
            }
                }




        stage('Build Docker Image') {
                    steps {
                        script {
                            docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                        }
                    }
                }


        stage('Push Docker Image to Docker Hub') {
                    steps {
                        script {
                            docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                                docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                            }
                        }
                    }
                }




    }
}