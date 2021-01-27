
# WS client Game Sumilation

import asyncio
import websockets
import random

async def hello():
	uri = "ws://pacific-plateau.herokuapp.com/game-endpoint"
	async with websockets.connect(uri) as websocket:
		m = "idPlayer=%s,idLevel=%s,message=startGame"%(input("idPlayer:"),input("idLevel:"))

		await websocket.send(m)

		rs = await websocket.recv()  # idGame=####
		print(f"< {rs}")

		p=random.randint(1,100)

		for i in range(100):
			x,y = p+random(),p+random()

			m = "xy=%f-%f,message=update"(x,y)
			await websocket.send(m)
			rs = await websocket.recv()  # x,y
			print(i,">>",rs)
asyncio.get_event_loop().run_until_complete(hello())