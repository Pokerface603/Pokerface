import React from "react";
import { ReactComponent as Stamp } from "assets/images/stamp.svg";

// animate위해 img를 svg로 변경
const RegistStamp = ({ onClick, animate }) => {
  return (
    <Stamp
      className={`${
        animate ? "animate-bounce" : ""
      } absolute cursor-pointer brightness-0 opacity-50 hover:brightness-100 hover:opacity-100 bottom-2 w-40 right-5`}
      onClick={onClick}
    />
  );
};

export default RegistStamp;
