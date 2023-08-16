import GamePage from "@feature/Game/routes/GamePage";
import LoginFormPage from "@feature/login/components/LoginFormPage";
import RoomsPage from "@feature/rooms/components/RoomsPage";
import RegistPage from "@feature/regist/component/RegistPage";
import { Navigate, createBrowserRouter } from "react-router-dom";
import LogoPage from "../components/LogoPage";
import MyPage from "@feature/mypage/component/MyPage";
import History from "@feature/history/components/History";
import Redirect from "@feature/login/routes/Redirect";
import WebsocketProvider from "context/WebsocketProvider";
import Tutorial from "@feature/Game/routes/TutorialPage";

export const publicRouter = createBrowserRouter([
  {
    path: "/regist",
    element: <RegistPage />,
  },
  { path: "/", element: <LoginFormPage /> },
  { path: "/redirect", element: <Redirect /> },
  { path: "*", element: <Navigate to="/" /> },
]);

export const privateRouter = createBrowserRouter([
  { path: "/mypage", element: <MyPage /> },
  { path: "/history/:historyId", element: <History /> },
  { path: "/game/:sessionId", element: <GamePage /> },
  {
    path: "/lobby",
    element: (
      <WebsocketProvider>
        <RoomsPage />
      </WebsocketProvider>
    ),
  },
  {
    path: "/tutorial",
    element: <Tutorial />,
  },
  { path: "*", element: <Navigate to="/lobby" /> },
]);
