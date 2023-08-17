import React from "react";
import wanted from "../assets/images/wanted.svg";

const Wanted = ({ width, height, text }) => {
  return (
    <div
      className="text-center justify-self-auto"
      style={{ width, height, margin: "auto" }}>
      <img src={wanted} style={{ margin: "auto" }} alt="wanted" />
      <div
        className="align-top leading-10"
        style={{ fontFamily: "Unchained-Regular", fontSize: "55px" }}>
        {text}
      </div>
    </div>
  );
};

export default Wanted;
