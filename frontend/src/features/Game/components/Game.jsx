import React, { Fragment, useCallback, useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router";
import { Unity, useUnityContext } from "react-unity-webgl";

export default function Game({ roomName, gameMode, leaveRoom }) {
  const { unityProvider, sendMessage, addEventListener, removeEventListener } =
    useUnityContext({
      loaderUrl: "/Build/poker_face.loader.js",
      dataUrl: "/Build/poker_face.data",
      frameworkUrl: "/Build/poker_face.framework.js",
      codeUrl: "/Build/poker_face.wasm",
    });

  const { email, authorization } = useSelector((state) => state.user);

  const navigate = useNavigate();

  // 유니티에서 보낸 유저를 추방했다는 호출 확인하는 함수
  const setUserDropOut = useCallback(() => {
    leaveRoom();
  }, []);

  // 유니티에서 내가 방에서 나갔다는 호출 확인하는 함수
  const userRoomOut = useCallback(() => {
    leaveRoom();
  }, []);

  // 한 라운드를 시작하라는 유니티를 호출하는 함수
  const handleRoundStart = () => {
    sendMessage("GameManager", "FinishFaceDetection");
  };

  // 유니티에서 보낸 얼굴인식을 시작하라는 호출 확인하는 함수
  const startFaceDetection = useCallback(() => {
    handleRoundStart();
  }, [handleRoundStart]);

  useEffect(() => {
    // 방 정보를 담고 있는 객체
    const info = {
      userId: email,
      roomName,
      gameMode,
    };

    // 게임을 준비하기 위해 정보를 담고 있는 객체를 보내는 함수
    const handleGameSetting = () => {
      sendMessage("GameManager", "ReceiveUnityGameInfo", JSON.stringify(info));
    };

    // 게임이 종료됐고, 필요한 토큰을 전달해주는 함수
    const handleSendToken = () => {
      sendMessage("GameManager", "SetAccessToken", authorization);
    };

    // 유니티에서 백엔드에 성공적으로 로그를 보냈다는 함수
  const getGameLog = () => {
    console.log("get game log");
    navigate("/result");
  }

    // 유니티에서 오는 호출을 확인하기 위한 이벤트들
    addEventListener("TakeGameInfoFromReact", handleGameSetting);
    addEventListener("UserDropOutToReact", setUserDropOut);
    addEventListener("UserRoomOutToReact", userRoomOut);
    addEventListener("StartFaceAPIToReact", startFaceDetection);
    addEventListener("GameOverToReact", handleSendToken);
    addEventListener("SendCompleteToReact", getGameLog);

    return () => {
      removeEventListener("TakeGameInfoFromReact", handleGameSetting);
      removeEventListener("UserDropOutToReact", setUserDropOut);
      removeEventListener("UserRoomOutToReact", userRoomOut);
      removeEventListener("StartFaceAPIToReact", startFaceDetection);
      removeEventListener("GameOverToReact", handleSendToken);
      removeEventListener("SendCompleteToReact", getGameLog);
    };
  }, [
    sendMessage,
    addEventListener,
    removeEventListener,
    setUserDropOut,
    startFaceDetection,
    userRoomOut,
  ]);

  return (
    <Fragment>
      <Unity
        unityProvider={unityProvider}
        style={{ width: "100%", height: "100vh" }}
      ></Unity>
    </Fragment>
  );
}
