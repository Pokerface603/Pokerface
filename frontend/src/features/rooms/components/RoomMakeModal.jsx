import Button from "@component/Button";
import Input from "@component/Input";
import Parchment from "@component/Parchment";
import React, { useState } from "react";
import ModalHeader from "./ModalHeader";
import CheckBox from "@component/checkbox/CheckBox";
import { makeRoom } from "../api/room";
import { useNavigate } from "react-router-dom";

function RoomMakeModal({ close }) {
  const navigate = useNavigate();

  const [roomData, setRoomData] = useState({
    roomName: "",
    roomPassword: "",
    isPrivate: false,
    gameMode: "NORMAL",
  });

  const onChangeRoomName = (e) => {
    const {
      target: { value },
    } = e;

    setRoomData((prevRoomData) => ({ ...prevRoomData, roomName: value }));
  };

  const onChangePassword = (e) => {
    const {
      target: { value },
    } = e;

    setRoomData((prevRoomData) => ({ ...prevRoomData, roomPassword: value }));
  };

  const onClickMakeRoom = () => {
    makeRoom(roomData);
    navigate(`game/${roomData.roomName}`);
  };

  const onToggleSecretRoom = () => {
    setRoomData((prevRoomData) => ({
      ...prevRoomData,
      isPrivate: !prevRoomData.isPrivate,
    }));
  };

  return (
    <Parchment
      style={{
        width: "862px",
        height: "646.5px",
        position: "absolute",
        top: "calc(50vh - 323px)",
        left: "calc(50vw - 431px)",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <div>
        <ModalHeader />
        <div
          style={{
            display: "flex",
            gap: "15px",
            flexDirection: "column",
            fontWeight: "bold",
          }}
        >
          <div className="flex">
            <Input
              placeholder={"방 제목"}
              height={"50px"}
              style={{
                flex: "1 0 auto",
              }}
              onChange={onChangeRoomName}
            />
            <CheckBox
              width={"55px"}
              height={"55px"}
              fontFamily={"nexonGothic"}
              fontSize={"44px"}
              onClick={onToggleSecretRoom}
            />
          </div>

          {roomData.isPrivate && (
            <Input
              placeholder={"비밀번호"}
              height={"50px"}
              onChange={onChangePassword}
              disabled={!roomData.isPrivate}
            />
          )}
          <Input placeholder={"모드"} height={"50px"} />
        </div>

        <div className="flex gap-5 m-8">
          <Button
            label={"방 생성"}
            background={"var(--red)"}
            color={"white"}
            width={"300px"}
            fontSize={"30px"}
            onClick={onClickMakeRoom}
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

export default RoomMakeModal;
