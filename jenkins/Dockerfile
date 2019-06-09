FROM centos:7

MAINTAINER Florin Nenciu <nenfl@yahoo.com>

ENV datadir /var/data
ENV appname jenkins
ENV appversion 1.617
ENV portoffset 80
ENV JAVA_OPTIONS -Xmx1024m
ENV JENKINS_OPTIONS --httpPort=80${portoffset}
ENV JENKINS_HOME ${datadir}/${appname}
ENV DOCKERVERSION=18.03.1-ce


## create data directory
RUN mkdir -p ${datadir}

## create user
RUN adduser -u 10${portoffset} -U ${appname} -b ${datadir}

## setting up locale
RUN echo "LC_ALL=en_US.UTF-8" >> ${JENKINS_HOME}/.bashrc

## install basic tools
RUN yum install --quiet git subversion deltarpm vim wget curl tar -y && yum clean all -y

## get java
RUN wget --quiet --continue --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.tar.gz -O /opt/jdk18.tar.gz
RUN cd /opt && tar xfz jdk18.tar.gz && ln -s /opt/jdk1.8* /opt/jdk18 && rm -rf /opt/*.tar.gz

## add java opts to .bashrc file
RUN echo "export JAVA_OPTIONS=${JAVA_OPTIONS}" >> ${JENKINS_HOME}/.bashrc

## create custom run.sh
RUN wget --quiet http://mirrors.jenkins-ci.org/war/latest/jenkins.war -O /opt/jenkins.war && chown ${appname}:${appname} /opt/jenkins.war

## create custom run.sh
RUN echo "/opt/jdk18/bin/java ${JAVA_OPTS} -jar /opt/jenkins.war ${JENKINS_OPTIONS}" >> /opt/run.sh

## Download and install Ansible

RUN curl -O https://bootstrap.pypa.io/get-pip.py  && \
    python get-pip.py && \
    pip install ansible --upgrade

## Download docker cli
RUN curl -fsSLO https://download.docker.com/linux/static/stable/x86_64/docker-${DOCKERVERSION}.tgz \
  && tar xzvf docker-${DOCKERVERSION}.tgz --strip 1 -C /usr/local/bin docker/docker \
  && rm docker-${DOCKERVERSION}.tgz

## Install kubectl
RUN curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.13.0/bin/linux/amd64/kubectl \
    && chmod +x ./kubectl \
    && mv ./kubectl /usr/local/bin/kubectl \
    && mkdir ~/.kube

## Copy Script to set ~/.kube/config
COPY . /scripts/

## Volume for Jenkins
VOLUME ${JENKINS_HOME}

## start jenkins on container start
CMD /scripts/set-config.sh 
CMD su - root -c "sh /opt/run.sh"
