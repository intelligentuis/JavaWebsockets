
# WS client Game Sumilation

import asyncio
import websockets
import random
import json
async def hello():
	uri = "ws://pacific-plateau.herokuapp.com/game-endpoint"
	async with websockets.connect(uri) as websocket:
		m = {"idPlayer":input("idPlayer:"),"idLevel":input("idLevel:"),"message":"startGame"}


		await websocket.send(json.dumps(m))

		rs = await websocket.recv()  # idGame=####
		print(f"< {rs}")

		p=random.randint(1,100)

		for i in range(100):
			x,y = p+random.random(),p+random.random()

			m= {"x":x,"y":y,"message":"update"}
			await websocket.send(m)
			rs = await websocket.recv()  # x,y
			print(i,">>",rs)
asyncio.get_event_loop().run_until_complete(hello())