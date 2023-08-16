import GamePage from "@feature/Game/routes/GamePage";
import LoginFormPage from "@feature/login/components/LoginFormPage";
import RoomsPage from "@feature/rooms/components/RoomsPage";
import RegistPage from "@feature/regist/component/RegistPage";
import { createBrowserRouter } from "react-router-dom";
import MyPage from "@feature/mypage/component/MyPage";
import Redirect from "@feature/login/routes/Redirect";
import WebsocketProvider from "context/WebsocketProvider";
import Tutorial from "@feature/Game/routes/TutorialPage";

export const publicRouter = createBrowserRouter([
  {
    path: "/regist",
    element: <RegistPage />,
  },
  { path: "*", element: <LoginFormPage /> },
]);

export const privateRouter = createBrowserRouter([
  { path: "/game/:sessionId", element: <GamePage /> },
  { path: "/mypage", element: <MyPage /> },
  { path: "/redirect", element: <Redirect /> },
  {
    path: "/lobby",
    element: (
      <WebsocketProvider>
        <RoomsPage />{" "}
      </WebsocketProvider>
    ),
  },
  {
    path: "/tutorial",
    element: <Tutorial />,
  },
  {
    path: "/myinfo",
    element: <MyPage />,
  },
]);
