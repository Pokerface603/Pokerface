import React from "react";
import WoodButton from "@component/WoodButton";
import WoodStick from "../../../assets/images/rooms/wood-stick.svg";

const BackButton = () => {
  return (
    <div className="m-auto flex justify-center items-end relative">
      <img className="w-2/3 mt-16" src={WoodStick} alt="wood stick" />
      <div className="absolute">
        <div className="mb-14 transform rotate-2">
          <WoodButton
            width="225px"
            height="60px"
            text="Back"
            onClick={onclick}
            fontSize="40px"
          />
        </div>
      </div>
    </div>
  );
};

export default BackButton;
