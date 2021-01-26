# heroku-tomcat-websocket
An example to show how to develop tomcat-based WebSocket application on Heroku


Run locally
To build your application simply run:

mvn package
Run the worker with:

sh target/bin/worker
Worker process woke up
Worker process woke up
Worker process woke up
...
(use target\bin\worker.bat on Windows). Run the one-off process with:

sh target/bin/oneoff
OneOffProcess executed.
That’s it. You are now ready to deploy to Heroku.