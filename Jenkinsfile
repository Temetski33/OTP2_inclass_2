pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
            PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
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

        stage('Generate Report') {
            steps {
                bat 'mvn jacoco:report'
            }
        }


        stage('Publish Coverage Report') {
            steps {
                jacoco()
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