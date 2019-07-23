import hudson.EnvVars

def app


properties([
  parameters([
    booleanParam(defaultValue: false,
      description: 'Click this if you would like to deploy to latest',
      name: 'PUSH_LATEST'
      )])])


node {

  // Pooling the docker image
  checkout scm

  stage('Build docker image') {

      // Build the docker image
      app = docker.build("florinen/jenkins", "-f ${WORKSPACE}/jenkins-lts/Dockerfile .")
  }
  stage('Copy scripts') {
    // Copy scripts to image
      sh 'cp /jenkins-lts/scripts /usr/share/jenkins/ref/init.groovy.d/'
      }  

  stage('Push image') {

     // Push docker image to the Docker hub
      docker.withRegistry('', 'dockerhub-cred') {
          app.push("0.${BUILD_NUMBER}")
          // If push to latest parameters selected
          if (params.PUSH_LATEST){
            app.push("latest")
          }
      }
  }
}
