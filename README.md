# build instructions

    export DOCKER_HOST="unix:/run/user/$(id -u)/podman/podman.sock"
    podman system service --time=0 unix:/run/user/$(id -u)/podman/podman.sock &
    
    oc login --token=<token> --server=<server_url>
    
    mvn -e -Popenshift -DskipTests -Dcom.redhat.xpaas.repo.redhatga -Dfabric8.skip=true --batch-mode -Djava.net.preferIPv4Stack=true clean package
    mvn oc:build
    mvn oc:resource
    mvn oc:apply