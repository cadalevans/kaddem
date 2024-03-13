pipeline {
    agent any
    
    stages {

        
          stage('Build') {
                   steps {
                       echo 'Building Maven project...'
                       script {
                           try {
                               sh 'mvn clean install jacoco:prepare-agent test jacoco:report'
                           } catch (Exception e) {
                               currentBuild.result = 'FAILURE'
                               error "Build failed: ${e.message}"
                           }
                       }
                   }
               }

 stage('JUNIT/MOCKITO') {
            steps {
                echo 'Running JUnit tests...'
                script {
                    try {
                        sh 'mvn test'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        error "JUnit/Mockito tests failed: ${e.message}"
                    }
                }
            }
        }

        /*
        stage('Deploy') {
            steps {
                // Add deployment steps here, such as deploying to a server
                sh 'mvn deploy'
            }
        }

        */
        stage('Checkout GIT') {
                  steps {
                      echo 'Pulling from Git...'
                      git branch: 'feature',
                      url: 'https://github.com/cadalevans/kaddem.git'
                  }
              }


         stage('SonarQube') {
              steps {
                          echo 'Running JUnit tests...'
                          script {
                              try {
                                  sh 'mvn sonar:sonar'
                              } catch (Exception e) {
                                  currentBuild.result = 'FAILURE'
                                  error "Sonarqube tests failed: ${e.message}"
                              }
                          }
                      }
        }

                    
        stage('Nexus') {
                            steps {
                                            // Deploy the artifacts to Nexus repository
                                            script {
                                                try{
                                                def mvnCmd = 'mvn deploy -DskipTests=true' // Skip tests during deployment
                                                mvnCmd += ' -DaltDeploymentRepository=deploymentRepo::default::http://192.168.33.10:8081/repository/maven-releases/' // Nexus repository URL
                                                sh mvnCmd
                                                } catch (Exception e) {
                                                     currentBuild.result = 'FAILURE'
                                                      error "Nexus Deploy failed: ${e.message}"
                                                }

                                            }
                            }
        }

        stage('Build Docker Image') {
                    steps {
                        script {
                          try{
                            // Utilize Docker to build the image using the provided Dockerfile
                            docker.build('spring-app', '--build-arg NEXUS_URL=$NEXUS_URL --build-arg ARTIFACT_PATH=$ARTIFACT_PATH .')
                             } catch (Exception e) {
                                 currentBuild.result = 'FAILURE'
                                 error "Nexus Deploy failed: ${e.message}"
                                 }
                        }
                    }
        }

           stage('Push image to Hub'){
                                   steps{
                                       script{
                                          withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                                          sh 'docker login -u franck625 -p Pamfranck10'
                                          }
                                          sh 'docker push /app/kaddem-1.0.0'
                                       }
                                   }
           }


     stage('Run Docker Compose') {
                steps {
                    script {
                        // Run Docker Compose up command to launch containers defined in the Docker Compose file
                        sh 'docker-compose up -d'
                    }
                }
            }

        /*

      stage('Build docker image') {
                steps {
                    // Add test steps here
                    //withSonarQubeEnv('SonarQube')
                    sh 'docker build -t franck625/kaddem-4.0 .'
                }
       }

                    stage('Push image to Hub'){
                               steps{
                                   script{
                                      withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                                      sh 'docker login -u franck625 -p Pamfranck10'
                                      }
                                      sh 'docker push /app/kaddem-4.0'
                                   }
                               }
                    }

        */

    }


    // Jacoco plugin with Jenkins



 post {
        always {
            // Publish JaCoCo coverage report as artifact
            archiveArtifacts(artifacts: 'target/site/jacoco/index.html', onlyIfSuccessful: true)
        }

        success {
            echo 'Pipeline successful!'
            emailext(
                to: 'mandoupam@gmail.com', // Replace with recipient email address
                subject: 'Jenkins Build Notification - Success',
                body: 'The Jenkins build was successful.'
            )
            slackSend(color: 'good', message: "Build succeeded! ${currentBuild.fullDisplayName}")
        }

        failure {
            echo 'Pipeline failed!'
            emailext(
                to: 'mandoupam@gmail.com', // Replace with recipient email address
                subject: 'Jenkins Build Notification - Failure',
                body: 'The Jenkins build failed.'
            )
            slackSend(color: 'danger', message: "Build failed! ${currentBuild.fullDisplayName}. Error: ${currentBuild.rawBuild.getLog(100)}")
        }
    }
}