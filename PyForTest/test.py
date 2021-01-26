
# WS client example

import asyncio
import websockets

async def hello():
    uri = "ws://pacific-plateau.herokuapp.com/message-endpoint"
    async with websockets.connect(uri) as websocket:
        m = "GET"

        await websocket.send(m)
        print(f"> {m}")

        greeting = await websocket.recv()
        print(f"< {greeting}")

asyncio.get_event_loop().run_until_complete(hello())