
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

		

		for i in range(20):
			rs = await websocket.recv()
			print(f"< {rs}")




asyncio.get_event_loop().run_until_complete(hello())