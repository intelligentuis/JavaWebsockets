
# WS client Game Sumilation

import asyncio
import websockets
import random

async def hello():
	uri = "ws://pacific-plateau.herokuapp.com/game-endpoint"
	async with websockets.connect(uri) as websocket:

		await websocket.send("m")

		rs = await websocket.recv() 
		print(f"< {rs}")

		
asyncio.get_event_loop().run_until_complete(hello())