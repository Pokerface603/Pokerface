import React from "react";
import stamp from "assets/images/stamp.svg";

const RegistStamp = (onClick) => {
  return (
    <img
      src={stamp}
      alt="stamp"
      className="absolute cursor-pointer brightness-0 opacity-50 hover:brightness-100 hover:opacity-100 bottom-2 w-40 right-5"
      onClick={onClick}
    />
  );
};

export default RegistStamp;
