import Wanted from "@component/Wanted";
import React from "react";
import RegistForm from "./registform/RegistForm";
import cowboy from "../../../assets/images/cowboy.svg";
import WoodBackground from "@component/WoodBackground";
import Parchment from "@component/Parchment";

const RegistPage = () => {
  return (
    <>
      <WoodBackground>
        <Parchment
          style={{ width: "912px", height: "973px", position: "relative" }}
        >
          <div className="flex justify-center align-middle items-center h-60">
            <Wanted text="only alive" />
          </div>
          <div className="flex justify-center align-middle items-center">
            <img src={cowboy} alt="cowboy" style={{ height: "370px" }} />
          </div>
          <RegistForm />
        </Parchment>
      </WoodBackground>
    </>
  );
};

export default RegistPage;
