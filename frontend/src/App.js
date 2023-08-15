import "./App.css";
import { RouterProvider } from "react-router-dom";
import { privateRouter, publicRouter } from "@route/index";
import { useSelector } from "react-redux";

function App() {
  const { isLogin } = useSelector((state) => state.user);
  const router = isLogin ? privateRouter : publicRouter;

  return <RouterProvider router={router} />;
}

export default App;
