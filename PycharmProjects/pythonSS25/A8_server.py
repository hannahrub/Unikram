import socket
from http.cookiejar import user_domain_match


def udp_server():
    nachrichten = []
    local_ip = "192.168.56.1"
    local_port = 10000
    buffer_size = 1024

    # create datagram socket
    udp_server_socket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)
    # bind socket to address and port
    udp_server_socket.bind((local_ip, local_port))

    print("server funktionsbereit")

    # warte auf datagramme
    while True:
        bytes_address_pair = udp_server_socket.recvfrom(buffer_size)
        message = bytes_address_pair[0]
        address = bytes_address_pair[1]

        client_msg = f"Message from client: {message.decode()}"
        client_ip = f"client ip address: {address}"

        print(client_msg, client_ip)
        nachrichten.append(message.decode())
        print(nachrichten)

        # sending reply
        msg_from_server = f"hello from server, your message was: {message.decode()} all messages: {nachrichten}"
        print(msg_from_server)
        bytes_to_send = str.encode(msg_from_server)
        udp_server_socket.sendto(bytes_to_send, address)

def main():
    udp_server()

if __name__ == '__main__':
    main()