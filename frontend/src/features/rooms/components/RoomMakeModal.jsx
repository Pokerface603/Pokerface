import Button from "@component/Button";
import Input from "@component/Input";
import Parchment from "@component/Parchment";
import React from "react";
import ModalHeader from "./ModalHeader";
import CheckBox from "@component/checkbox/CheckBox";

function RoomMakeModal() {
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
        <ModalHeader>
          <span style={{ color: "red" }}>방 생성</span>
        </ModalHeader>
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
            />
            <CheckBox
              width={"55px"}
              height={"55px"}
              fontFamily={"nexonGothic"}
              fontSize={"44px"}
            />
          </div>

          <Input placeholder={"비밀번호"} height={"50px"} />
          <Input placeholder={"모드"} height={"50px"} />
        </div>

        <div className="flex gap-5 m-8">
          <Button
            label={"방 생성"}
            background={"var(--red)"}
            color={"white"}
            width={"300px"}
            fontSize={"30px"}
          ></Button>
          <Button
            label={"취소"}
            background={"var(--brown_dark)"}
            color={"white"}
            width={"300px"}
            fontSize={"30px"}
          ></Button>
        </div>
      </div>
    </Parchment>
  );
}

export default RoomMakeModal;
