import { OpenVidu } from "openvidu-browser";
import React, { useEffect, useLayoutEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import OpeviduVideo from "../components/OpeviduVideo";
import { useRef } from "react";
import Game from "../components/Game";
import { ReactComponent as Avatar } from "@asset/images/BlindAvatar.svg";
import { leaveRoom } from "@feature/rooms/api/room";
import { useSelector } from "react-redux";

function GamePage() {
  const navigate = useNavigate();
  const {
    state: { token, gameMode },
  } = useLocation();

  const { email } = useSelector((state) => state.user);

  let ov = new OpenVidu();
  const { sessionId } = useParams();

  const [mySession, _setMySession] = useState();

  const [sessionInfo, _setSessionInfo] = useState({
    mainStreamManager: undefined, // Main video of the page. Will be the 'publisher' or one of the 'subscribers'
    publisher: undefined,
    subscribers: [],
  });

  const sessionInfoRef = useRef(sessionInfo);
  const mySessionRef = useRef(mySession);

  const setSessionInfo = (newSessionInfo) => {
    sessionInfoRef.current = newSessionInfo;
    _setSessionInfo(newSessionInfo);
  };

  const setMySession = (newMySession) => {
    mySessionRef.current = newMySession;
    _setMySession(newMySession);
  };

  const onUnload = (e) => {
    leaveSession();
  };

  useEffect(() => {
    joinSession();
    window.addEventListener("beforeunload", onUnload);

    return () => {
      leaveSession();
    };
  }, []);

  const connectStream = (event) => {
    const subscriber = mySession.subscribe(event.stream, undefined);

    const { subscribers } = sessionInfoRef.current;

    subscribers.push(subscriber);

    setSessionInfo({ ...sessionInfoRef.current, subscribers });
  };
  useEffect(() => {
    if (!mySession || !needCamera()) {
      return;
    }

    mySession.on("streamCreated", (event) => {
      connectStream(event);
    });

    mySession.on("streamDestroyed", (event) => {
      deleteSubscriber(event.stream.streamManager);
    });

    mySession.on("exception", (exception) => {
      console.warn(exception);
    });

    mySession.connect(token).then(async () => {
      const publisher = await ov.initPublisherAsync(undefined, {
        audioSource: undefined, // The source of audio. If undefined default microphone
        videoSource: undefined, // The source of video. If undefined default webcam
        publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
        publishVideo: true, // Whether you want to start publishing with your video enabled or not
        resolution: "640x480", // The resolution of your video
        frameRate: 30, // The frame rate of your video
        insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
        mirror: false, // Whether to mirror your local video or not
      });

      mySession.publish(publisher);

      setSessionInfo({
        ...sessionInfoRef.current,
        mainStreamManager: publisher,
        publisher,
      });
    });
  }, [mySession]);

  const deleteSubscriber = (streamManager) => {
    const { subscribers } = sessionInfoRef.current;

    const deleted = subscribers.filter((sm) => sm !== streamManager);

    setSessionInfo({
      ...sessionInfoRef.current,
      subscribers: deleted,
    });
  };

  const joinSession = async () => {
    if (mySession || !needCamera()) {
      return;
    }

    const ovSession = ov.initSession();
    setMySession(ovSession);
  };

  const leaveSession = async () => {
    await leaveRoom({ email, sessionId });

    if (!needCamera()) {
      return;
    }

    await mySessionRef.current.disconnect();
  };

  const onClickLeave = () => {
    navigate("../lobby");
  };

  const needCamera = () => {
    return gameMode === "NORMAL" || gameMode === "EMOTION";
  };

  return (
    <div>
      <Game roomName={sessionId} gameMode={gameMode} leaveRoom={onClickLeave} />
      {needCamera() &&
        sessionInfoRef?.current.subscribers?.map((sub, i) => {
          return (
            <OpeviduVideo key={i} streamManager={sub} gameMode={gameMode} />
          );
        })}
      {!needCamera() && (
        <Avatar
          style={{
            height: "450px",
            position: "absolute",
            left: "calc(50vw - 337.5px)",
            top: "20px",
          }}
        />
      )}
    </div>
  );
}

export default GamePage;
