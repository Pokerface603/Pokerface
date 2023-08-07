import React from "react";
import FrameButton from "./FrameButton";
import AmericanSaloon from "../../../../assets/images/rooms/american-saloon.svg";
import TierNoBox from "../TierNoBox";

const ConnectList = ({ tier, nickname, button }) => {
  const isenglish = /^[A-Za-z]+$/;

  const fontFamily = isenglish.test(nickname)
    ? "Unchained-Spaghetti"
    : "NexonGothic";
  return (
    <div className="relative w-600 h-200">
      <div className="flex">
        <div className="flex-1 relative flex justify-center items-center mt-4">
          <FrameButton text="CONNECT" />
        </div>
        <div className="flex-1 relative flex justify-center items-center mt-4">
          <FrameButton text="FRIENDS" />
        </div>
      </div>
      <div className="flex items-center justify-center">
        <div
          className="grid grid-cols-7 gap-2 items-center"
          style={{ width: "380px", height: "43px" }}
        >
          <div className="m-auto">
            <TierNoBox tier={tier} />
          </div>
          <p className="col-span-3" style={{ fontFamily, fontSize: "30px" }}>
            {nickname}
          </p>
          <div className="col-end-7 col-span-2">{button}</div>
        </div>
      </div>
      <img
        src={AmericanSaloon}
        style={{
          top: "90px",
          right: "150px",
          position: "absolute",
          zIndex: 1,
        }}
        alt="american-saloon"
      />
    </div>
  );
};

export default ConnectList;
