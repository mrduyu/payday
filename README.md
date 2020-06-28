# Spring Boot - Kafka - MongoDB - Microservice - Docker - Mail Service
Hi dear friend,
I developed a sample solution. This solution includes MongoDB as database, Kafka as stream platform, Docker for containerize and Mail service to notify user.
I hope it will be useful for you.

## Steps
  - Clone/download my project to your local folder.
  - Open terminal and go to your local folder where project has been downloaded.
  - Write docker-compose up --build -d and hit Enter.
    ```sh
    $ docker-compose up --build -d
    ```
  - You will wait a little to finish process.
  - When everything is done you should see an image below. ![github-small](https://user-images.githubusercontent.com/40163745/85951006-ee103b80-b968-11ea-9338-1c649dbbebbf.png)
  - When you want to see container list. Write docker ps and hit Enter.
      ```sh
    $ docker ps
    ```
    ![github-small](https://user-images.githubusercontent.com/40163745/85951088-91615080-b969-11ea-8b9c-62c7220befa9.png)
   - When you want to see image list. Write docker images and hit Enter.
   ![github-small](https://user-images.githubusercontent.com/40163745/85951089-96260480-b969-11ea-9b74-d9fe789c8f93.png)
    *Please ignore other images. Those are my test images*
   - If you want to stop all containers and remove all containers and images. Write docker-compose down --rmi all and hit Enter.
   ![github-small](https://user-images.githubusercontent.com/40163745/85951173-059bf400-b96a-11ea-9e21-1705f3cf7798.png)

#### If your containers are standing and everything is completed, you can test rest endpoint via Postman.
   - [Here](https://www.postman.com/collections/f3a8f8ca2aafafa33f4e) you can find my sample GET/POST requests to test rest endpoints.

## Rest Endpoint List
Here you can use endpoints to test rest services.

| Service | Endpoint | Port
| ------ | ------ | ------
| Loan-Service | **loanservice** | 8100
| Kyc-Information-Service | **kycinformationservice** | 8101
| Income-Information-Service | **incomeinformationservice** | 8102
| Risk-Analysis-Service | **kycinformationservice** | 8103
| Mail-Service | **mailservice** | 8104
| Kafka-Service | **kafkaservice** | 8105

Best Regards,
**Emre**
