import { OpenVidu } from "openvidu-browser";
import React, { useEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import OpeviduVideo from "../components/OpeviduVideo";
import Button from "@component/Button";
import { useRef } from "react";
import Game from "../components/Game";

function GamePage() {
  const navigate = useNavigate();
  const {
    state: { token, gameMode },
  } = useLocation();

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

  useEffect(() => {
    joinSession();

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
    if (!mySession) {
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
    if (mySession) {
      return;
    }

    const ovSession = ov.initSession();
    setMySession(ovSession);
  };

  const leaveSession = () => {
    mySessionRef.current.disconnect();
    navigate("/");
  };

  return (
    <div>
      <Game roomName={sessionId} gameMode={gameMode} />
      {sessionInfo?.subscribers?.map((sub, i) => (
        <OpeviduVideo key={i} streamManager={sub} />
      ))}

      <Button label={"나가기"} onClick={leaveSession}></Button>
    </div>
  );
}

export default GamePage;
