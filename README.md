**Steps to run:**

1. Download Consul Agent and unzip it in a folder.
2. Download Consul UI and copy contents to AGENT_PATH/web-ui
3. Pull spring-cloud-consul-demo from: https://github.com/seropian/spring-cloud-consul-demo.git
4. Run mvn clean package
5. Run agent: consul agent from its folder:

```
consul agent -server -bootstrap-expect 1 -data-dir ./tmp -ui-dir=./web-ui/dist
```

6. Go to spring-cloud-consul-demo folder
7. Start 2 instances of service:

```
java -jar service/target/service-1.0.0.BUILD-SNAPSHOT.jar --server.port=8081
java -jar service/target/service-1.0.0.BUILD-SNAPSHOT.jar --server.port=8082
```

8. Start client instance:

```
java -jar client/target/client-1.0.0.BUILD-SNAPSHOT.jar --server.port=8080
```

**Done!**

**Next steps**

To see services in consul interface, go to: ```http://localhost:8500```

To see how client app works with services:
- list all services registered with consul (including non-java apps): 
```localhost:8080/services ```
- list all service instances by service name: ```localhost:8080/services```
- let the load balancer to select a service instance: ``` localhost:8080/loadBalancedServiceInstance```
on refreshing the page you will see how returned instance is different between calls
- call a service by using the java interface: ```localhost:8080/helloClient```
- helloClient it's load balanced by default so on page refresh you shall see response coming from different instances