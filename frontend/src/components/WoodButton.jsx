import React from "react";

const WoodButton = ({ width, height, text, onClick }) => {
  return (
    <div
      className="bg-wood-button bg-cover text-center hover:brightness-90"
      style={{
        width,
        height,
      }}
      onClick={onClick}>
      <text
        className="align-top"
        style={{
          fontFamily: "Unchained-Spaghetti",
        }}>
        {text}
      </text>
    </div>
  );
};

export default WoodButton;
