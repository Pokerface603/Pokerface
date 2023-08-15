import GamePage from "@feature/Game/routes/GamePage";
import LoginFormPage from "@feature/login/components/LoginFormPage";
import RoomsPage from "@feature/rooms/components/RoomsPage";
import RegistPage from "@feature/regist/component/RegistPage";
import { createBrowserRouter } from "react-router-dom";
import MyPage from "@feature/mypage/component/MyPage";
import History from "@feature/history/components/History";
import Redirect from "@feature/login/routes/Redirect";

const router = createBrowserRouter([
  { path: "/", element: <LoginFormPage /> },
  { path: "/lobby", element: <RoomsPage /> },
  { path: "/game/:sessionId", element: <GamePage /> },
  { path: "/regist", element: <RegistPage /> },
  { path: "/mypage", element: <MyPage /> },
  { path: "/history/:historyId", element: <History /> },
  { path: "/redirect", element: <Redirect /> },
]);

export default router;
