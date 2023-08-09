import React, { Fragment, useCallback, useEffect } from "react";
import { Unity, useUnityContext } from "react-unity-webgl";

export default function Game({ roomName }) {
  const startFaceDetection = useCallback(() => {
    console.log("start face detection");
  }, []);

  const setUserDropOut = useCallback(() => {
    console.log("user out");
  }, []);

  const setGameFinish = useCallback(() => {
    console.log("game over");
  }, []);

  const { unityProvider, sendMessage, addEventListener, removeEventListener } =
    useUnityContext({
      loaderUrl: "/Build/poker_face.loader.js",
      dataUrl: "/Build/poker_face.data",
      frameworkUrl: "/Build/poker_face.framework.js",
      codeUrl: "/Build/poker_face.wasm",
    });

  const handleRoundStart = () => {
    sendMessage("GameManager", "FinishFaceDetection");
  };

  useEffect(() => {
    // TODO 이국신: 사용자 정보 넣어주어야함
    const handleUserIdSetting = () => {
      sendMessage("NetworkManager", "ReceiveUnityUserId", "test");
    };

    const handleRoomNameSetting = () => {
      sendMessage("NetworkManager", "ReceiveUnityRoomName", roomName);
    };

    addEventListener("TakeUserIdFromReact", handleUserIdSetting);
    addEventListener("TakeRoomNameFromReact", handleRoomNameSetting);
    addEventListener("UserDropOutFromReact", setUserDropOut);
    addEventListener("GameOverFromReact", setGameFinish);
    addEventListener("StartFaceAPIFromReact", startFaceDetection);

    return () => {
      removeEventListener("TakeUserIdFromReact", handleUserIdSetting);
      removeEventListener("TakeRoomNameFromReact", handleRoomNameSetting);
      removeEventListener("UserDropOutFromReact", setUserDropOut);
      removeEventListener("GameOverFromReact", setGameFinish);
      removeEventListener("StartFaceAPIFromReact", startFaceDetection);
    };
  }, [
    sendMessage,
    addEventListener,
    removeEventListener,
    setUserDropOut,
    setGameFinish,
    startFaceDetection,
    roomName,
  ]);

  return (
    <Fragment>
      <Unity unityProvider={unityProvider} style={{ width: "100%" }}></Unity>
    </Fragment>
  );
}
