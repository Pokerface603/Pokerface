import React from "react";
import Frame from "../Frame";

const FrameButton = ({ text }) => {
  return (
    <button className="relative p-0 border-none bg-transparent hover:text-white focus:text-white">
      <Frame style={{ objectFit: "cover"}} />
      <div
        className="absolute top-0 left-0 right-0 bottom-0 flex items-center justify-center"
        style={{
          fontFamily: "Unchained-Spaghetti",
          fontSize: "30px",
          marginBottom: "7px",
        }}
      >
        {text}
      </div>
    </button>
  );
};

export default FrameButton;
