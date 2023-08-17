import React from "react";

const WoodButton = ({ width, height, text, fontSize, onClick }) => {
  return (
    <div
      className="bg-wood-button bg-cover text-center hover:brightness-90"
      style={{
        width,
        height,
      }}
      onClick={onClick}>
      <div
        className="leading-none"
        style={{
          fontFamily: "Unchained-Spaghetti",
          fontSize,
        }}>
        {text}
      </div>
    </div>
  );
};

export default WoodButton;
