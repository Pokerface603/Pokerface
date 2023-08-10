import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { Provider } from "react-redux";
import store from "@store/store.js";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  // react-redux 사용 위한 설정
  <Provider store={store}>
    <App />
  </Provider>
);