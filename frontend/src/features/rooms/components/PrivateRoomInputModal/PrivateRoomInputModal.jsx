import Parchment from "@component/Parchment";
import React, { useState } from "react";
import ModalHeader from "../RoomMakeModal/ModalHeader";
import Input from "@component/Input";
import Button from "@component/Button";
import { useNavigate } from "react-router-dom";
import { hashOpenviduTitle } from "@util/hashing";
import { participateRoom } from "@feature/rooms/api/session";
import soundEffects from "@config/soundEffects";

function PrivateRoomInputModal({ close, roomName, gameMode }) {
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const onInputPassword = (e) => {
    const {
      target: { value },
    } = e;

    setPassword(value);
  };

  const onClickMakeRoom = async () => {
    soundEffects.shot.play();

    const sessionId = hashOpenviduTitle(roomName);

    if (gameMode !== "BLIND") {
      const token = await participateRoom(sessionId, "");
      navigate(`../game/${sessionId}`, { state: { token, gameMode } });
    } else {
      navigate(`../game/${sessionId}`, { state: { gameMode } });
    }
  };
  return (
    <Parchment
      style={{
        width: "862px",
        height: "400px",
        position: "absolute",
        top: "calc(50vh - 200px)",
        left: "calc(50vw - 431px)",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <div>
        <ModalHeader title={"비밀번호 확인"} />
        <Input
          placeholder={"비밀번호"}
          height={"50px"}
          width={"100%"}
          onChange={onInputPassword}
        />
        <div className="flex m-5 gap-4">
          <Button
            label={"방 입장"}
            background={"var(--red)"}
            color={"white"}
            width={"300px"}
            fontSize={"30px"}
            onClick={() => onClickMakeRoom()}
          ></Button>
          <Button
            label={"취소"}
            background={"var(--brown_dark)"}
            color={"white"}
            width={"300px"}
            fontSize={"30px"}
            onClick={close}
          ></Button>
        </div>
      </div>
    </Parchment>
  );
}

export default PrivateRoomInputModal;
