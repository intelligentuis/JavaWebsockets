
# WS client example

import asyncio
import websockets

async def hello():
	uri = "ws://pacific-plateau.herokuapp.com/game-endpoint"
	async with websockets.connect(uri) as websocket:
		m = "idPlayer=%s,idLevel=58zd5,message=startGame"%(input("idPlayer:"),input("idLevel:"))

		await websocket.send(m)

		rs = await websocket.recv()  # idGame=####
		print(f"< {rs}")

		

asyncio.get_event_loop().run_until_complete(hello())