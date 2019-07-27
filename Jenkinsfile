import hudson.EnvVars

master
def custom_image
def repo = 'florinen/grafana'
def Dockerfile = 'grafana/Dokerfile'

node {

  // Pooling the docker image
  checkout scm

  stage('Build docker image') {

      // Build the docker image
      custom_image = docker.build("${repo}", "-f ${WORKSPACE}/${Dockerfile} .")
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
