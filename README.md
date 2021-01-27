# heroku-tomcat-websocket
An example to show how to develop tomcat-based WebSocket application on Heroku


# SQL
	
	Queue Table : idLevel(key),idPlayer
	Game Table : idGame(key),x1,y1,x2,y2

# SQL TUTO
https://devcenter.heroku.com/articles/heroku-postgresql#local-setup



create table Players (
  idPlayer varchar(32),
  idLevel varchar(32),
  idGame varchar(32),
  xy varchar(32) default '0-0',

);




# Add Player

INSERT INTO Players (idPlayer,idLevel) VALUES('p55','l10');


SELECT * FROM Players;

