import socket

socket_instance = socket.socket()
host = socket.gethostname()
port = 1234
socket_instance.bind((host, port))
socket_instance.listen(10)

while True:
    connection, addr = socket_instance.accept()
    print("get connection from ", addr)
    connection.close()
