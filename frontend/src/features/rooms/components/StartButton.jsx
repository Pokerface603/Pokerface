import React from "react";
import WoodButton from "@component/WoodButton";
import WoodStick from "../../../assets/images/rooms/wood-stick.svg";

const Button = ({ onClickCreateRoom }) => {
  return (
    <div className="flex justify-center items-center relative">
      <img src={WoodStick} alt="wood stick" />
      <div className="absolute">
        <div className="mb-5 transform rotate-2">
          <WoodButton
            width="325px"
            height="60px"
            text="QUICK START"
            onClick={onclick}
            fontSize="40px"
          />
        </div>
        <div>
          <WoodButton
            width="325px"
            height="60px"
            text="CREATE ROOM"
            onClick={onClickCreateRoom}
            fontSize="40px"
          />
        </div>
      </div>
    </div>
  );
};

export default Button;
