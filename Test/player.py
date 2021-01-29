
# WS client Game Sumilation
# asserst X=""

import asyncio
import websockets
import random
import json
import threading
import time
async def hello():
	uri = "ws://pacific-plateau.herokuapp.com/game-endpoint"
	async with websockets.connect(uri) as websocket:
		m = {"idLevel":"20","option":"startGame"}


		await websocket.send(json.dumps(m))


		rs = await websocket.recv()  #  Waiting Start Game
		print(">>",rs)

		

		for i in range(20):
			rs = await websocket.recv()
			print(f"< {rs}")




asyncio.get_event_loop().run_until_complete(hello())