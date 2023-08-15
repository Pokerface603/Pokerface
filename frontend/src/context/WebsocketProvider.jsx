import React, { useRef } from "react";
import { useSelector } from "react-redux";

export const WebSocketContext = React.createContext(null);

function WebsocketProvider({ children }) {
  const webSocketUrl = process.env.REACT_APP_WEB_SOCKET_URL;
  let ws = useRef(null);

  const { email } = useSelector((state) => state.user);

  if (!ws.current) {
    ws.current = new WebSocket(webSocketUrl);
    ws.current.onopen = () => {
      ws.current.send(`CONNECT,${email}`);
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
  return (
    <WebSocketContext.Provider value={ws}>{children}</WebSocketContext.Provider>
  );
}

export default WebsocketProvider;
