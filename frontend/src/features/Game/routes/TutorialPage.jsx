import React, { Fragment, useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { Unity, useUnityContext } from "react-unity-webgl";

function Tutorial() {
  const { unityProvider, sendMessage, addEventListener, removeEventListener } =
    useUnityContext({
      loaderUrl: "/tutorial/poker_face_tutorial.loader.js",
      dataUrl: "/tutorial/poker_face_tutorial.data",
      frameworkUrl: "/tutorial/poker_face_tutorial.framework.js",
      codeUrl: "/tutorial/poker_face_tutorial.wasm",
    });

  const navigate = useNavigate();

  const { authorization } = useSelector((state) => state.user);

  // 게임이 종료됐고, 필요한 토큰을 전달해주는 함수
  const handleSendToken = () => {
    sendMessage("GameManager", "ReceiveUnityGameInfo", authorization);
    navigate("/lobby");
  };

  useEffect(() => {
    addEventListener("GameOverToReact", handleSendToken);

    return () => {
      removeEventListener("GameOverToReact", handleSendToken);
    };
  }, []);

  return (
    <Fragment>
      <Unity
        unityProvider={unityProvider}
        style={{ width: "100vw", height: "100vh" }}
      ></Unity>
    </Fragment>
  );
}

export default Tutorial;
