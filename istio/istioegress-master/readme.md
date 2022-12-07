https://istio.io/docs/tasks/traffic-management/egress.html

```
eval $(minishift oc-env)

eval $(minishift docker-env)

oc login $(minishift ip):8443 -u admin -p admin

oc new-project istioegress

oc adm policy add-scc-to-user privileged -z default -n istioegress
```

## Create HTTPBin Java App
```
cd egresshttpbin/

mvn spring-boot:run

curl locahost:8080

ctrl-c

mvn clean package

docker build -t example/egresshttpbin:v1 .

docker images | grep egress

docker run -it -p 8080:8080 --rm example/egresshttpbin:v1

curl $(minishift ip):8080

ctrl-c

docker ps | grep egress

docker ps -a | grep egress

oc apply -f <(istioctl kube-inject -f src/main/kubernetes/Deployment.yml) -n istioegress

oc create -f src/main/kubernetes/Service.yml

oc expose service egresshttpbin

curl egresshttpbin-istioegress.$(minishift ip).nip.io

```

Note: It does not work...yet

```
cd ..
```

## Create the Github Java App
```
cd egressgithub/

mvn clean package

docker build -t example/egressgithub:v1 .

docker images | grep egress

docker run -it -p 8080:8080 --rm example/egressgithub:v1

curl $(minishift ip):8080
```

Note: it will not work now but it will once Istio-ized

```
ctrl-c

docker ps | grep egress

oc apply -f <(istioctl kube-inject -f src/main/kubernetes/Deployment.yml) -n istioegress

oc create -f src/main/kubernetes/Service.yml

oc expose service egressgithub

curl egressgithub-istioegress.$(minishift ip).nip.io

cd ..
```

## Istio-ize them
```
istioctl create -f istiofiles/egress_httpbin.yml

istioctl get egressrules

curl egresshttpbin-istioegress.$(minishift ip).nip.io
```
or
```
oc get pods

oc exec -it egresshttpbin-v1-1125123520-4599t /bin/bash

curl localhost:8080

curl httpbin.org/user-agent

curl httpbin.org/headers

exit 
```
add a egressrule for google
```
cat <<EOF | istioctl create -f -
apiVersion: config.istio.io/v1alpha2
kind: EgressRule
metadata:
  name: google-egress-rule
spec:
  destination:
    service: www.google.com
  ports:
    - port: 443
      protocol: https
EOF
```
```
oc exec -it egresshttpbin-v1-1125123520-4599t /bin/bash

curl http://www.google.com:443

exit
```

```
istioctl create -f istiofiles/egress_github.yml

curl egressgithub-istioegress.$(minishift ip).nip.io
```