FROM grafana/grafana:6.2.4

# ARG GF_UID="472"
# ARG GF_GID="472"

#ENV PATH=/usr/share/grafana/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin \
ENV GF_PATHS_PLUGINS="/var/lib/grafana/plugins" 

ARG GF_INSTALL_PLUGINS="grafana-kubernetes-app,grafana-clock-panel,grafana-simple-json-datasource"

RUN if [ ! -z "${GF_INSTALL_PLUGINS}" ]; then \
    OLDIFS=$IFS; \
        IFS=','; \
    for plugin in ${GF_INSTALL_PLUGINS}; do \
        IFS=$OLDIFS; \
        grafana-cli --pluginsDir "$GF_PATHS_PLUGINS" plugins install ${plugin}; \
    done; \
fi

 
EXPOSE 3000

WORKDIR /
ENTRYPOINT [ "/run.sh" ]









