# redis service with kubernetes





$  ls
 Directory: C:\sezo\redis-kubernetes-service

Mode                 LastWriteTime         Length Name
----                 -------------         ------ ----
-a----        20.06.2021     10:42            706 redis-pod.yaml
-a----        19.06.2021     18:51            176 redis-service.yml
-a----        19.06.2021     18:50            156 sezayir-redis-config.yml

#Create configmap
$  kubectl apply -f .\sezayir-redis-config.yml
configmap/sezayir-redis-config created

#Create pod
$  kubectl apply -f  .\redis-pod.yaml
pod/redis created

#Create service
$  kubectl apply -f  .\redis-service.yml
service/redis-service created

$  kubectl get all
NAME        READY   STATUS              RESTARTS   AGE
pod/redis   0/1     ContainerCreating   0          19s

NAME                    TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)          AGE
service/kubernetes      ClusterIP   10.96.0.1      <none>        443/TCP          2m47s
service/redis-service   NodePort    10.96.35.245   <none>        6379:31248/TCP   8s

#EXTERNAL ACCESS
$  telnet 127.0.0.1 31248

#Acces from  redis-cli

$  kubectl get pods
NAME    READY   STATUS    RESTARTS   AGE
redis   1/1     Running   0          71m$  kubectl exec -it redis -- redis-cli
127.0.0.1:6379> set name sezayir
OK
127.0.0.1:6379> set surname dagtekin
OK
127.0.0.1:6379> keys *
1) "surname"
2) "name"
127.0.0.1:6379> get name
"sezayir"
127.0.0.1:6379>

#Open redis conf with nano

PS C:\sezo\redis-kubernetes-service> kubectl exec -it redis  -- bash
root@redis:/data# cd ..
root@redis:/# ls
bin  boot  data  dev  etc  home  lib  lib64  media  mnt  opt  proc  redis-master  redis-master-data  root  run  sbin  srv  sys  tmp  usr  var
root@redis:/# cd redis-master
root@redis:/redis-master# ls
redis.conf

root@redis:/redis-master# apt update
 
root@redis:/redis-master# apt install nano

root@redis:/redis-master# nano redis.conf

redis.conf
GNU nano 3.2                 
                                                                                                           
maxmemory 2mb
maxmemory-policy allkeys-lru

