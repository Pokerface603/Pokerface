import React, { Fragment, useEffect } from "react";
import { Unity, useUnityContext } from "react-unity-webgl";

export default function Game() {
  const { unityProvider, sendMessage, addEventListener, removeEventListener } =
    useUnityContext({
      loaderUrl: "/Build/poker_face.loader.js",
      dataUrl: "/Build/poker_face.data",
      frameworkUrl: "/Build/poker_face.framework.js",
      codeUrl: "/Build/poker_face.wasm",
    });

  useEffect(() => {
    const handleUserId = () => {
      sendMessage("NetworkManager", "ReceiveUnityUserId", "sdfsdfsdf");
    };

    const handleRoomName = () => {
      sendMessage("NetworkManager", "ReceiveUnityRoomName", "pine room");
    };

    addEventListener("TakeUserIdFromReact", handleUserId);
    addEventListener("TakeRoomNameFromReact", handleRoomName);
    return () => {
      removeEventListener("TakeUserIdFromReact", handleUserId);
      removeEventListener("TakeRoomNameFromReact", handleRoomName);
    };
  }, [sendMessage, addEventListener, removeEventListener]);

  return (
    <Fragment>
      <Unity unityProvider={unityProvider} style={{ width: "100%" }}></Unity>
    </Fragment>
  );
}
