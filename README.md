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
  idGame varchar(100),
  idSession varchar(100),
  score FLOAT(53) default 0,
  x varchar(32) default '0',
  y varchar(32) default '0'
);




# Test

INSERT INTO Players (idPlayer,idLevel,idSession) VALUES('p1','l1','@1');

INSERT INTO Players (idPlayer,idLevel,idSession,idGame) VALUES('p1','l1','@1','2020');
INSERT INTO Players (idPlayer,idLevel,idSession,idGame) VALUES('p2','l1','@2','2020');


SELECT * FROM Players;



SELECT idSession FROM Players WHERE idGame='2020' and NOT idPlayer = 'p1'