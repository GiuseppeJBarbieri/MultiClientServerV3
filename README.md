# MultiClientServerV3
This is used as a model for the multi-client and server functionality to be used for the ElantechAppV2.

It is setup using an Observables design pattern. The client creates an event and sends the event object to the server. The server then creates a new thread for each new unique client socket. When the client sends an event object to the server it updates all connected clients. The server can also send event objects to all connected clients.
