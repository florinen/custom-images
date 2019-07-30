FROM grafana/grafana:6.2.4

ARG GF_UID="472"
ARG GF_GID="472"

ENV PATH=/usr/share/grafana/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin \
    GF_PATHS_CONFIG="/etc/grafana/grafana.ini" \
    GF_PATHS_DATA="/var/lib/grafana" \
    GF_PATHS_HOME="/usr/share/grafana" \
    GF_PATHS_LOGS="/var/log/grafana" \
    GF_PATHS_PLUGINS="/var/lib/grafana/plugins" \
    GF_PATHS_PROVISIONING="/etc/grafana/provisioning"


RUN apt-get update && apt-get install -qq -y tar libfontconfig curl ca-certificates && \
    grafana-cli plugins install grafana-kubernetes-app && \
    apt-get autoremove -y && \
    groupadd -r -g $GF_GID grafana && \
    useradd -r -u $GF_UID -g grafana grafana && \
    mkdir -p "$GF_PATHS_PROVISIONING/datasources" \
             "$GF_PATHS_PROVISIONING/dashboards" \
             "$GF_PATHS_LOGS" \
             "$GF_PATHS_PLUGINS" \
             "$GF_PATHS_DATA" && \
    cp "$GF_PATHS_HOME/conf/sample.ini" "$GF_PATHS_CONFIG" && \
    chown -R grafana:grafana "$GF_PATHS_DATA" "$GF_PATHS_HOME" "$GF_PATHS_LOGS" "$GF_PATHS_PLUGINS" && \
    chmod 777 "$GF_PATHS_DATA" "$GF_PATHS_HOME" "$GF_PATHS_LOGS" "$GF_PATHS_PLUGINS"

EXPOSE 3000

#COPY ./run.sh /run.sh

USER grafana
WORKDIR /
ENTRYPOINT [ "/run.sh" ]









