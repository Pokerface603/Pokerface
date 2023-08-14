import React from "react";
import WoodButton from "@component/WoodButton";
import WoodStick from "../../../assets/images/rooms/wood-stick.svg";
import { useNavigate } from "react-router-dom";

const BackButton = () => {
  const navigate = useNavigate();

  const onLobby = () => {
    navigate("/lobby");
  };

  return (
    <div className="m-auto flex justify-center items-end relative">
      <img className="w-2/3 mt-16" src={WoodStick} alt="wood stick" />
      <div className="absolute">
        <div className="mb-14 transform rotate-2">
          <WoodButton
            width="225px"
            height="60px"
            text="Back"
            onClick={onLobby}
            fontSize="40px"
          />
        </div>
      </div>
    </div>
  );
};

export default BackButton;
