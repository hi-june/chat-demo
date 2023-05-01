# SWU 채팅 기능을 구현하기 위한 데모 프로젝트입니다.
SWU: Share With Us(new version of 혼자왔니)

## Tech Stacks
`spring boot`, `web socket`, `redis(cache)`, `redis(pub/sub)`

## How to Run on Local
1. Build jar
   ```shell
    ./gradlew bootJar
    ```
2. Run Redis Server
    ```shell
    redis-server
    ```
3. Run Chat Servers
    ```shell
   # build file path: ~/build/libs
    
   # server 1
    java -jar -Dserver.port={port_num_1} {jar_file_name}
    
    # server 2
    java -jar -Dserver.port={port_num_2} {jar_file_name}
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
<img src="https://user-images.githubusercontent.com/98803599/235394540-d07e2fcb-76c0-4e23-8303-03c7a0e728f9.png">