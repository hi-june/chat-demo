# SWU 채팅 기능을 구현하기 위한 데모 프로젝트입니다.
SWU: Share With Us(new version of 혼자왔니)

## Tech Stacks
`spring boot`, `web socket`, `redis(cache)`, `kafka(pub/sub)`

## How to Run on Local  
준비 사항: `redis`, `kafka`가 설치 되어 있어야 합니다.  
1. Build jar  
   총 2번 빌드해줘야합니다.  
   build.gradle과 application-local.yml 파일을 각각 수정하여 server_1용, server_2용 빌드 파일을 각각 만들어줍니다.
   1) build.gradle 파일
      ```groovy
      bootJar {   // archiveFileName은 생성될 build 파일의 이름입니다.  
      archiveFileName = "chatdemo-foo.jar"	// server 1
      // archiveFileName = "chatdemo-boo.jar"	// server 2
      }
      ```
   2) application-local.yml
      ```yml
      spring:
         kafka:
            bootstrap-servers: localhost:9092
            consumer:
               group-id: foo # server 1
               # group-id: boo # server 2
            template:
               default-topic: chatroom
         redis:
            host: localhost
            port: 6379
      ```
   ```shell
    ./gradlew bootJar
    ```
2. Run Redis Server
    ```shell
    redis-server
    ```
3. Run Kafka Server
   ```shell
   # kafka가 설치된 경로에서 수행해주세요.
   
   # zookeeper 실행
   ./bin/zookeeper-server-start.sh ./config/zookeeper.properties
   
   # kafka 실행
   ./bin/kafka-server-start.sh ./config/server.properties
   ```
4. Run Chat Servers
    ```shell
   # build file path: ~/build/libs
    
   # server 1
    java -jar -Dserver.port={port_num_1} chatdemo-foo.jar
    
    # server 2
    java -jar -Dserver.port={port_num_2} chatdemo-boo.jar
    ```
4. Web Browser
    ```
   # server 1
   localhost:{port_num_1}/chat/room
   
   # server 2
   localhost:{port_num_2}/chat/room
   ```

## Images
1. 채팅방 개설  
<img src="https://user-images.githubusercontent.com/98803599/235394414-2bc44f2c-8a53-4ea7-85b9-6d586e565553.png">  
2. 채팅  
<img src="https://github.com/hi-june/chat-demo/assets/98803599/2eccf576-ff99-40d5-9ed3-bb3914a2cc02">  
