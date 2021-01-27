
# WS client Game Sumilation

import asyncio
import websockets
import random
import json
import time
async def hello():
	uri = "ws://pacific-plateau.herokuapp.com/game-endpoint"
	async with websockets.connect(uri) as websocket:
		m = {"idLevel":input("idLevel:"),"option":"startGame","ncoins":"0"}


		await websocket.send(json.dumps(m))

		rs = await websocket.recv()  # idGame=####
		print(f"< {rs}")

		x,y = random.randint(1,1999),random.randint(1,1999)
		m= {"x":str(x),"y":str(y),"option":"update"}
		await websocket.send(json.dumps(m))
		rs = await websocket.recv()  # x,y
		print(">>",rs)

		rs = await websocket.recv() # get id coint 
		print(rs)

		rs = await websocket.recv() # get id coint 
		print(rs)

asyncio.get_event_loop().run_until_complete(hello())