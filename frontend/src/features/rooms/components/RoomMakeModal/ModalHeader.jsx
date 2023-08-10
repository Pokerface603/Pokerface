import React from "react";
import { ReactComponent as HeaderFrame } from "@asset/images/HeaderFrame.svg";

function ModalHeader() {
  return (
    <div
      className="relative flex justify-center items-center"
      style={{
        marginBottom: "37px",
      }}
    >
      <HeaderFrame />
      <span
        className="absolute font-['nexonGothic']"
        style={{
          fontSize: "40px",
          color: "white",
          fontWeight: "bold",
        }}
      >
        방 생성
      </span>
    </div>
  );
}

export default ModalHeader;
