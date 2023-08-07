import React from "react";
import Frame from "../../../../assets/images/rooms/frame.svg";

const ImageButton = ({ text }) => {
  return (
    <button className="relative p-0 border-none bg-transparent hover:text-white focus:text-white">
      <img src={Frame} alt="frame" className="object-cover" />
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

export default ImageButton;
