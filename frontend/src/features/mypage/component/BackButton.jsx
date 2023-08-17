import React from "react";
import WoodButton from "@component/WoodButton";
import WoodStick from "../../../assets/images/rooms/wood-stick.svg";
import { useNavigate } from "react-router-dom";
import soundEffects from "@config/soundEffects";
import { useDispatch } from "react-redux";
import { clearUser } from "@store/userSlice";

const BackButton = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const onLobby = () => {
    soundEffects.load.play();
    navigate("/lobby");
  };

  const onLogout = () => {
    dispatch(clearUser());

    localStorage.removeItem("authorization");
    localStorage.removeItem("authorization-refresh");

    navigate("/login");
  };

  return (
    <div className="m-auto flex justify-center items-end relative">
      <img className="w-2/3 mt-16" src={WoodStick} alt="wood stick" />
      <div className="absolute">
        <div className="transform rotate-2">
          <WoodButton
            width="225px"
            height="60px"
            text={"logout"}
            fontSize="40px"
            onClick={onLogout}
          />

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
