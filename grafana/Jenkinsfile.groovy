import hudson.EnvVars

def custom_image
def repo = 'florinen/grafana'
def Dockerfile = 'grafana/Dockerfile'

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
      custom_image = docker.build("${repo}", "-f ${WORKSPACE}/grafana/Dockerfile .")
  }
  

  stage('Push image') {

     // Push docker image to the Docker hub
      docker.withRegistry('', 'dockerhub-cred') {
          custom_image.push("0.${BUILD_NUMBER}")
          // If push to latest parameters selected
          if (params.PUSH_LATEST){
            custom_image.push("latest")
          }
      }
  }
}
