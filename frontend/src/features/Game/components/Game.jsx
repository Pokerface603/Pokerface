import React, { Fragment, useCallback, useEffect } from "react";
import { Unity, useUnityContext } from "react-unity-webgl";

export default function Game({ roomName, gameMode }) {
  const { unityProvider, sendMessage, addEventListener, removeEventListener } =
    useUnityContext({
      loaderUrl: "/Build/poker_face.loader.js",
      dataUrl: "/Build/poker_face.data",
      frameworkUrl: "/Build/poker_face.framework.js",
      codeUrl: "/Build/poker_face.wasm",
    });

  // 유니티에서 보낸 유저를 추방했다는 호출 확인하는 함수
  const setUserDropOut = useCallback(() => {}, []);

  // 유니티에서 보낸 얼굴인식을 시작하라는 호출 확인하는 함수
  const startFaceDetection = useCallback(() => {}, []);

  // 한 라운드를 시작하라는 유니티를 호출하는 함수
  const handleRoundStart = () => {
    sendMessage("GameManager", "FinishFaceDetection");
  };

  useEffect(() => {
    // 방 정보를 담고 있는 객체
    const info = {
      userId: "daeyoung@ssafy.com",
      roomName,
    };

    // 게임을 준비하기 위해 정보를 담고 있는 객체를 보내는 함수
    const handleGameSetting = () => {
      sendMessage(
        "NetworkManager",
        "ReceiveUnityGameInfo",
        JSON.stringify(info)
      );
    };

    // 유니티에 현재 게임 모드가 무엇인지 알려주는 함수
    const setGameModeReact = () => {
      sendMessage("GameManager", "SetGameMode", "NORMAL");
    };

    // 게임이 종료됐고, 필요한 토큰을 전달해주는 함수
    const handleSendToken = () => {
      sendMessage("GameManager", "SetAccessToken", "accessToken");
    };

    // 유니티에서 오는 호출을 확인하기 위한 이벤트들
    addEventListener("TakeGameInfoFromReact", handleGameSetting);
    addEventListener("SetGameModeFromReact", setGameModeReact);
    addEventListener("UserDropOutFromReact", setUserDropOut);
    addEventListener("StartFaceAPIFromReact", startFaceDetection);
    addEventListener("GameOverFromReact", handleSendToken);

    return () => {
      removeEventListener("TakeGameInfoFromReact", handleGameSetting);
      removeEventListener("SetGameModeFromReact", setGameModeReact);
      removeEventListener("UserDropOutFromReact", setUserDropOut);
      removeEventListener("StartFaceAPIFromReact", startFaceDetection);
      removeEventListener("GameOverFromReact", handleSendToken);
    };
  }, [
    sendMessage,
    addEventListener,
    removeEventListener,
    setUserDropOut,
    startFaceDetection,
  ]);

  return (
    <Fragment>
      <Unity unityProvider={unityProvider} style={{ width: "100%" }}></Unity>
    </Fragment>
  );
}
