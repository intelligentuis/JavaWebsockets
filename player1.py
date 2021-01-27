
# WS client Game Sumilation

import asyncio
import websockets
import random
import json
import time
async def hello():
	uri = "ws://pacific-plateau.herokuapp.com/game-endpoint"
	async with websockets.connect(uri) as websocket:
		m = {"idPlayer":input("idPlayer:"),"idLevel":input("idLevel:"),"message":"startGame"}


		await websocket.send(json.dumps(m))

		rs = await websocket.recv()  # idGame=####
		print(f"< {rs}")

		x,y = random.randint(1,1999),random.randint(1,1999)
		m= {"x":x,"y":y,"message":"update"}
		await websocket.send(json.dumps(m))
		rs = await websocket.recv()  # x,y
		print(i,">>",rs)

		m = {"message":"coinEated","idCoin":"15"}
		await websocket.send(json.dumps(m))


asyncio.get_event_loop().run_until_complete(hello())