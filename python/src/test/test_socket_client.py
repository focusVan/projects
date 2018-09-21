import socket

socket_instance = socket.socket()
server = socket.gethostname()
port = 1234
socket_instance.connect((server, port))
print(socket_instance.recv(1024))
socket_instance.close()