# heroku-tomcat-websocket
An example to show how to develop tomcat-based WebSocket application on Heroku


# SQL
	
	Queue Table : idLevel(key),idPlayer
	Game Table : idGame(key),x1,y1,x2,y2

# SQL TUTO
https://devcenter.heroku.com/articles/heroku-postgresql#local-setup



create table QueuePlayers (
  idPlayer varchar(32),
  idLevel varchar(32),
  idGame varchar(32) 
);


create table GamePlayers (
  idGame varchar(32),
  X1 varchar(32),
  Y1 varchar(32),
  X2 varchar(32),
  Y2 varchar(32)
);

# Add Player

INSERT INTO QueuePlayers (idPlayer,idLevel) VALUES('4f5f','fppf5');

SELECT * FROM QueuePlayers;

