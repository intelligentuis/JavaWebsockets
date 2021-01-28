
# WS client Game Sumilation
# asserst X=""

import asyncio
import websockets
import random
import json
import time
async def hello():
	uri = "ws://pacific-plateau.herokuapp.com/game-endpoint2"
	async with websockets.connect(uri) as websocket:
		m = {"idLevel":"20","option":"startGame","user":"Abdo"}


		await websocket.send(json.dumps(m))

		rs = await websocket.recv()  # x,y
		print(">>",rs)




		for i in range(100):
			x,y = random.randint(1,1999),random.randint(1,1999)
			m= {"x":str(x),"y":str(y),"option":"update","user":"Abdo"}
			await websocket.send(json.dumps(m))

		# 	rs = await websocket.recv()  # x,y
		# 	print(">>",rs)
		# 	time.sleep(0.5)


asyncio.get_event_loop().run_until_complete(hello())