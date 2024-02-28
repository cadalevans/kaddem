pipeline {
    agent any
    
    stages {

        
        stage('Build') {
            steps {
               // Execute Maven build with JaCoCo coverage
                               sh 'mvn clean install jacoco:prepare-agent test jacoco:report'
            }
        }

        stage('JUNIT/MOCKITO') {
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
        }

        */
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
                //withSonarQubeEnv('SonarQube')
                sh 'mvn sonar:sonar'
            }
        }

        stage('Nexus') {
                            steps {
                                            // Deploy the artifacts to Nexus repository
                                            script {
                                                def mvnCmd = 'mvn deploy -DskipTests=true' // Skip tests during deployment
                                                mvnCmd += ' -DaltDeploymentRepository=deploymentRepo::default::http://192.168.33.10:8081/repository/maven-snapshots/'// Nexus repository URL
                                                sh mvnCmd
                                            }
                            }
        }



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



    }


    // Jacoco plugin with Jenkins

    
    
  post {
          always {
             // Publish JaCoCo coverage report as artifact
                         archiveArtifacts(artifacts: 'target/site/jacoco/index.html', onlyIfSuccessful: true)
          }
          success {
              // Actions à effectuer si le pipeline réussit
              echo 'Pipeline réussi!'
              emailext(
                  to: 'mandoupam@gmail.com', // Remplacez par l'adresse e-mail destinataire
                  subject: 'Notification de build Jenkins - Succès',
                  body: 'Le build Jenkins a réussi.'
              )
          }
          failure {
              // Actions à effectuer si le pipeline échoue
              echo 'Pipeline échoué!'
              emailext(
                  to: 'mandoupam@gmail.com', // Remplacez par l'adresse e-mail destinataire
                  subject: 'Notification de build Jenkins - Échec',
                  body: 'Le build Jenkins a échoué.'
              )
          }
      }
  }
