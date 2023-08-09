import GamePage from "@feature/Game/routes/GamePage";
import RoomsPage from "@feature/rooms/components/RoomsPage";
import { createBrowserRouter } from "react-router-dom";

const router = createBrowserRouter([
  { path: "/lobby", element: <RoomsPage /> },
  { path: "/game/:sessionId", element: <GamePage /> },
]);

export default router;
