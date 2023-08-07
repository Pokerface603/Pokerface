import Wanted from "@component/Wanted";
import React from "react";
import EmailCheckModal from "./emailcheck/EmailCheckModal";
import RegistForm from "./registform/RegistForm";
import cowboy from "../../../assets/images/cowboy.svg";
import RegistStamp from "./RegistStamp";

const Regist = () => {
  return (
    <>
      <EmailCheckModal width="607px" height="226px" fontSize="40px" />
      <div className="flex justify-center align-middle items-center h-60">
        <Wanted text="only alive" />
      </div>
      <div className="flex justify-center align-middle items-center">
        <img src={cowboy} alt="" />
      </div>
      <RegistForm />
      <RegistStamp />
    </>
  );
};

export default Regist;
