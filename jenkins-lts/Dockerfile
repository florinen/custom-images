FROM jenkins/jenkins:2.176.2
LABEL Maintainer="Florin" \
      Version="1.2"       
ENV JAVA_OPTS "-Djenkins.install.runSetupWizard=false"
ENV DOCKERVERSION=18.09.8

## Run the following task as root user
USER root
## Install Terraform ##
RUN apt-get update && apt-get install -y --no-install-recommends \
    unzip wget \
    && rm -rf /var/lib/apt/lists/* \
    && wget -O /tmp/terraform.zip https://releases.hashicorp.com/terraform/0.12.3/terraform_0.12.3_linux_amd64.zip \
    && unzip /tmp/terraform.zip -d /bin
## Install Docker ##
RUN curl -fsSLO https://download.docker.com/linux/static/stable/x86_64/docker-${DOCKERVERSION}.tgz \
  && tar xzvf docker-${DOCKERVERSION}.tgz --strip 1 -C /usr/local/bin docker/docker \
  && rm docker-${DOCKERVERSION}.tgz

## Run other tasks as non root user
USER jenkins

## For jenkins pipeline build please use full path of dir you are copying
COPY /jenkins-lts/scripts/ /usr/share/jenkins/ref/init.groovy.d/
COPY /jenkins-lts/plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/plugins.txt