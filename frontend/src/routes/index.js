import GamePage from "@feature/Game/routes/GamePage";
import LoginFormPage from "@feature/login/components/LoginFormPage";
import RoomsPage from "@feature/rooms/components/RoomsPage";
import { createBrowserRouter } from "react-router-dom";

const router = createBrowserRouter([
  { path: "/", element: <LoginFormPage /> },
  { path: "/lobby", element: <RoomsPage /> },
  { path: "/game/:sessionId", element: <GamePage /> },
]);

export default router;
