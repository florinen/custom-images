FROM grafana/grafana:6.2.4

WORKDIR /var/lib/grafana/plugins

RUN grafana-cli plugins install grafana-kubernetes-app

WORKDIR /

ENTRYPOINT [ "/run.sh" ]


