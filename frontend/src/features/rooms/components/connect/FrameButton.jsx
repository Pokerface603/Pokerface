import React from "react";
import Frame from "../Frame";

const FrameButton = ({ text, onClick, tab }) => {
  const className = `relative p-0 border-none bg-transparent ${
    tab === text ? "text-white" : ""
  }`;

  return (
    <button onClick={() => onClick(text)} className={className}>
      <Frame style={{ objectFit: "cover" }} />
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
