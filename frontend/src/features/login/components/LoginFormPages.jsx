import React from "react";
import Parchment from "@component/Parchment";
import Wanted from "@component/Wanted";
import WoodBackground from "@component/WoodBackground";
import cowboy from "../../../assets/images/cowboy.svg";
import LoginFormContent from "./LoginFormContent"; 

const LoginFormPages = () => {
  return (
    <>
      <WoodBackground>
        <Parchment style={{ height: "900px", width: "680px" }}>
          <div className="h-1/4 flex item-center">
            <Wanted width="548px" height="171px" text="DEATH OR ALIVE" />
          </div>
          <div className="h-2/5 flex justify-center">
            <img src={cowboy} alt="cowboy" />
          </div>
          <LoginFormContent />
        </Parchment>
      </WoodBackground>
    </>
  );
};

export default LoginFormPages;