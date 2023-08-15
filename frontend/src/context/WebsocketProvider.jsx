import React, { useRef } from "react";

export const WebSocketContext = React.createContext(null);

function WebsocketProvider({ children }) {
  const webSocketUrl = process.env.REACT_APP_WEB_SOCKET_URL;
  let ws = useRef(null);

  if (!ws.current) {
    ws.current = new WebSocket(webSocketUrl);
    ws.current.onopen = () => {
      console.log("connected to " + webSocketUrl);
    };

    ws.current.onclose = (error) => {
      console.log("disconnect from " + webSocketUrl);
      console.log(error);
    };

    ws.current.onerror = (error) => {
      console.log("connection error " + webSocketUrl);
      console.log(error);
    };
  }
  <WebSocketContext.Provider value={ws}>{children}</WebSocketContext.Provider>;
}

export default WebsocketProvider;
