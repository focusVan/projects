from socketserver import TCPServer,StreamRequestHandler,ForkingMixIn
import socket

class Server(ForkingMixIn, TCPServer):
    pass

class MyHandler(StreamRequestHandler):
    def handle(self):
        addr = self.request.getpeername()
        print("Get connection form ", addr)
        self.wfile('This is a tcp socket server')

host = socket.gethostname()
port = 1234
server = Server((host, port), MyHandler)
server.server_forever()