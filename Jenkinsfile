pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                // Add build steps here, such as compiling code, running tests, etc.
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                // Add test steps here
                sh 'mvn test'
            }
        }
        /*
        stage('Deploy') {
            steps {
                // Add deployment steps here, such as deploying to a server
                sh 'mvn deploy'
            }
        }*/
         stage('Clean') {
            steps {
                // Add test steps here
                sh 'mvn clean'
            }
        }
          stage('Checkout GIT') {
            steps {
                echo 'pulling...'
                    git branch: 'feature',
                    url : 'https://github.com/cadalevans/kaddem.git'
                // Add test steps here
               
            }
        }
         stage('SonarQube') {
            steps {
                // Add test steps here
                withSonarQubeEnv('SonarQube')
                sh 'mvn sonar:sonar'
            }
        }
    }
    
    post {
        always {
            // Clean up steps, if needed
            deleteDir()
        }
        success {
            // Actions to perform if the pipeline succeeds
            echo 'Pipeline succeeded!'
        }
        failure {
            // Actions to perform if the pipeline fails
            echo 'Pipeline failed!'
        }
    }
}
