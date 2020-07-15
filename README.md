# ReslivInterview 

Для запуска приложения запустим класс PayrollApplication.
При его инициализацци наш телеграм бот и веб-сервисы начинают свою работу.
## Токен: 1056753107:AAG-Sx6ZOBozVoD8LpvW2Y2XkDcjJbd9I6g
## Имя:ReslivInterviewBot
Телеграмм бот запускается сообщением /start  и после чего мы можем использовать его в качестве туристического путеводителя. Так же предусмотрен вариант когда город отсуствует в базе данных, в таком случае будет выведено сообщение - "К сожалению, у нас нет информации для этого города."
Когда город есть в нашей базе данных мы получаем сообщение с рекомендациями:
### -Kiev
### -Bright, beautiful and night city
Таким образом телеграм бот полностью выполняет поставленную задача. 
Далее перейдем к веб части нашего проекта.

Для удобства просмотра сообщений есть домашняя страница.
```
http://localhost:8080/home
```
На ней отображаются города, их описание и айди записи.

Для работы через REST необходимо подготовить терминал и там будет происходить ввод команд.

Для получения информации о всех сообщения пропишем команду
```
curl -v http://localhost:8080/messages
```
Результат:

```
{"_embedded":{"messageList":[{"id":1,"name":"Minsk","description":"Go to the national library and eat potato pancakes","_links":{"self":{"href":"http://localhost:8080/messages/1"},"messeges":{"href":"http://localhost:8080/messages"}}},{"id":4,"name":"Kiev","description":"Bright, beautiful and night city","_links":{"self":{"href":"http://localhost:8080/messages/4"},"messeges":{"href":"http://localhost:8080/messages"}}},{"id":5,"name":"Grodno","description":"The main square is very majestic and large","_links":{"self":{"href":"http://localhost:8080/messages/5"},"messeges":{"href":"http://localhost:8080/messages"}}},{"id":6,"name":"New-York","description":"Statue of Liberty and much more","_links":{"self":{"href":"http://localhost:8080/messages/6"},"messeges":{"href":"http://localhost:8080/messages"}}},{"id":7,"name":"Istanbul","description":"I went there just like that","_links":{"self":{"href":"http://localhost:8080/messages/7"},"messeges":{"href":"http://localhost:8080/messages"}}}]},"_links":{"self":{"href":"http://localhost:8080/messages"}}}
```

Так же для удобства просмотра сообщений есть домашняя страница.
```
http://localhost:8080/home
```

Для удаления какой-либо записи необходимо в терминале прописать команду
```
curl -v -X DELETE http://localhost:8080/messages/{ID Вашего сообщения}
```
Например: 
```
curl -v -X DELETE http://localhost:8080/messages/1
```
Результат:
```
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> DELETE /messages/1 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.55.1
> Accept: */*
>
< HTTP/1.1 200
< Content-Length: 0
< Date: Tue, 14 Jul 2020 18:36:45 GMT
<
* Connection #0 to host localhost left intact
```

После этого мы можем проверить наши сообщения и сообщения с ID = 1 будет удалено.

 
Для получения информации о определенном сообщениии
```
curl -v http://localhost:8080/messages/{ID Вашего сообщения}
```
Например:
```
curl -v http://localhost:8080/messages/1
```
Результат:
```
{"id":1,"name":"Minsk","description":"Go to the national library and eat potato pancakes","_links":{"self":{"href":"http://localhost:8080/messages/1"},"messagess":{"href":"http://localhost:8080/messages"}}}
```

Для измения информации о сообщении введем команду
```
curl -v -X PUT localhost:8080/messages/{ID изменяемого сообщения} -H 'Content-Type:application/json' -d '{"name": "Samwise Gamgee", "role": "ring bearer"}'
```

Для добавления информации введем команду
```
curl -v -X POST localhost:8080/messages -H 'Content-Type:application/json' -d '{"id": "{ID сообщения}", "name": "{Город}", "description": "{Описание города}"}'
```
