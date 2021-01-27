
# WS client Game Sumilation


import asyncio
import websockets
import random
import json
import time
async def hello():
	uri = "ws://pacific-plateau.herokuapp.com/game-endpoint"
	async with websockets.connect(uri) as websocket:
		m = {"idPlayer":input("idPlayer:"),"idLevel":input("idLevel:"),"option":"startGame"}


		await websocket.send(json.dumps(m))

		rs = await websocket.recv()  # idGame=####
		print(f"< {rs}")

		x,y = random.randint(1,1999)+0.666,random.randint(1,1999)+0.666
		m= {"x":str(x),"y":str(y),"option":"update"}
		await websocket.send(json.dumps(m))
		rs = await websocket.recv()  # x,y
		print(">>",rs)

		m = {"option":"coinEated","idCoin":"15"}
		await websocket.send(json.dumps(m))


		m = {"option":"End"}
		await websocket.send(json.dumps(m))
asyncio.get_event_loop().run_until_complete(hello())