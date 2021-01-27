
# WS client Game Sumilation

import asyncio
import websockets
import random
import time
async def hello():
	uri = "ws://pacific-plateau.herokuapp.com/test-endpoint"
	async with websockets.connect(uri) as websocket:

		await websocket.send(input("sessionId"))

		rs = await websocket.recv()
		print(f"< {rs}")
		# time.sleep(1200)
asyncio.get_event_loop().run_until_complete(hello())