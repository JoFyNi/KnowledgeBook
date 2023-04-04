import http.server
import socketserver

# Benutzername und Passwort
username = "admin"
password = "password"

# Handler-Klasse, die das Anfordern von Benutzernamen und Passwort erfordert
class AuthHandler(http.server.SimpleHTTPRequestHandler):
    def do_HEAD(self):
        self.send_response(401)
        self.send_header('WWW-Authenticate', 'Basic realm=\"Test\"')
        self.send_header('Content-type', 'text/html')
        self.end_headers()

    def do_AUTHHEAD(self):
        self.send_response(401)
        self.send_header('WWW-Authenticate', 'Basic realm=\"Test\"')
        self.send_header('Content-type', 'text/html')
        self.end_headers()

    def do_GET(self):
        if self.headers.get('Authorization') == None:
            self.do_AUTHHEAD()
            self.wfile.write(b'no auth header received')
        elif self.headers.get('Authorization') == 'Basic ' + (username + ':' + password).encode('base64').strip():
            http.server.SimpleHTTPRequestHandler.do_GET(self)
        else:
            self.do_AUTHHEAD()
            self.wfile.write(b'not authenticated')

# Port, auf dem der Webserver läuft
PORT = 8880

# Webserver starten
with socketserver.TCPServer(("", PORT), AuthHandler) as httpd:
    print("Server started at localhost:" + str(PORT))

    # Admin-Konto erstellen
    with open("admin_credentials.txt", "w") as f:
        f.write("Username: " + username + "\n")
        f.write("Password: " + password + "\n")

    # Server laufen lassen
    httpd.serve_forever()
