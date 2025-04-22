import socket
import struct


def udp_client():
    server_address_port = ("192.168.56.1", 10000)
    buffer_size = 1024

    # create client side socket
    udp_client_socker = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)

    for i in range(100):
        msg_from_client = str(i)
        bytes_to_send = str.encode(msg_from_client)

        # send message to server
        udp_client_socker.sendto(bytes_to_send, server_address_port)

        msg_from_server = udp_client_socker.recvfrom(buffer_size)
        mgs = f"message from server: {msg_from_server[0]}"
        print("message: ", mgs)

def main():
    udp_client()

if __name__ == '__main__':
    main()