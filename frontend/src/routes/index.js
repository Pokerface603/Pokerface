import GamePage from "@feature/Game/routes/GamePage";
import LobbyPage from "@feature/RoomTest/components/LobbyPage";
import { createBrowserRouter } from "react-router-dom";

const router = createBrowserRouter([
  { path: "/", element: <LobbyPage /> },
  { path: "/game/:sessionId", element: <GamePage /> },
]);

export default router;
