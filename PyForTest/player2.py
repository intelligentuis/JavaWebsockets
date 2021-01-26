
# WS client example

import asyncio
import websockets

async def hello():
    uri = "ws://pacific-plateau.herokuapp.com/message-endpoint"
    async with websockets.connect(uri) as websocket:
        m = "idPlayer=25f54,idLevel=58zd5,message=startGame"

        await websocket.send(m)
        print("waiting ...")

        greeting = await websocket.recv()
        print(f"< {greeting}")

asyncio.get_event_loop().run_until_complete(hello())